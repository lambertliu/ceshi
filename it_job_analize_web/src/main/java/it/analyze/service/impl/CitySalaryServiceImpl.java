package it.analyze.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.vo.SysResult;

import it.analyze.mapper.CSMapper;
import it.analyze.pojo.Position;
import it.analyze.redis.RedisService;
import it.analyze.service.CitySalaryService;

@Service
public class CitySalaryServiceImpl implements CitySalaryService {

	@Autowired
	private CSMapper mapper;
	@Autowired
	private RedisService jedis;
	private static final ObjectMapper MAPPER = new ObjectMapper();

	@Override
	public SysResult CSQuery(String position, byte experience, String education) {

		String key = "it_CSQuery5" + position + experience + education;
		try {
			// 拼接position1、position2字符串 例：java开发工程师
			Position p = new Position();
			p.setPositionName(position);
			p.setExperience(experience);
			p.setEdu(education);
			// 查询数据库

			List<Map<String, Object>> list = new ArrayList<>();
			// 判断key存在与否
			if (jedis.exists(key)) {
				// 从redis获取数据
				System.out.println("从redis获取数据citysalary");
				String valueJson = jedis.get(key);
				SysResult result = MAPPER.readValue(valueJson, SysResult.class);
				list = (List<Map<String, Object>>) result.getData();
			} else {
				list = mapper.selectaaa(p);
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
			System.out.println(list.size());
			return SysResult.oK(list);
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, "抱歉~~没有查询到数据");
		}

	}

	@Override
	public SysResult CSSQuery(String position, byte experience, String education) {

		String key = "it_CSSQuery5" + position + experience + education;
		try {
			// 拼接position1、position2字符串 例：java开发工程师
			Position p = new Position();
			p.setPositionName(position);
			p.setExperience(experience);
			p.setEdu(education);
			// 查询数据库

			List<Map<String, Object>> list = new ArrayList<>();
			// 判断key存在与否
			if (jedis.exists(key)) {
				// 从redis获取数据
				System.out.println("从redis获取");
				String valueJson = jedis.get(key);
				SysResult result = MAPPER.readValue(valueJson, SysResult.class);
				list = (List<Map<String, Object>>) result.getData();
				return SysResult.oK(list);
			} else {
				list = mapper.selectbbb(p);
				Iterator<Map<String,Object>> iterator = list.iterator(); 
				while(iterator.hasNext()){
					Map<String,Object> objm = (Map<String, Object>) iterator.next();
						if(objm.get("city").toString().contains("-")) {
							iterator.remove();
						}
					}
				System.out.println("数据库获取");
				// System.out.println(list);
				String valueJson = MAPPER.writeValueAsString(SysResult.oK(list));
				jedis.set(key, valueJson);
				return SysResult.oK(list);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, "抱歉~~没有查询到数据");
		}
	}
}
