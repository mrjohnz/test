package com.pcitc.impl.rtcal.service;

import java.util.List;

import com.pcitc.impl.rtcal.entity.Student;
import com.pcitc.impl.rtcal.exception.BusiException;

public interface StudentService {
	
	/**
	 * @Title: getStudents 
	 * @Description: 查询-查询所有
	 * @throws BusiException
	 * @return List<Student>    返回类型 
	 */
	List<Student> getStudents(List<String> ids) throws BusiException;
	
	/**
	 * @Title: getStudentById 
	 * @Description: 查询-条件查询id 
	 * @param id
	 * @throws BusiException
	 * @return com.pcitc.impl.rtcal.entity.Student    返回类型 
	 */
	com.pcitc.impl.rtcal.entity.Student getStudentById(String id) throws BusiException;
	
	/**
	 * @Title: addStudent 
	 * @Description: 添加
	 * @param studentEntity
	 * @throws BusiException
	 */
	void addStudent(com.pcitc.impl.rtcal.entity.Student studentEntity) throws BusiException;
	
	/**
	 * @Title: updateStudent 
	 * @Description: 修改
	 * @param id
	 * @param studentEntity
	 * @throws BusiException
	 */
	void updateStudent(String id, com.pcitc.impl.rtcal.entity.Student studentEntity) throws BusiException;

	/**
	 * @Title: deleteStudent 
	 * @Description: 删除
	 * @param id
	 * @param appEntity
	 * @throws BusiException
	 */
	void deleteStudent(String id) throws BusiException;
}
