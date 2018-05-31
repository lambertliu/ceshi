package it.analyze.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jt.common.vo.SysResult;

import it.analyze.service.PriceService;


@Controller
public class priceAnalizeController {
	@Autowired
	private PriceService priceService;
	//不同职位的平均工资
	@RequestMapping("aveSalary")
	@ResponseBody
	public SysResult analizePrcie() {

		SysResult result=priceService.aveSalary();
		
		return result;
		
		
	}
	
	//具体职务不同城市的平均工资、
	@RequestMapping("aveSalary/position")
	@ResponseBody
	public SysResult aveSalaryPosition( String position) {
		
		SysResult result=priceService.aveSalaryPosition(position);
		
			return result;
		
		
	}
}
