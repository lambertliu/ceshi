package it.analyze.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "_cat")
public class ITCat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	// 职位
	private String typeName;

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
