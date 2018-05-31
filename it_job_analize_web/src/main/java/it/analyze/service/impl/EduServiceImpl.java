package it.analyze.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.vo.SysResult;

import it.analyze.mapper.PCMapper;
import it.analyze.pojo.EduCount;
import it.analyze.pojo.PositionCount;
import it.analyze.redis.RedisService;
import it.analyze.service.EduService;

@Service
public class EduServiceImpl implements EduService {

	@Autowired
	private PCMapper mapper;

	@Autowired
	private RedisService jedis;
	private static final ObjectMapper MAPPER = new ObjectMapper();

	@Override
	public SysResult eduQuery() {
		List<EduCount> list = new ArrayList<EduCount>();
		String[] edu = new String[] { "不限", "大专", "本科", "硕士", "博士" };
		String key = "it_eduQuery";
		try {
			if (jedis.exists(key)) {
				// 从redis获取数据
				System.out.println("从redis获取数据dbedu");
				String valueJson = jedis.get(key);
				SysResult result = MAPPER.readValue(valueJson, SysResult.class);
				list = (List<EduCount>) result.getData();
			} else {
				System.out.println("从mysql获取数据");
				for (int i = 0; i < edu.length; i++) {
					list.add(new EduCount(edu[i], mapper.eduQuery(edu[i])));
				}
				String valueJson = MAPPER.writeValueAsString(SysResult.oK(list));
				jedis.set(key, valueJson);
			}
			return SysResult.oK(list);

		} catch (Exception e) {

			e.printStackTrace();

			return SysResult.build(201, "很抱歉，没有查询到数据~");
		}

	}

}
