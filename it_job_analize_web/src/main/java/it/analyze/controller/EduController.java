package it.analyze.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.SysResult;

import it.analyze.service.EduService;

@Controller
public class EduController {

	@Autowired
	private EduService service;
	
	@RequestMapping("dbedu")
	@ResponseBody
	public SysResult eduQuery() {
		SysResult result = service.eduQuery();
		return result;
	}
}
