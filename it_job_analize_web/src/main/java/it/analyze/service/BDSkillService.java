package it.analyze.service;

import com.jt.common.vo.SysResult;

public interface BDSkillService {

	/**
	 * 查询商品详情，并统计相应的职位所需要的技能与职位数
	 * @return
	 */
	SysResult queryDesc();

}
