package com.pcitc.impl.rtcal.entity;

import java.io.Serializable;

import pcitc.imp.common.ettool.baseresrep.BaseResRep;

public class Student extends BaseResRep implements Serializable {

	//学生id，唯一标识
	private String id;
	//学生姓名
	private String name;
	//学生年龄
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
