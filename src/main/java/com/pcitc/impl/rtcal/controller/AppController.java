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
import com.pcitc.impl.rtcal.service.AppService;

import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;
/**
 * @ClassName: AppController
 * @Description: 业务应用集合
 * @author
 * 
 * @date 2016年8月29日 下午3:49:55
 */
@RestController
@RequestMapping(value = "/realTimeCalcService", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AppController {
	@Autowired
	private AppService appService;


	/**
	 * @Title: GetApps
	 * @Description: 查询-查询所有数据
	 * @return CommonResult 返回类型
	 * @throws Exception
	 */

	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = "/apps", method = RequestMethod.GET)
	public String getApps(HttpServletRequest request) throws Exception {
		PcitcLogger.logger.info("getApps is start");
		String collecionStr = null;
		List<com.pcitc.impl.rtcal.model.App> listApp = new ArrayList<>();
		//获取codelist参数
		String codeList = request.getParameter("$codeList") == null ? null : request.getParameter("$codeList").trim();
		try {
			//校验codelist
			List<String> codes = checkInputCodelist(codeList);
			//根据codelist获取数据，如果为空，则获取所有
			List<com.pcitc.impl.rtcal.entity.App> appList = appService.getApps(codes);
			//将entity转换位model
			listApp = ObjectConverter.listConverter(appList, App.class);
			//拼接href
			for (com.pcitc.impl.rtcal.model.App apps : listApp) {
				List<String> appLinks = new ArrayList<>();
				// appLinks.add("/"+apps.getCode());
				apps.setLinks(appLinks);
				apps.setHref("/" + apps.getAppCode());
			}
			//转换为collectionJson格式
			collecionStr = RestfulTool.buildCollection(listApp, request.getRequestURI(), App.class);
			PcitcLogger.logger.info("getApps is end");
		} catch (BusiException e) {
			// 记录日志
			PcitcLogger.logger.error("getApps is error", e);
			// 构造错误返回值
			collecionStr = RestfulTool.buildCollection(new ErrorInfo("", e.getCode().toString(), e.getMessage()),
					request.getRequestURI());
		}
		return collecionStr;
	}
	/**
	 * @Title: getAppByCode
	 * @Description: app查询-条件查询code
	 * @param request
	 * @param code
	 * @throws Exception
	 * @return String 返回类型
	 */
	@RequestMapping(value = "/apps/{code}", method = RequestMethod.GET)
	public String getAppByCode(HttpServletRequest request, @PathVariable String code) throws Exception {
		PcitcLogger.logger.info("getAppByCode is start");
		String collecionStr = null;
		String codetrim = null;
		com.pcitc.impl.rtcal.model.App apps = new App();
		List<com.pcitc.impl.rtcal.model.App> listApp = new ArrayList<>();
		try {
			codetrim = code.trim();
			// 输入校验
			checkInput(codetrim);
			//通过code获取唯一数据
			com.pcitc.impl.rtcal.entity.App app = appService.getAppByCode(codetrim);
			//将entity转换位model
			apps = ObjectConverter.entityConverter(app, App.class);
			//判断是否存在code
			if (apps != null) {
				apps.setHref("/" + apps.getAppCode());
				// 构造collection
				listApp.add(apps);
				collecionStr = RestfulTool.buildCollection(listApp, request.getRequestURI(), App.class);
				
			} else {
				collecionStr = RestfulTool.buildCollection(new ErrorInfo("", "", CheckPrompt.APP_CODE_CHECKMATCHER),
						request.getRequestURI());
			}
			PcitcLogger.logger.info("getAppByCode is end");
		} catch (BusiException e) {
			// 记录日志
			PcitcLogger.logger.error("getAppByCode is error", e);
			// 构造错误返回值
			collecionStr = RestfulTool.buildCollection(new ErrorInfo("", e.getCode().toString(), e.getMessage()),
					request.getRequestURI());
		}

		return collecionStr;
	}

	/**
	 * @Title: AddApp
	 * @Description: 添加App
	 * @param appModel
	 * @return void 返回类型
	 */
	@RequestMapping(value = "/apps", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public String addApp(HttpServletRequest request, @RequestBody String collectionStr) throws Exception {
		PcitcLogger.logger.info("addApp is start");
		String collecionStr = null;
		try {
			int count = 0;
			//将json格式转换为业务类
			List<App> app1 = RestfulTool.toResourceRep(collectionStr, com.pcitc.impl.rtcal.model.App.class);
			for(App ap : app1){
				// 空格校验
				App app = getStringNoBlankAdd(ap);
	//			Date date = new Date();
	//			//获取当前时间为创建时间
	//			app.setCreateTime(date);
	//			app.setUpdateTime(date);
				// 非空校验
				checkInput(app);
				com.pcitc.impl.rtcal.entity.App appEntity = EntityUtil.model2Entity(app);
				// 创建app
				appService.addApp(appEntity);
				count++;
				//返回成功信息
				//collecionStr=request.getRequestURI() + "/" + app.getAppCode();
			}
			return Integer.valueOf(count).toString();
		} catch (BusiException e) {
			// 记录日志
			PcitcLogger.logger.error("addApp is error", e);
			// 构造错误返回值
			collecionStr = RestfulTool.buildCollection(new ErrorInfo("", e.getCode().toString(), e.getMessage()),
					request.getRequestURI());
		}
		return collecionStr;
	}

	/**
	 * @Title: updateAppName
	 * @Description: 修改App
	 * @param code
	 * @return String 返回类型
	 * @throws BusiException
	 */
	@RequestMapping(value = "/apps/{code}", method = RequestMethod.PUT)
	public String updateAppName(HttpServletRequest request, @PathVariable String code,
			@RequestBody String collectionStr) throws Exception {
		PcitcLogger.logger.info("updateAppName is start");
		String collecionStr = "";
		try {
			code = code == null ? null : code.trim();
			List<com.pcitc.impl.rtcal.model.App> app1 = RestfulTool.toResourceRep(collectionStr,
					com.pcitc.impl.rtcal.model.App.class);
//			//不能修改code
//			if(app1.getAppCode()!=null&&!"".equals(app1.getAppCode().trim())&&!code.equals(app1.getAppCode())){
//				throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.APP_CODE_NOT);
//			}
			for(com.pcitc.impl.rtcal.model.App ap : app1){
			// 空格校验
			App app = getStringNoBlankUpdate(ap);
			app.setAppCode(code);
			// 非空校验
			checkInputUp(app);
			com.pcitc.impl.rtcal.entity.App appEntity = EntityUtil.model2Entity(app);
			//更新APP
			appService.updateApp(code, appEntity);
			PcitcLogger.logger.info("updateAppName is end");
			//返回成功信息
			collecionStr+=request.getRequestURI()+"/r/n";
			}
		} catch (BusiException e) {
			// 记录日志
			PcitcLogger.logger.error("updateAppName is error", e);
			// 构造错误返回值
			collecionStr = RestfulTool.buildCollection(new ErrorInfo("", e.getCode().toString(), e.getMessage()),
					request.getRequestURI());
		}
		return collecionStr;
	}

	/**
	 * @Title: DeleteApp
	 * @Description: 删除-根据条件删除
	 * @param code
	 *            唯一条件
	 * @return void 返回类型
	 * @throws BusiException
	 */
	@RequestMapping(value = "/apps/{code}", method = RequestMethod.DELETE)
	public String deleteApp(HttpServletRequest request, @PathVariable String code) throws BusiException {
		PcitcLogger.logger.info("deleteApp is start");
		String collecionStr = null;
		//去除前后空格
		String codeVal = code == null ? null : code.trim();
		try {
			//非空校验
			checkInput(codeVal);
			//删除app
			appService.deleteApp(codeVal);
			//返回成功信息
			//collecionStr=request.getRequestURI() ;
			PcitcLogger.logger.info("deleteApp is end");
		} catch (BusiException e) {
			// 记录日志
			PcitcLogger.logger.error("deleteApp is error", e);
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
	public static void checkInputUp(App appModel) throws BusiException {
		
		if (CheckUtil.checkNameIsNull(appModel.getAppName())) {
			throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.APP_NAME_NULL);
		}
		if(CheckUtil.checkCrtMntNameIsNull(appModel.getUpdateUser())){
			throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.APP_USER_NAME_NULL);
		}
		Matcher checkMatcher = CheckUtil.checkMatcher(appModel.getAppCode());
		if (checkMatcher.find() == false) {
			throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.CHECKMATCHER);
		}
		
	}

	/**
	 * 新增前数据校验
	 * 
	 * @param appModel
	 * @throws BusiException
	 */
	public void checkInput(App appModel) throws BusiException {

		if (CheckUtil.checkAppCodeIsNull(appModel.getAppCode())) {
			throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.APP_CODE_NULL);
		}
		if (CheckUtil.checkNameIsNull(appModel.getAppName())) {
			throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.APP_NAME_NULL);
		}
		if (CheckUtil.checkCrtMntNameIsNull(appModel.getCreateUser())) {
			throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.APP_USER_NAME_NULL);
		}
		if (CheckUtil.checkCrtMntNameIsNull(appModel.getUpdateUser())) {
			throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.APP_USER_NAME_NULL);
		}
		Matcher checkMatcher = CheckUtil.checkMatcher(appModel.getAppCode());
		if (checkMatcher.find() == false) {
			throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.CHECKMATCHER);
		}
		
	}

	/**
	 * @Title: getStringNoBlank
	 * @Description: 校验前后空格
	 * @param appModel
	 * @return App 返回类型
	 */
	public App getStringNoBlankAdd(App appModel) throws BusiException {
		App app = new App();
	
		String code = appModel.getAppCode();
		String name = appModel.getAppName();
		String createUser = appModel.getCreateUser();
		String updateUser = appModel.getUpdateUser();
		if (code != null && name != null && createUser != null && updateUser!=null&&
				!"".equals(name.trim()) && !"".equals(createUser.trim())&&!"".equals(code.trim()) &&!"".equals(updateUser.trim())) {
			
			app.setCreateUser(createUser.trim());
			app.setUpdateUser(updateUser.trim());
			app.setAppCode(code.trim());
			app.setAppName(name.trim());
		} else {
			throw new BusiException(ErrorCodeEnum.M001, CheckPrompt.INSERT_NOTNULL);
		}
		
		if(!app.getCreateUser().equals(app.getUpdateUser())){
			throw new BusiException(ErrorCodeEnum.M001, CheckPrompt.CRE_MNT_NAME);
		}
		return app;
	}

	/**
	 * @Title: getStringNoBlank
	 * @Description: 校验前后空格
	 * @param appModel
	 * @return App 返回类型
	 */
	public App getStringNoBlankUpdate(App appModel) throws BusiException {
		App app = new App();
		//String code = appModel.getAppCode();
		String name = appModel.getAppName();
		String updateUser = appModel.getUpdateUser();
		if( name != null && updateUser != null 
				&& !"".equals(name.trim()) && !"".equals(updateUser.trim())){
			//app.setAppCode(code.trim());
			app.setAppName(name.trim());
			app.setUpdateUser(updateUser.trim());
		}else{
			throw new BusiException(ErrorCodeEnum.M001, CheckPrompt.UPDETE_NOTNULL);
		}
		return app;
	}

	/**
	 * @Title: checkInput
	 * @Description: 查询校验code
	 * @param code
	 * @throws BusiException
	 * @return void 返回类型
	 */
	public void checkInput(String code) throws BusiException {
		if (CheckUtil.checkAppCodeIsNull(code)) {
			throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.APP_CODE_NULL);
		}
		Matcher checkMatcher = CheckUtil.checkMatcher(code);
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
