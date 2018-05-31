package it.analyze.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.SysResult;

import it.analyze.service.JobNumberService;


@Controller
public class jobNumberController {
	@Autowired
	private JobNumberService jobNumberService;
	//各职位的市场需求人数(分城市)
	@RequestMapping("number")
	@ResponseBody
	public SysResult peopleNumber() {
		
		SysResult result =jobNumberService.needPeopleForCity();
		
		return result;
		
		
		
	}

}
