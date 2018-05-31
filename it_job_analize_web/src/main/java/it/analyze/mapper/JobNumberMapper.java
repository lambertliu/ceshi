package it.analyze.mapper;

import java.util.List;
import java.util.Map;

import com.jt.common.mapper.MyMapper;

import it.analyze.pojo.Position;
import it.analyze.pojo.Test;


public interface JobNumberMapper extends MyMapper<Position>{

	List<Map<String, Object>> getNumberForCity();

	List<String> typeQuery();

	List<Test> Cquery(String pos);

}
