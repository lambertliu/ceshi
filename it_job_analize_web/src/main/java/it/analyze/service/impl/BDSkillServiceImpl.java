package it.analyze.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.vo.SysResult;

import it.analyze.mapper.DescMapper;
import it.analyze.pojo.PositionCount;
import it.analyze.pojo.WelCount;
import it.analyze.redis.RedisService;
import it.analyze.service.BDSkillService;

@Service
public class BDSkillServiceImpl implements BDSkillService {

	@Autowired
	private DescMapper descMapper;
	@Autowired
	private RedisService jedis;
	private static final ObjectMapper MAPPER = new ObjectMapper();

	@Override
	public SysResult queryDesc() {
		// 将要查询的关键字保存到数组中
		String[] pos = new String[] { "hadoop", "java", "spark", "hbase", "hive", "linux", "python", "storm", "mysql",
				"sql", "shell", "mapreduce", "c++", "kafka", "oracle", "redis", "nosql", "scala", "flume", "c", "r",
				"hdfs", "web", "mongodb", "prel", "zookeeper", "pig", "sas", "spring", "javascript", "mahout", "php",
				"spss", "impala", "j2ee", "yarn", "jquery", "sqoop", "html", "hibernate", "sqlserver", "css",
				"cassandra", "http", "lucene", "jvm", "tomcat", "excel", "solr", "ajax" };
		// 定义一个List存入数据
		List<PositionCount> list = new ArrayList<PositionCount>();
		// list.add(new PositionCount(descMapper.selectCount(), position))
		String key = "it_queryDescTE";
		try {
			if (jedis.exists(key)) {
				// 从redis获取数据
				System.out.println("从redis获取数据it_queryDescTE");
				String valueJson = jedis.get(key);
				SysResult result = MAPPER.readValue(valueJson, SysResult.class);
				list = (List<PositionCount>) result.getData();
			} else {
				for (int i = 0; i < pos.length; i++) {
					// 循环模糊查询，数据返回给封装对象，最后存入到list集合中
					list.add(new PositionCount(descMapper.selectPosition(pos[i]), pos[i]));
				}

				// 对list中的对象进行排序
				Collections.sort(list);

				// Collections.sort(list, new Comparator<PositionCount>() {
				//
				// @Override
				// public int compare(PositionCount o1, PositionCount o2) {
				// if (o1.getCount() > o2.getCount()) {
				// return 1;
				// } else if (o1.getCount() < o2.getCount()) {
				// return 0;
				// } else {
				// return o1.getPosition().compareTo(o2.getPosition());
				// }
				// }
				// });

				String valueJson = MAPPER.writeValueAsString(SysResult.oK(list));
				jedis.set(key, valueJson);
			}

			return SysResult.oK(list);

		} catch (Exception e) {

			e.printStackTrace();

			return SysResult.build(201, "很抱歉~~没有查到数据");
		}

	}

}
