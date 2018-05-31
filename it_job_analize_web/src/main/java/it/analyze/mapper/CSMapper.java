package it.analyze.mapper;

import java.util.List;
import java.util.Map;

import com.jt.common.mapper.MyMapper;

import it.analyze.pojo.Position;

public interface CSMapper extends MyMapper<Position>{

	List<Map<String,Object>> selectaaa(Position p);

	List<Map<String, Object>> selectbbb(Position p);

}
