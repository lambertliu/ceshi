package it.analyze.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.SysResult;

import it.analyze.service.BDSkillService;

@Controller
@RequestMapping("host")
public class BDSkillController {

	@Autowired
	private BDSkillService service;
	
	//大数据职位技能数据统计分析
	//http://ITAnalyze.com/host/dbtechnology.html		
	@ResponseBody
	@RequestMapping("dbtechnology")
	public SysResult skillAnalyze() {
		SysResult result = service.queryDesc();
		return result; 
	}
}
