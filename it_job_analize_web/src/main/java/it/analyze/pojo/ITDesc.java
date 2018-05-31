package it.analyze.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="it_desc")
public class ITDesc {
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private int id;
	 //福利
	 private String welfare;
	 //职位描述
	 private String positDesc;
	 private int jobId;
	
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public String getWelfare() {
		return welfare;
	}
	public void setWelfare(String welfare) {
		this.welfare = welfare;
	}
	public String getPositDesc() {
		return positDesc;
	}
	public void setPositDesc(String positDesc) {
		this.positDesc = positDesc;
	}
}
