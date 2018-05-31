package it.analyze.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.vo.SysResult;

import it.analyze.mapper.PriceMapper;
import it.analyze.redis.RedisService;
import it.analyze.service.PriceService;

@Service
public class PriceServiceImpl implements PriceService {
	@Autowired
	private PriceMapper priceMapper;
	@Autowired
	private RedisService jedis;
	private static final ObjectMapper MAPPER = new ObjectMapper();

	public SysResult aveSalary() {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		String key = "it_aveSalary";
		try {
			if (jedis.exists(key)) {
				// 从redis获取数据
				System.out.println("从redis获取数据aveSalary");
				String valueJson = jedis.get(key);
				SysResult result1 = MAPPER.readValue(valueJson, SysResult.class);
				result = (List<Map<String, Object>>) result1.getData();
			} else {
				result = priceMapper.selectAveSalary();
				String valueJson = MAPPER.writeValueAsString(SysResult.oK(result));
				jedis.set(key, valueJson);
			}
			return SysResult.oK(result);
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, "抱歉~~没有查询到数据");
		}

	}

	@Override
	public SysResult aveSalaryPosition(String position) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		String key = "it_aveSalaryPosition1"+position;
		try {
			if (jedis.exists(key)) {
				// 从redis获取数据
				String valueJson = jedis.get(key);
				SysResult result = MAPPER.readValue(valueJson, SysResult.class);
				list = (List<Map<String, Object>>) result.getData();
			} else {
				list = priceMapper.selectaveSalaryPosition(position);
				Iterator<Map<String,Object>> iterator = list.iterator(); 
				while(iterator.hasNext()){
					Map<String,Object> objm = (Map<String, Object>) iterator.next();
						if(objm.get("city").toString().contains("-")) {
							iterator.remove();
						}
					}

				String valueJson = MAPPER.writeValueAsString(SysResult.oK(list));
				jedis.set(key, valueJson);
			}
			return SysResult.oK(list);
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, "很抱歉~~没有查询到数据");
		}

	}
}
