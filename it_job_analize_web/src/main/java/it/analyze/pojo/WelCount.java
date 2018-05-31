package it.analyze.pojo;

public class WelCount {
	private String welfare;
	private int count;
	public WelCount(int count, String welfare) {
		this.count = count;
		this.welfare = welfare;
	}
	public String getWelfare() {
		return welfare;
	}
	public void setWelfare(String welfare) {
		this.welfare = welfare;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
