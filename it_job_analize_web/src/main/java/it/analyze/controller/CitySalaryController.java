package it.analyze.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.SysResult;

import it.analyze.service.CitySalaryService;

@Controller
public class CitySalaryController {

	@Autowired
	private CitySalaryService service;

	// 职位学历工作经验对应薪资
	@RequestMapping(value = "citysalary", method = RequestMethod.POST)
	@ResponseBody
	public SysResult CSQuery(String position, byte experience, String education) {
		if (experience == 1) {
			SysResult result = service.CSSQuery(position, experience, education);
			return result;
		}else {
			SysResult result = service.CSQuery(position, experience, education);
			return result;
		}
	}
}
