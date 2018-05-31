package it.analyze.service;

import java.util.List;
import java.util.Map;

import com.jt.common.vo.SysResult;

public interface PriceService {
	

	public SysResult aveSalary();

	public SysResult aveSalaryPosition(String position);
}
