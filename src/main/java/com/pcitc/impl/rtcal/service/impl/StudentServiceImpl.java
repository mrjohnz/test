package com.pcitc.impl.rtcal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcitc.impl.rtcal.common.CheckPrompt;
import com.pcitc.impl.rtcal.common.EntityUtil;
import com.pcitc.impl.rtcal.common.ErrorCodeEnum;
import com.pcitc.impl.rtcal.common.PcitcLogger;
import com.pcitc.impl.rtcal.dao.StudentDao;
import com.pcitc.impl.rtcal.entity.Student;
import com.pcitc.impl.rtcal.exception.BusiException;
import com.pcitc.impl.rtcal.service.StudentService;

import pcitc.imp.common.ettool.utils.ObjectConverter;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentDao studentDao;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Student> getStudents(List<String> ids) throws BusiException {
		List<com.pcitc.impl.rtcal.pojo.Student> studentList = null;
		List<Student> studentlists = new ArrayList<>();
		PcitcLogger.logger.info("AppServiceImpl  getApps is start");
		try {
			if (ids.size() == 0) {
				studentList = studentDao.getStudents();
			} else {
				studentList = studentDao.getStudentsById(ids);
			}
			studentlists = ObjectConverter.listConverter(studentList, Student.class);
			PcitcLogger.logger.info("StudentServiceImpl  getStudents is end");
		} catch (BusiException e) {
			PcitcLogger.logger.info("StudentServiceImpl  getStudents is error", e);
			throw new BusiException(ErrorCodeEnum.M001, CheckPrompt.APP_CODE_CHECKMATCHER, e);
		} catch (Exception e) {
			PcitcLogger.logger.info("StudentServiceImpl  getStudents is error", e);
			throw new BusiException(ErrorCodeEnum.M001, e.getMessage(), e);
		}

		return studentlists;
	}

	@Override
	public Student getStudentById(String id) throws BusiException {
		com.pcitc.impl.rtcal.pojo.Student student = new com.pcitc.impl.rtcal.pojo.Student();
		Student students = new Student();
		PcitcLogger.logger.info("StudentServiceImpl  getStudentById is start");
		try {
			student = studentDao.getStudentById(id);
			students = ObjectConverter.entityConverter(student, Student.class);
			PcitcLogger.logger.info("StudentServiceImpl  getStudentById is end");
		} catch (BusiException e) {
			PcitcLogger.logger.info("StudentServiceImpl  getStudentById is error");
			throw new BusiException(ErrorCodeEnum.M001, CheckPrompt.APP_CODE_CHECKMATCHER, e);
		} catch (Exception e) {
			PcitcLogger.logger.info("StudentServiceImpl  getStudentById is error");
			throw new BusiException(ErrorCodeEnum.M001, e.getMessage(), e);
		}
		return students;
	}

	@Override
	public void addStudent(Student studentEntity) throws BusiException {
		PcitcLogger.logger.info("StudentServiceImpl  addStudent is start");
		// entity转换为pojo
		com.pcitc.impl.rtcal.pojo.Student studentPojo = EntityUtil.entity2PojoStudent(studentEntity);
		// 校验appcode是否已经存在
		com.pcitc.impl.rtcal.pojo.Student student = studentDao.queryById(studentPojo.getId());
		if (student != null) {
			PcitcLogger.logger.info("StudentServiceImpl  addStudent is error");
			throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.APP_CODE_REPEAT);
		}

		// 创建app
		studentDao.save(studentPojo);
		PcitcLogger.logger.info("StudentServiceImpl  addStudent is end");

	}

	@Override
	public void updateStudent(String id, Student studentEntity) throws BusiException {
		PcitcLogger.logger.info("StudentServiceImpl  updateStudent is start");
		// 校验appcode是否已经存在
		com.pcitc.impl.rtcal.pojo.Student queryById = studentDao.queryById(id);
		if (queryById == null) {
			PcitcLogger.logger.info("StudentServiceImpl  updateStudent is error");
			throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.APP_CODE_NOT_REPEAT);
		}
		// entity转换为pojo
		com.pcitc.impl.rtcal.pojo.Student studentPojo = EntityUtil.entity2PojoStudent(studentEntity);

		studentDao.updateStudentById(id, studentPojo.getName(), studentPojo.getAge());
		PcitcLogger.logger.info("StudentServiceImpl  updateStudent is end");

	}

	@Override
	public void deleteStudent(String id) throws BusiException {

		PcitcLogger.logger.info("StudentServiceImpl  deleteStudent is start");
		// 校验appcode是否已经存在

		com.pcitc.impl.rtcal.pojo.Student queryById = studentDao.queryById(id);
		if (queryById == null) {
			throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.APP_CODE_NOT_REPEAT);
		}else{
			studentDao.deleteStudentById(id);
			PcitcLogger.logger.info("StudentServiceImpl  deleteStudent is end");
		}

		/*// 校验此appCode下是否有配置
		List<RtcalcConfig> list = rtcalcConfigDao.searchConfigByAppCode(codeVal);
		if (list == null || list.size() == 0) {
			appDao.deleteAppByAppCode(codeVal);
			PcitcLogger.logger.info("AppServiceImpl  deleteApp is end");
		} else {
			PcitcLogger.logger.info("AppServiceImpl  deleteApp is error");
			throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.APP_CODE_REPEAT_CONFIG);
		}*/
	

	}

}
