package it.analyze.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.vo.SysResult;

import it.analyze.mapper.JobNumberMapper;
import it.analyze.pojo.CityPojo;
import it.analyze.pojo.Test;
import it.analyze.redis.RedisService;
import it.analyze.service.JobNumberService;

@Service
public class JobNumberServiceImpl implements JobNumberService {
	@Autowired
	private JobNumberMapper jobNumberMapper;

	@Autowired
	private RedisService jedis;
	private static final ObjectMapper MAPPER = new ObjectMapper();

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.analyze.service.JobNumberService#needPeopleForCity()
	 */
	@Override
	public SysResult needPeopleForCity() {
		try {
			List<CityPojo> list = new ArrayList<CityPojo>();

			 String key = "it_needPeopleForCity";
			 if (jedis.exists(key)) {
			 // 从redis获取数据
			 System.out.println("从redis获取数据number");
			 String valueJson = jedis.get(key);
			 SysResult result = MAPPER.readValue(valueJson, SysResult.class);
			 list = (List<CityPojo>) result.getData();
			 } else {
			 String[] city = new String[] { "北京", "上海", "广州", "深圳", "杭州", "成都", "重庆",
			 "武汉", "苏州", "西安", "天津", "南京", "郑州",
			 "长沙", "沈阳" };
			List<String> pos = jobNumberMapper.typeQuery();
			// int cl = city.length;
			int posl = pos.size();
			for (int i = 0; i < posl; i++) {
				CityPojo p = new CityPojo(15);
				// 根据城市、技能查询出num
				List<Test> num = jobNumberMapper.Cquery(pos.get(i));
				Iterator<Test> iterator = num.iterator();
				while (iterator.hasNext()) {
					Test objm = (Test) iterator.next();
					if (objm.getCity().toString().contains("-")) {
						iterator.remove();
					}
				}

				for (int j = 0; j < num.size(); j++) {

					if (num.get(j).getCity().contains("北京")) {
						p.getNum()[0] = num.get(j).getNum();
					} else if (num.get(j).getCity().contains("上海")) {
						p.getNum()[1] = num.get(j).getNum();
					} else if (num.get(j).getCity().contains("广州")) {
						p.getNum()[2] = num.get(j).getNum();
					} else if (num.get(j).getCity().contains("深圳")) {
						p.getNum()[3] = num.get(j).getNum();
					} else if (num.get(j).getCity().contains("杭州")) {
						p.getNum()[4] = num.get(j).getNum();
					} else if (num.get(j).getCity().contains("成都")) {
						p.getNum()[5] = num.get(j).getNum();
					} else if (num.get(j).getCity().contains("重庆")) {
						p.getNum()[6] = num.get(j).getNum();
					} else if (num.get(j).getCity().contains("武汉")) {
						p.getNum()[7] = num.get(j).getNum();
					} else if (num.get(j).getCity().contains("苏州")) {
						p.getNum()[8] = num.get(j).getNum();
					} else if (num.get(j).getCity().contains("西安")) {
						p.getNum()[9] = num.get(j).getNum();
					} else if (num.get(j).getCity().contains("天津")) {
						p.getNum()[10] = num.get(j).getNum();
					} else if (num.get(j).getCity().contains("南京")) {
						p.getNum()[11] = num.get(j).getNum();
					} else if (num.get(j).getCity().contains("郑州")) {
						p.getNum()[12] = num.get(j).getNum();
					} else if (num.get(j).getCity().contains("长沙")) {
						p.getNum()[13] = num.get(j).getNum();
					} else if (num.get(j).getCity().contains("沈阳")) {
						p.getNum()[14] = num.get(j).getNum();
					}
					p.setType_name(pos.get(i));
					list.add(p);
				}
			}

			// System.out.println("从mysql获取数据");
			 String valueJson = MAPPER.writeValueAsString(SysResult.oK(list));
			 jedis.set(key, valueJson);
			 }
			removeDuplicateWithOrder(list);

			return SysResult.oK(list);
		} catch (Exception e) {
			e.printStackTrace();
			return SysResult.build(201, "很抱歉~~没有查询到数据");
		}
	}

	// 删除ArrayList中重复元素，保持顺序
	public static void removeDuplicateWithOrder(List list) {
		Set set = new HashSet();
		List newList = new ArrayList();
		for (Iterator iter = list.iterator(); iter.hasNext();) {
			Object element = iter.next();
			if (set.add(element))
				newList.add(element);
		}
		list.clear();
		list.addAll(newList);
		System.out.println(" remove duplicate " + list);
	}
}
