package it.analyze.service;

import com.jt.common.vo.SysResult;

public interface CitySalaryService {

	/**
	 * 查询职位学历工作经验对应的薪资
	 * @param position1
	 * @param years
	 * @param education
	 * @return
	 */
	SysResult CSQuery(String position, byte experience, String education);

	SysResult CSSQuery(String position, byte experience, String education);

}
