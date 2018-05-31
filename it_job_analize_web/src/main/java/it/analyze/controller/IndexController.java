package it.analyze.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.SysResult;

import it.analyze.service.IndexService;

@Controller	
@RequestMapping("host")
public class IndexController {
	@Autowired
	private IndexService indexService;
	
	//工作地点分布（全国地图）
	@RequestMapping("index")
	@ResponseBody
	public SysResult goIndex() {
		SysResult result = indexService.peopleCounting();
		return result;
	}
	
}
