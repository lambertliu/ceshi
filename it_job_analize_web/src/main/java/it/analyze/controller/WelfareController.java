package it.analyze.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.SysResult;

import it.analyze.service.WelfareService;

@Controller
public class WelfareController {

	@Autowired
	private WelfareService service;
	//大数据职位福利待遇分析
	@RequestMapping("dbwelfare")
	@ResponseBody
	public SysResult welfare() {
		SysResult result = service.welfareQuery();
		return result;
	}
}
