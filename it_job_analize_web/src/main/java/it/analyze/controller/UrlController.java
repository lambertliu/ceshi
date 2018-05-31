package it.analyze.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.EasyUIResult;
import com.jt.common.vo.SysResult;

import it.analyze.service.UrlService;

@Controller
public class UrlController {
	
	@Autowired
	private UrlService service;
	
	@RequestMapping("select/{pos}/{city}")
	@ResponseBody
	public EasyUIResult ReUrl(@PathVariable String pos , @PathVariable String city,Integer page,Integer rows) {
		EasyUIResult result = service.QueryTo(pos,city,page,rows);
		return result;
	}
}
