package it.analyze.pojo;

public class CityPojo {
	private int[] num;
	private String type_name;

	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public CityPojo(int i) {
		num = new int[i];
	}
	public int[] getNum() {
		return num;
	}
	public void setNum(int[] num) {
		this.num = num;
	}

}
