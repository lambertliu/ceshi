package it.analyze.service.impl;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.vo.SysResult;

import it.analyze.mapper.DescMapper;
import it.analyze.pojo.WelCount;
import it.analyze.redis.RedisService;
import it.analyze.service.WelfareService;

@Service
public class WelfareServiceImpl implements WelfareService {

	@Autowired
	private DescMapper mapper;
	@Autowired
	private RedisService jedis;
	private static final ObjectMapper MAPPER = new ObjectMapper();

	@Override
	
	public SysResult welfareQuery() {
		// 将要查询的关键字保存到数组中
		String[] wel = new String[] { "五险一金", "定期体检", "交通补助", "房补", "年底双薪", "不加班", "高温补贴", "每年多次调薪", "餐补", "股票期权",
				"双休", "补充医疗保险", "14薪", "员工旅游", "节日福利", "包住", "弹性工作", "带薪年假" };
		// 定义一个List存入数据
		List<WelCount> list = new ArrayList<WelCount>();
		String key = "it_welfareQuery";
		try {
			if (jedis.exists(key)) {
				// 从redis获取数据
				System.out.println("从redis获取数据dbwelfare");
				String valueJson = jedis.get(key);
				SysResult result = MAPPER.readValue(valueJson, SysResult.class);
				list = (List<WelCount>) result.getData();
			} else {
				for (int i = 0; i < wel.length; i++) {
					// 循环模糊查询，数据返回给封装对象，最后存入到list集合中
					list.add(new WelCount(mapper.selectWelfare(wel[i]), wel[i]));
				}
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
