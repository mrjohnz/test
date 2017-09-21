package com.pcitc.impl.rtcal.dao;

import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.impl.rtcal.pojo.Student;

public interface StudentDao extends JpaRepository<Student, Long>{
	
	/**
	 * @Title: getStudents
	 * @Description: 查询所有Student
	 * @return List<Student>
	 */
	@Query("from Student")
	List<com.pcitc.impl.rtcal.pojo.Student> getStudents();
	
	/**
	 * @Title: getStudentById
	 * @Description: 根据条件查询Student
	 * @param id 唯一条件
	 * @return Student
	 */
	@Query("from Student a where id = :id")
	com.pcitc.impl.rtcal.pojo.Student getStudentById(@Param("id") String id);
	
	/**
	 * @Description: 判断是否存在指定Student
	 * @param code
	 * @return App
	 */
	@Query("from Student a where id = :id")
	com.pcitc.impl.rtcal.pojo.Student queryById(@Param("id") String id);
	
	/**
	 * @Title: updateStudent
	 * @Description: 更新Student
	 * @param id
	 * @param name
	 * @param age
	 */
	@Modifying
	@Transactional
	@Query(" update Student set name = :name, age = :age where id = :id")
	void updateStudentById(@Param("id") String id, 
			@Param("name") String name, 
			@Param("age") String age);
	
	/**
	 * @Title: deleteStudent
	 * @Description: 删除指定Student
	 * @param id 唯一条件
	 */
	@Transactional
	@SQLDelete(sql = "delete from Student where id = :id")
	void deleteStudentById(@Param("id") String id);

	@Query("from Student a where id in (:id)")
	List<com.pcitc.impl.rtcal.pojo.Student> getStudentsById(@Param("id") List<String> ids);
}
