package it.analyze.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.vo.SysResult;

import it.analyze.mapper.PCMapper;
import it.analyze.pojo.CityNum;
import it.analyze.pojo.PositionCount;
import it.analyze.redis.RedisService;
import it.analyze.service.IndexService;

@Service
public class IndexServiceImpl implements IndexService {

	@Autowired
	private PCMapper mapper;
	@Autowired
	private RedisService jedis;
	private static final ObjectMapper MAPPER = new ObjectMapper();
	@SuppressWarnings("null")
	public SysResult peopleCounting() {
		
		String[] city = new String[] {"北京","上海","广州","深圳","杭州","成都","重庆","武汉","苏州","西安","天津","南京","郑州","长沙","沈阳"};
		List<CityNum> list = new ArrayList<CityNum>();
		String key = "it_peopleCounting";
		try {
			if (jedis.exists(key)) {
				// 从redis获取数据
				System.out.println("从redis获取数据host/index");
				String valueJson = jedis.get(key);
				SysResult result = MAPPER.readValue(valueJson, SysResult.class);
				list = (List<CityNum>) result.getData();
			}else {
				for (int i = 0; i < city.length; i++) {
					list.add(new CityNum(city[i],mapper.CNQuery(city[i])));
				}
				String valueJson = MAPPER.writeValueAsString(SysResult.oK(list));
				jedis.set(key, valueJson);
			}
			return SysResult.oK(list);
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, "抱歉~~没有查到数据");
		}
	}
}
