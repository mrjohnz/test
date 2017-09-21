package com.pcitc.impl.rtcal.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pcitc.impl.rtcal.common.CheckPrompt;
import com.pcitc.impl.rtcal.common.CheckUtil;
import com.pcitc.impl.rtcal.common.EntityUtil;
import com.pcitc.impl.rtcal.common.ErrorCodeEnum;
import com.pcitc.impl.rtcal.common.PcitcLogger;
import com.pcitc.impl.rtcal.exception.BusiException;
import com.pcitc.impl.rtcal.model.App;
import com.pcitc.impl.rtcal.model.Student;
import com.pcitc.impl.rtcal.service.StudentService;

import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

@RestController
@RequestMapping(value = "/a", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class StudentController {
	@Autowired
	private StudentService studentService;
	
	/**
	 * @Title: GetStudents
	 * @Description: 查询-查询所有数据
	 * @return CommonResult 返回类型
	 * @throws Exception
	 */

	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = "/students", method = RequestMethod.GET)
	public String getStudents(HttpServletRequest request) throws Exception {
		PcitcLogger.logger.info("getStudents is start");
		String collecionStr = null;
		List<com.pcitc.impl.rtcal.model.Student> listStudent = new ArrayList<>();
		//获取idlist参数
		String idList = request.getParameter("$idList") == null ? null : request.getParameter("$idList").trim();
		try {
			//校验idList
			List<String> ids = checkInputCodelist(idList);
			//根据idList获取数据，如果为空，则获取所有
			List<com.pcitc.impl.rtcal.entity.Student> studentList = studentService.getStudents(ids);
			//将entity转换位model
			listStudent = ObjectConverter.listConverter(studentList, Student.class);
			//拼接href
			for (com.pcitc.impl.rtcal.model.Student students : listStudent) {
				List<String> studentLinks = new ArrayList<>();
				// appLinks.add("/"+apps.getCode());
				students.setLinks(studentLinks);
				students.setHref("/" + students.getId());
			}
			//转换为collectionJson格式
			collecionStr = RestfulTool.buildCollection(listStudent, request.getRequestURI(), Student.class);
			PcitcLogger.logger.info("getStudents is end");
		} catch (BusiException e) {
			// 记录日志
			PcitcLogger.logger.error("getStudents is error", e);
			// 构造错误返回值
			collecionStr = RestfulTool.buildCollection(new ErrorInfo("", e.getCode().toString(), e.getMessage()),
					request.getRequestURI());
		}
		return collecionStr;
	}
	
	/**
	 * @Title: getStudentById
	 * @Description: student查询-条件查询ID
	 * @param request
	 * @param id
	 * @throws Exception
	 * @return String 返回类型
	 */
	@RequestMapping(value = "/students/{id}", method = RequestMethod.GET)
	public String getStudentById(HttpServletRequest request, @PathVariable String id) throws Exception {
		PcitcLogger.logger.info("getStudentById is start");
		String collecionStr = null;
		String idtrim = null;
		com.pcitc.impl.rtcal.model.Student students = new Student();
		List<com.pcitc.impl.rtcal.model.Student> listStudent = new ArrayList<>();
		try {
			idtrim = id.trim();
			// 输入校验
			checkInput(idtrim);
			//通过id获取唯一数据
			com.pcitc.impl.rtcal.entity.Student student = studentService.getStudentById(idtrim);
			//将entity转换位model
			students = ObjectConverter.entityConverter(student, Student.class);
			//判断是否存在id
			if (students != null) {
				students.setHref("/" + students.getId());
				// 构造collection
				listStudent.add(students);
				collecionStr = RestfulTool.buildCollection(listStudent, request.getRequestURI(), Student.class);
				
			} else {
				collecionStr = RestfulTool.buildCollection(new ErrorInfo("", "", CheckPrompt.APP_CODE_CHECKMATCHER),
						request.getRequestURI());
			}
			PcitcLogger.logger.info("getStudentById is end");
		} catch (BusiException e) {
			// 记录日志
			PcitcLogger.logger.error("getStudentById is error", e);
			// 构造错误返回值
			collecionStr = RestfulTool.buildCollection(new ErrorInfo("", e.getCode().toString(), e.getMessage()),
					request.getRequestURI());
		}

		return collecionStr;
	}
	
	
	/**
	 * @Title: AddStudent
	 * @Description: 添加Student
	 * @param studentModel
	 * @return void 返回类型
	 */
	@RequestMapping(value = "/students", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public String addStudent(HttpServletRequest request, @RequestBody String collectionStr) throws Exception {
		PcitcLogger.logger.info("addStudent is start");
		String collecionStr = null;
		try {
			int count = 0;
			//将json格式转换为业务类
			List<Student> student1 = RestfulTool.toResourceRep(collectionStr, com.pcitc.impl.rtcal.model.Student.class);
			for(Student stu : student1){
				// 空格校验
				Student student =  getStringNoBlankAdd(stu);
	//			Date date = new Date();
	//			//获取当前时间为创建时间
	//			app.setCreateTime(date);
	//			app.setUpdateTime(date);
				// 非空校验
				checkInput(student);
				com.pcitc.impl.rtcal.entity.Student studentEntity = EntityUtil.model2EntityStudent(student);
				// 创建student
				studentService.addStudent(studentEntity);
				count++;
				//返回成功信息
				//collecionStr=request.getRequestURI() + "/" + app.getAppCode();
			}
			return Integer.valueOf(count).toString();
		} catch (BusiException e) {
			// 记录日志
			PcitcLogger.logger.error("addStudent is error", e);
			// 构造错误返回值
			collecionStr = RestfulTool.buildCollection(new ErrorInfo("", e.getCode().toString(), e.getMessage()),
					request.getRequestURI());
		}
		return collecionStr;
	}
	/**
	 * @Title: updateStudentName
	 * @Description: 修改Student
	 * @param id
	 * @return String 返回类型
	 * @throws BusiException
	 */
	@RequestMapping(value = "/students/{id}", method = RequestMethod.PUT)
	public String updateStudentName(HttpServletRequest request, @PathVariable String id,
			@RequestBody String collectionStr) throws Exception {
		PcitcLogger.logger.info("updateAppName is start");
		String collecionStr = "";
		try {
			id = id == null ? null : id.trim();
			List<com.pcitc.impl.rtcal.model.Student> student1 = RestfulTool.toResourceRep(collectionStr,
					com.pcitc.impl.rtcal.model.Student.class);
//			//不能修改code
//			if(app1.getAppCode()!=null&&!"".equals(app1.getAppCode().trim())&&!code.equals(app1.getAppCode())){
//				throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.APP_CODE_NOT);
//			}
			for(com.pcitc.impl.rtcal.model.Student stu : student1){
			// 空格校验
			Student student = getStringNoBlankUpdate(stu);
			student.setId(id);
			// 非空校验
			checkInputUp(student);
			com.pcitc.impl.rtcal.entity.Student studentEntity = EntityUtil.model2EntityStudent(student);
			//更新APP
			studentService.updateStudent(id, studentEntity);
			PcitcLogger.logger.info("updateStudentName is end");
			//返回成功信息
			collecionStr+=request.getRequestURI()+"/r/n";
			}
		} catch (BusiException e) {
			// 记录日志
			PcitcLogger.logger.error("updateStudentName is error", e);
			// 构造错误返回值
			collecionStr = RestfulTool.buildCollection(new ErrorInfo("", e.getCode().toString(), e.getMessage()),
					request.getRequestURI());
		}
		return collecionStr;
	}
	
	/**
	 * @Title: DeleteStudent
	 * @Description: 删除-根据条件删除
	 * @param id
	 *            唯一条件
	 * @return void 返回类型
	 * @throws BusiException
	 */
	@RequestMapping(value = "/students/{id}", method = RequestMethod.DELETE)
	public String deleteStudent(HttpServletRequest request, @PathVariable String id) throws BusiException {
		PcitcLogger.logger.info("deleteStudent is start");
		String collecionStr = null;
		//去除前后空格
		String idVal = id == null ? null : id.trim();
		try {
			//非空校验
			checkInput(idVal);
			//删除app
			studentService.deleteStudent(idVal);
			//返回成功信息
			//collecionStr=request.getRequestURI() ;
			PcitcLogger.logger.info("deleteStudent is end");
		} catch (BusiException e) {
			// 记录日志
			PcitcLogger.logger.error("deleteStudent is error", e);
			// 构造错误返回值
			collecionStr = RestfulTool.buildCollection(new ErrorInfo("", e.getCode().toString(), e.getMessage()),
					request.getRequestURI());
		}
		return collecionStr;
	}
	
	
	/**
	 * @Title: checkInputUp
	 * @Description: 修改时非空校验
	 * @param appModel
	 * @throws BusiException
	 */
	public static void checkInputUp(Student studentModel) throws BusiException {
		
		if (CheckUtil.checkNameIsNull(studentModel.getName())) {
			throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.APP_NAME_NULL);
		}
		if(CheckUtil.checkCrtMntNameIsNull(studentModel.getAge())){
			throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.APP_USER_NAME_NULL);
		}
		Matcher checkMatcher = CheckUtil.checkMatcher(studentModel.getId());
		if (checkMatcher.find() == false) {
			throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.CHECKMATCHER);
		}
		
	}
	
	/**
	 * 新增前数据校验
	 * 
	 * @param studentModel
	 * @throws BusiException
	 */
	public void checkInput(Student studentModel) throws BusiException {

		if (CheckUtil.checkAppCodeIsNull(studentModel.getId())) {
			throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.APP_CODE_NULL);
		}
		if (CheckUtil.checkNameIsNull(studentModel.getName())) {
			throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.APP_NAME_NULL);
		}
		if (CheckUtil.checkCrtMntNameIsNull(studentModel.getAge())) {
			throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.APP_USER_NAME_NULL);
		}
		
	}
	
	/**
	 * @Title: getStringNoBlank
	 * @Description: 校验前后空格
	 * @param appModel
	 * @return App 返回类型
	 */
	public Student getStringNoBlankAdd(Student studentModel) throws BusiException {
		Student student = new Student();
		
		String id = studentModel.getId();
		String name = studentModel.getName();
		String age = studentModel.getAge();
		
		if (id != null && name != null && age != null &&
				!"".equals(id.trim()) && !"".equals(name.trim())&&!"".equals(age.trim())) {
			
			student.setId(id);
			student.setName(name);
			student.setAge(age);
		} else {
			throw new BusiException(ErrorCodeEnum.M001, CheckPrompt.INSERT_NOTNULL);
		}
		
		/*if(!student.getCreateUser().equals(app.getUpdateUser())){
			throw new BusiException(ErrorCodeEnum.M001, CheckPrompt.CRE_MNT_NAME);
		}*/
		return student;
	}

	/**
	 * @Title: getStringNoBlank
	 * @Description: 校验前后空格
	 * @param appModel
	 * @return App 返回类型
	 */
	public Student getStringNoBlankUpdate(Student studentModel) throws BusiException {
		Student student = new Student();
		
		String name = studentModel.getName();
		String age = studentModel.getAge();
		
		if( name != null && age != null 
				&& !"".equals(name.trim()) && !"".equals(age.trim())){
			//app.setAppCode(code.trim());
			student.setName(name.trim());
			student.setAge(age.trim());
		}else{
			throw new BusiException(ErrorCodeEnum.M001, CheckPrompt.UPDETE_NOTNULL);
		}
		return student;
	}
	
	/**
	 * @Title: checkInput
	 * @Description: 查询校验code
	 * @param code
	 * @throws BusiException
	 * @return void 返回类型
	 */
	public void checkInput(String id) throws BusiException {
		if (CheckUtil.checkAppCodeIsNull(id)) {
			throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.APP_CODE_NULL);
		}
		Matcher checkMatcher = CheckUtil.checkMatcher(id);
		if (checkMatcher.find() == false) {
			throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.CHECKMATCHER);
		}
	}
	
	/**
	 * @return
	 * @Title: checkCodeList
	 * @Description: 校验codeList
	 */
	public static List<String> checkCodeList(String codeList) throws BusiException {
		List<String> list = new ArrayList<>();
		String[] strs = codeList.split(",");
		for (int i = 0; i < strs.length; i++) {
			// 空格校验
			String code = strs[i].trim();
			// 非空校验
			boolean checkStringIsNull = CheckUtil.checkAppCodeIsNull(code);
			if (checkStringIsNull) {
				continue;
			}
			// 特殊字符判断
			if (CheckUtil.characterFilter(code)) {
				continue;
			}
		}
		Collections.addAll(list, strs);

		return list;
	}
	/**
	 * @Title: checkInput
	 */
	public static List<String> checkInputCodelist(String codeList) throws BusiException {
		List<String> list = new ArrayList<>();

		if (CheckUtil.checkStringIsNotNull(codeList)) {
			// 校验codeList
			list = checkCodeList(codeList);
		}
		return list;
	}
}
