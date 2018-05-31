package it.analyze.mapper;

import java.util.List;

import com.jt.common.mapper.MyMapper;

import it.analyze.pojo.CityNum;
import it.analyze.pojo.Position;
import it.analyze.pojo.UrlPo;

public interface PCMapper extends MyMapper<Position>{
	
	/**模糊查询
	 * 查询大数据职位所需对应学历的个数
	 * @param edu
	 * @return
	 */
	int eduQuery(String education);
	/**
	 * 查询City NUm
	 * @return
	 */
	int CNQuery(String city);
	/**
	 * 查
	 * @param pid
	 * @param city
	 * @return
	 */
	List<UrlPo> QueryUrl(Integer pid, String city);
	
	
//	List<Position> getPs(String city);

}
