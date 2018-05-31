package it.analyze.pojo;

public class Test {

	private String city;
	private int num;
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	@Override
	public String toString() {
		return "Test [city=" + city + ", num=" + num + "]";
	}
	
}
