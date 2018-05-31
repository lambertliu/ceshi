package it.analyze.pojo;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="it_position")
//@Table(name="T1")
public class Position {
		
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	//序号
	private int id;
	//唯一id
	private String uuid;
	//职位类别id
	private int pid;
	//人数
	private int num;
	//工作地点
	private String city;
	//职位名称
	private String positionName;
	//薪资
	private int minSalary;
	private int maxSalary;
	public int getMinSalary() {
		return minSalary;
	}
	public void setMinSalary(int minSalary) {
		this.minSalary = minSalary;
	}
	public int getMaxSalary() {
		return maxSalary;
	}
	public void setMaxSalary(int maxSalary) {
		this.maxSalary = maxSalary;
	}
	//发布时间
	private Date creatTime;
	//工作经验，1-不限，2-无经验，3-1年以下，4-1-2年，5-3-5年，6-5-10年，7-10年以上',
	private byte experience;
	//学历
	private String edu;
	//数据来源网址
	private String website;
	//公司
	private String company;
	//工作性质
	private String jobType; 
	//反馈率
	private int responseRate;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public int getPid() {
		return pid;
	}
	public void setPid(int pid) {
		this.pid = pid;
	}
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
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	
	public Date getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	public byte getExperience() {
		return experience;
	}
	public void setExperience(byte experience) {
		this.experience = experience;
	}
	public String getEdu() {
		return edu;
	}
	public void setEdu(String edu) {
		this.edu = edu;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	public int getResponseRate() {
		return responseRate;
	}
	public void setResponseRate(int responseRate) {
		this.responseRate = responseRate;
	}

}
