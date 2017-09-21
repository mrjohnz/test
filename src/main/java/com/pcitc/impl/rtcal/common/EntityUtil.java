package com.pcitc.impl.rtcal.common;

import org.springframework.beans.BeanUtils;

import com.pcitc.impl.rtcal.model.App;
import com.pcitc.impl.rtcal.model.Student;

/**
 * model,entity,pojo转换工具类
 * @author haiwen.wang
 *
 */
public class EntityUtil {

	/**
	 * model->entity
	 * @param appModel
	 * @return
	 */
	public static com.pcitc.impl.rtcal.entity.App model2Entity(App appModel) {
		com.pcitc.impl.rtcal.entity.App appEntity = new com.pcitc.impl.rtcal.entity.App();
		BeanUtils.copyProperties(appModel, appEntity);
		return appEntity;
	}
	
	public static com.pcitc.impl.rtcal.entity.Student model2EntityStudent(Student studentModel) {
		com.pcitc.impl.rtcal.entity.Student studentEntity = new com.pcitc.impl.rtcal.entity.Student();
		BeanUtils.copyProperties(studentModel, studentEntity);
		return studentEntity;
	}
	
	/**
	 * entity->pojo
	 * @param appEntity
	 * @return
	 */
	public static com.pcitc.impl.rtcal.pojo.App entity2Pojo(com.pcitc.impl.rtcal.entity.App appEntity) {
		com.pcitc.impl.rtcal.pojo.App appPojo = new com.pcitc.impl.rtcal.pojo.App();
		BeanUtils.copyProperties(appEntity, appPojo);
		return appPojo;
	}
	
	public static com.pcitc.impl.rtcal.pojo.Student entity2PojoStudent(com.pcitc.impl.rtcal.entity.Student studentEntity) {
		com.pcitc.impl.rtcal.pojo.Student studentPojo = new com.pcitc.impl.rtcal.pojo.Student();
		BeanUtils.copyProperties(studentEntity, studentPojo);
		return studentPojo;
	}
	
	/**
	 * entity->model
	 * @param getApps
	 * @return
	 */
	public static App entity2Model(com.pcitc.impl.rtcal.entity.App getApps) {
		com.pcitc.impl.rtcal.model.App appEntity = new com.pcitc.impl.rtcal.model.App();
		BeanUtils.copyProperties(getApps, appEntity);
		return appEntity;
	}
	
	public static Student entity2ModelStudent(com.pcitc.impl.rtcal.entity.Student getStudents) {
		com.pcitc.impl.rtcal.model.Student studentEntity = new com.pcitc.impl.rtcal.model.Student();
		BeanUtils.copyProperties(getStudents, studentEntity);
		return studentEntity;
	}
	
	

}
