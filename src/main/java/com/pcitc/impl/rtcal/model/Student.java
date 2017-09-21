package com.pcitc.impl.rtcal.model;

import java.io.Serializable;

import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

@SuppressWarnings("serial")
@ResourceContract(ReadOnly = false)
@QueryContract(href = "", rel = "search", prompt = "查询")
public class Student extends BaseResRep implements Serializable {
	
	//应用id
	// Student的唯一标识，不重复。
	private String id;
	//Student的姓名
	private String name;
	// Student的年龄。
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
