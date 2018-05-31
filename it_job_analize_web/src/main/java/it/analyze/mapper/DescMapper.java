package it.analyze.mapper;



public interface DescMapper{

	/**
	 * 查询所需技能
	 * @param position
	 * @return
	 */
	int selectPosition(String position);

	/**
	 * 查询福利待遇
	 * @param welfare
	 * @return
	 */
	int selectWelfare(String wel);

	

}
