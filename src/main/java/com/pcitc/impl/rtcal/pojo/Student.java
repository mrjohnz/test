package com.pcitc.impl.rtcal.pojo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import pcitc.imp.common.ettool.baseresrep.BaseResRep;


@Entity
@Table(name = "Student")
@DynamicInsert(true)
public class Student extends BaseResRep implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	// Student的唯一标识，不重复。
	@Column(name = "id")
	private String id;

	
	@Column(name = "name")
	private String name;
	// App的年龄。
	@Column(name = "age")
	private String age;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
	

}
