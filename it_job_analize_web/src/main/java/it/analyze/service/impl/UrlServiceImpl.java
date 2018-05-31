package it.analyze.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jt.common.vo.EasyUIResult;

import it.analyze.mapper.PCMapper;
import it.analyze.pojo.UrlPo;
import it.analyze.service.UrlService;
@Service
public class UrlServiceImpl implements UrlService {

	@Autowired
	private PCMapper mapper;
	@Override
	public EasyUIResult QueryTo(String pos, String city,Integer page,Integer rows) {
			
			//分页查询
			PageHelper pageHelper=new PageHelper();
			pageHelper.startPage(page, rows);
			
			//查出
			List<UrlPo> list = mapper.QueryUrl(getPid(pos),city);
			
			for (UrlPo urlPo : list) {
				urlPo.setCity(city);
				urlPo.setDate(urlPo.getDate().substring(6, 11)); 
				urlPo.setUrl("http://jobs.zhaopin.com/"+urlPo.getUrl()+".htm");
			}
			PageInfo info =new PageInfo(list);
			
			List items = info.getList();
			int total = (int) info.getTotal();
			EasyUIResult result=new EasyUIResult();
			result.setRows(items);
			result.setTotal(total);
			return result;
		
	}
	
	public static Integer getPid(String position) {
		Integer pid = null;
		if (position.indexOf("测试") != -1) {
			pid = 1;
		} else if (position.indexOf("大数据") != -1 || position.indexOf("挖掘") != -1) {
			pid = 7;

		} else if (position.indexOf("Go") != -1 || position.indexOf("golang") != -1
				|| position.indexOf("Golang") != -1) {
			pid = 17;
		} else if (position.indexOf("ERP") != -1) {
			pid = 18;
		} else if (position.indexOf("PHP") != -1 || position.indexOf("php") != -1) {
			pid = 19;
		} else if (position.indexOf("python") != -1 || position.indexOf("Python") != -1) {
			pid = 20;
		} else if (position.indexOf("c#") != -1 || position.indexOf("C#") != -1) {
			pid = 21;
		} else if (position.indexOf(".net") != -1 || position.indexOf(".Net") != -1 || position.indexOf(".NET") != -1) {
			pid = 22;
		} else if (position.indexOf("linux") != -1 || position.indexOf("嵌入式") != -1
				|| position.indexOf("LINUX") != -1) {
			pid = 23;
		} else if (position.indexOf("c++") != -1 || position.indexOf("C++") != -1 || position.indexOf("C语言") != -1
				|| position.indexOf("c语言") != -1) {
			pid = 24;
		} else if (position.indexOf("java") != -1 || position.indexOf("JAVA") != -1) {
			pid = 25;
		} else if (position.indexOf("架构") != -1) {
			pid = 2;

		} else if (position.indexOf("产品") != -1) {
			pid = 3;

		} else if (position.indexOf("数据分析") != -1 || position.indexOf("数据研发") != -1 || position.indexOf("策划") != -1) {
			pid = 4;

		} else if (position.indexOf("网络工程") != -1) {
			pid = 5;

		} else if (position.indexOf("运营") != -1 || position.indexOf("实施") != -1 || position.indexOf("编辑") != -1
				|| position.indexOf("编辑") != -1 || position.indexOf("新媒体") != -1) {
			pid = 6;

		} else if (position.indexOf("大数据") != -1 || position.indexOf("挖掘") != -1) {
			pid = 7;

		} else if (position.indexOf("云计算") != -1) {
			pid = 8;
		} else if (position.indexOf("运维") != -1) {
			pid = 9;
		} else if (position.indexOf("seo") != -1 || position.indexOf("sem") != -1 || position.indexOf("SEO") != -1) {
			pid = 10;

		} else if (position.indexOf("算法") != -1 || position.indexOf("深度") != -1 || position.indexOf("机器") != -1
				|| position.indexOf("视觉") != -1 || position.indexOf("识别") != -1 || position.indexOf("AI") != -1
				|| position.indexOf("人工智能") != -1) {
			pid = 11;

		} else if (position.indexOf("app") != -1 || position.indexOf("ios") != -1 || position.indexOf("安卓") != -1
				|| position.indexOf("APP") != -1 || position.indexOf("Android") != -1
				|| position.indexOf("IOS") != -1) {
			pid = 12;

		} else if (position.indexOf("UI") != -1 || position.indexOf("界面") != -1 || position.indexOf("美工") != -1
				|| position.indexOf("ui") != -1 || position.indexOf("UE") != -1 || position.indexOf("UX") != -1) {
			pid = 13;

		} else if (position.indexOf("HTML") != -1 || position.indexOf("h5") != -1
				|| position.indexOf("Javascript") != -1 || position.indexOf("JavaScript") != -1
				|| position.indexOf("js") != -1 || position.indexOf("前端") != -1 || position.indexOf("html") != -1
				|| position.indexOf("H5") != -1) {
			pid = 14;

		} else if (position.indexOf("游戏") != -1 || position.indexOf("unity3d") != -1 || position.indexOf("vr") != -1
				|| position.indexOf("cocos2dx") != -1 || position.indexOf("Unity3d") != -1) {
			pid = 15;

		} else if (position.indexOf("数据库") != -1) {
			pid = 16;
		} else {
			pid = 0;
		}
		//System.out.println(pid);
		return pid;

	}

}
