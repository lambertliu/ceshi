package it.analyze.pojo;

public class PositionCount implements Comparable<PositionCount>{
	//相应技能
	private int count;
	//有多少个招聘信息是需要这个技能的
	private String position;

	public PositionCount(int count,String position) {
		this.count = count;
		this.position = position;
	}
	
	
	
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}



	@Override
	public int compareTo(PositionCount o) {
		int i = o.getCount()-this.getCount();		
		return i;
	
	}


}
