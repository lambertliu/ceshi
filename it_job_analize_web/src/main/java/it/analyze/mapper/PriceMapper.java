package it.analyze.mapper;

import java.util.List;
import java.util.Map;



public interface PriceMapper {

	List<Map<String,Object>> selectAveSalary();

	List<Map<String, Object>> selectaveSalaryPosition(String position);
	
}
