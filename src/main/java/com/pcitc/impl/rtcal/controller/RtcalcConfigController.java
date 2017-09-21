package com.pcitc.impl.rtcal.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.pcitc.impl.rtcal.common.ErrorCodeEnum;
import com.pcitc.impl.rtcal.common.PcitcLogger;
import com.pcitc.impl.rtcal.common.StringUtil;
import com.pcitc.impl.rtcal.exception.BusiException;
import com.pcitc.impl.rtcal.model.RtcalcConfig;
import com.pcitc.impl.rtcal.model.RtcalcConfigS;
import com.pcitc.impl.rtcal.service.RtcalcConfigService;

import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

@RestController
@RequestMapping(value = "/realTimeCalcService/apps/{appCode}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RtcalcConfigController {
	
	private static final Logger log = LoggerFactory.getLogger(RtcalcConfigController.class);
	@Autowired
	private RtcalcConfigService rtcalcConfigService;
	private boolean dflag;
	
	/**
	 * 查询-所有/按条件查询
	 * @param request
	 * @param appCode
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/configs", method = RequestMethod.GET)
	public String getConfigByNodeId(HttpServletRequest request,@PathVariable String appCode) throws Exception {
		String collecionStr = null;
		List<com.pcitc.impl.rtcal.model.RtcalcConfig> listConfig = new ArrayList<>();
		// 空格校验
		String stringAppCode = appCode == null ? null : appCode.trim();
		// 非空校验 
		checkInput(stringAppCode);
		checkStrLength(stringAppCode,"appcode",50);
		//获取查询参数								  
		String nodeIdList = request.getParameter("$nodeIdList") == null ? null : request.getParameter("$nodeIdList").trim();
		List<Integer> nodeList = null;
		try {
			if(nodeIdList != null && !"".equals(nodeIdList.trim()))
				nodeList = checkCodeList(nodeIdList);
			//查询
			List<com.pcitc.impl.rtcal.pojo.RtcalcConfig> appList = rtcalcConfigService.searchConfig(stringAppCode,nodeList);
			listConfig = ObjectConverter.listConverter(appList, RtcalcConfig.class);
			//拼接links
			for(com.pcitc.impl.rtcal.model.RtcalcConfig rc : listConfig){
				List<String> rcLinks = new ArrayList<>();
				rcLinks.add("/configId");
				rc.setLinks(rcLinks);
			}
				collecionStr = RestfulTool.buildCollection(listConfig,request.getRequestURI(),RtcalcConfig.class);
			
		} catch (BusiException e) {
			// 记录日志
			PcitcLogger.logger.error("PcitcLogger is error", e);
			// 构造错误返回值
			collecionStr = RestfulTool.buildCollection(new ErrorInfo("", e.getCode().toString(), e.getMessage()),
					request.getRequestURI());
		}

		return collecionStr;
	}
	
	/**
	 * @Title: getConfig
	 * @Description: 查询-指定条件查询
	 * @param request
	 * @param appCode
	 * @param configId
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/configs/{configId}", method = RequestMethod.GET)
	public String getConfig(HttpServletRequest request,@PathVariable String appCode,@PathVariable String configId) throws Exception {
		String collecionStr = null;
		List<com.pcitc.impl.rtcal.model.RtcalcConfig> listConfig = new ArrayList<>();
		// 空格校验
		String stringAppCode = appCode == null ? null : appCode.trim();
		// 非空校验 
		checkInput(stringAppCode);
		checkStrLength(stringAppCode,"appcode",50);
		try { 
			configId = configId.trim();
			//校验id
			checkId(configId);
			//根基configId查询唯一配置信息
			List<com.pcitc.impl.rtcal.pojo.RtcalcConfig> appList = rtcalcConfigService.searchConfigByACCI(stringAppCode,configId);
			//将entity转换为model
			listConfig = ObjectConverter.listConverter(appList, RtcalcConfig.class);
			for(com.pcitc.impl.rtcal.model.RtcalcConfig rc : listConfig){
				List<String> rcLinks = new ArrayList<>();
				rcLinks.add("/configId");
				rc.setLinks(rcLinks);
			}
			collecionStr = RestfulTool.buildCollection(listConfig,request.getRequestURI(),RtcalcConfig.class);
		} catch (BusiException e) {
			// 记录日志
			PcitcLogger.logger.error("getConfig is error", e);
			// 构造错误返回值
			collecionStr = RestfulTool.buildCollection(new ErrorInfo("", e.getCode().toString(), e.getMessage()),
					request.getRequestURI());
		}

		return collecionStr;
	}
	
	/**
	 * @Title: deleteRtcalcConfig
	 * @Description: 删除-指定条件删除
	 * @param request
	 * @param configId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/configs/{configId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	public String deleteRtcalcConfig(HttpServletRequest request,@PathVariable String appCode,@PathVariable(value = "configId")  String configId) throws Exception {
		log.debug("*** RtcalcConfigController START deleteRtcalcConfig ***");
		String collecionStr = null;
		// 空格校验
		String stringAppCode = appCode == null ? null : appCode.trim();
		// 非空校验 
		checkInput(stringAppCode);
		checkStrLength(stringAppCode,"appcode",50);
		String codeVal = configId == null ? null : configId.trim();
		try {
			//校验id
			checkId(codeVal);
			rtcalcConfigService.deleteRtcalcConfigByConfigId(stringAppCode,codeVal);
			log.debug("*** RtcalcConfigController END deleteRtcalcConfig Successful ***");
			//return request.getRequestURI();
			return collecionStr;
		} catch (BusiException e) {
			// 记录日志
			PcitcLogger.logger.error("deleteRtcalcConfig is error", e);
			// 构造错误返回值
			collecionStr = RestfulTool.buildCollection(new ErrorInfo("", e.getCode().toString(), e.getMessage()),
					request.getRequestURI());
		}
		log.debug("*** RtcalcConfigController END deleteRtcalcConfig Exception ***");
		return collecionStr;
	}
	
	/**
	 * update RtcalConfig
	 * @param configId
	 * @param appCode
	 * @param collection
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/configs/{configId}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public String updateRtcalcConfig(@PathVariable String configId,@PathVariable String appCode,@RequestBody String collection, HttpServletRequest request)
			throws Exception {

		log.debug("*** RtcalcConfigController START updateRtcalcConfig ***");
		String result = null;
		// 空格校验
		String stringAppCode = appCode == null ? null : appCode.trim();
		try{
			String hrefs = "";
			// 非空校验 
			checkInput(stringAppCode);
			checkStrLength(stringAppCode,"appcode",50);
			// 将template（模板）转换成 viewmodel对象
			List<com.pcitc.impl.rtcal.model.RtcalcConfigS> model_rtcalcConfig = RestfulTool.toResourceRep(collection, com.pcitc.impl.rtcal.model.RtcalcConfigS.class);
			for(com.pcitc.impl.rtcal.model.RtcalcConfigS cal : model_rtcalcConfig){
				//去除空格
				configId = configId.trim();
				//校验id
				checkId(configId);
				//appcode用路径下的
				cal.setAppCode(appCode);
				// 非空校验
		        checkInputIsNull(cal,false);
		       //去除空格
		       RtcalcConfigS rc = getStringNoBlankAdd(cal,false);
		       //数据校验
		        checkInput(rc,false);
				// 构造href
				String href = request.getRequestURI();
				 com.pcitc.impl.rtcal.model.RtcalcConfig frc = findRtcalcConfig(rc);
				frc.setHref(href);
				// 将viewmodel转换成entity，传到service层
				com.pcitc.impl.rtcal.entity.RtcalcConfig entity_rtcalcConfig = ObjectConverter.entityConverter(frc,com.pcitc.impl.rtcal.entity.RtcalcConfig.class);
				//处理特殊字符查询
				configId = StringUtil.toUrlDecode(configId);
				// 更新AlertPoint数据
				rtcalcConfigService.updateRtcalcConfigByconfigId(stringAppCode,configId,entity_rtcalcConfig);
				hrefs+=href+"/r/n";
			}
			log.debug("*** RtcalcConfigController END updateRtcalcConfig Successful***");
			return hrefs;
		}catch(BusiException e){
			// 记录日志
			PcitcLogger.logger.error("updateAlertPoint is error", e);
			// 构造错误返回值
			result = RestfulTool.buildCollection(new ErrorInfo("", e.getCode().toString(), e.getMessage()),
					request.getRequestURI());
			log.debug("*** RtcalcConfigController END updateRtcalcConfig Exception***");
			return result;
		}
	}
	
	/**
	 * @Title: addConfig
	 * @Description: 添加
	 * @param collection
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/configs",method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public String addConfig(@RequestBody String collection,@PathVariable String appCode,HttpServletRequest request
			) throws Exception {
		
		log.debug("*** RtcalcConfigController START addConfigController ***");
		String result = null;
		// 空格校验
		String stringAppCode = appCode == null ? null : appCode.trim();
		try{
				//新增成功多少行
				int count = 0;
				// 非空校验 
				checkInput(stringAppCode);
				checkStrLength(stringAppCode,"appcode",50);
				// 将template转换成viewmodel
				List<com.pcitc.impl.rtcal.model.RtcalcConfigS> model_rtcalcConfig = RestfulTool.toResourceRep(collection, com.pcitc.impl.rtcal.model.RtcalcConfigS.class);
				for(com.pcitc.impl.rtcal.model.RtcalcConfigS cal : model_rtcalcConfig){
				//appcode用路径下的
					cal.setAppCode(appCode);
				// 非空校验
				checkInputIsNull(cal,true);
				//去除空格
				RtcalcConfigS rc = getStringNoBlankAdd(cal,true);
				//数据校验
				checkInput(rc,true);
				// 构造href
				String href = request.getRequestURI();
				// 将href放入viewmodel对象里
				rc.setHref(href);
				com.pcitc.impl.rtcal.model.RtcalcConfig frc = findRtcalcConfig(rc);
				// 将viewmodel转换成entity
				com.pcitc.impl.rtcal.entity.RtcalcConfig entity_rtcalcConfig = ObjectConverter.entityConverter(frc,com.pcitc.impl.rtcal.entity.RtcalcConfig.class);
					// 调用全局变量rtcalcConfigService的add方法添加rtcalcConfig数据
					int num = rtcalcConfigService.addRtcalcConfig(stringAppCode,entity_rtcalcConfig);
					count+=num;
			}
		    log.debug("*** RtcalcConfigController END addConfigController Successful***");
		    //return href+"/"+rconfigId;
		    return Integer.valueOf(count).toString();
		}catch(BusiException e){
			// 记录日志
			PcitcLogger.logger.error("addConfig is error", e);
			// 构造错误返回值
   			result = RestfulTool.buildCollection(new ErrorInfo("", e.getCode().toString(), e.getMessage()),
					request.getRequestURI());
		}
		// 输出debug级别的日志
		log.debug("*** RtcalcConfigController END addConfigController Exception***");
		return result;
	}
	
	/**
	 * configId check
	 * @param configId
	 * @throws BusiException
	 */
	private static void checkId(String configId) throws BusiException {
			if (!CheckUtil.checkStringIsNull(configId)) {
				if(!configId.trim().matches("^[0-9]*$")){
					throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.CONFIG_CONFIGID_NOT);
				}
				try{
					Integer.parseInt(configId.trim());
				}catch(Exception e){
					throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.INT_NOT_VAID);
				}
			}else{
				throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.CONFIG_CONFIGID_NOT);
			}
	}
	
	/**
	 * nodeIdList check
	 * @param configId
	 * @throws BusiException
	 */
	private static void checknodeIdList(String nodeId) throws BusiException {
			if (!CheckUtil.checkStringIsNull(nodeId)) {
				if(!nodeId.trim().matches("^[0-9]*$")){
					throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.CONFIG_NODEID_NOT);
				}
				try{
					Integer.parseInt(nodeId.trim());
				}catch(Exception e){
					throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.CONFIG_NODEID_ILLEGAL);
				}
			}else{
				throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.CONFIG_NODE_NULL);
			}
	}
	
	/*
	 * @Title: checkInput 
	 * @Description: 非空校验 
	 */
	public void checkInput(String code) throws BusiException {
		if (CheckUtil.checkStringIsNull(code)) {
			throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.APP_CODE_NULL);
		}
		Matcher checkMatcher = CheckUtil.checkMatcher(code);
		if(checkMatcher.find() == false){
			throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.CHECKMATCHER);
		}
	}
	
	 /**
	  * @return 
	  * @Title: checkCodeList 
	  * @Description: 校验nodeIdList
	  */
	public static List<Integer> checkCodeList(String nodeList) throws BusiException {
		List<Integer> resultNodeList = new ArrayList<>();
		String[] strs = nodeList.split(",");
		for (int i=0; i<strs.length; i++) {
			String codeLists = strs[i];
			//数字校验
			checknodeIdList(codeLists.trim());
			resultNodeList.add(Integer.parseInt(codeLists.trim()));
		}
		return resultNodeList;
	}
	
	/**
	 * 去除空格
	 * @param rc
	 * @return
	 * @throws BusiException
	 */
	public RtcalcConfigS getStringNoBlankAdd(RtcalcConfigS rc,boolean flag) throws BusiException {
		RtcalcConfigS rtcalcConfig = new RtcalcConfigS();
		rtcalcConfig.setAppCode(rc.getAppCode().trim());
		rtcalcConfig.setCalcFrequency(rc.getCalcFrequency().trim());
		if(flag){
			rtcalcConfig.setCreateUser(rc.getCreateUser().trim());
		}
		rtcalcConfig.setFormula(rc.getFormula().trim());
		rtcalcConfig.setUpdateUser(rc.getUpdateUser().trim());
		rtcalcConfig.setNextTime(rc.getNextTime().trim());
		rtcalcConfig.setNodeId(rc.getNodeId().trim());
		return rtcalcConfig;
	}
	
	/**
	 * 数据校验
	 * @param rtcalcConfig
	 * @throws BusiException 
	 */
	public void checkInput(RtcalcConfigS rtcalcConfig,boolean flag) throws BusiException {
		//nodeId只能是大于0的整数
		try {
			Integer.valueOf(rtcalcConfig.getNodeId());
		} catch (Exception e) {
			throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.CONFIG_NODEID_ERROR);
		}
		if(Integer.valueOf(rtcalcConfig.getNodeId())<1){
			throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.CONFIG_NODEID_NOT);
		}
		//CalcFrequency只能是大于0的整数
		try {
			Integer.valueOf(rtcalcConfig.getCalcFrequency());
		} catch (Exception e) {
			throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.CONFIG_CALCFREQUENCY_ERROR);
		}
		if(Integer.valueOf(rtcalcConfig.getCalcFrequency())<1){
			throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.CONFIG_CALCFREQUENCY_MIN);
		}
		Matcher checkMatcher = CheckUtil.checkMatcher(rtcalcConfig.getAppCode());
		//appcode只能有数字、字母、下划线组成
		if(!checkMatcher.find()){
			throw new BusiException(ErrorCodeEnum.M000,CheckPrompt.INPUT_RULE);
		}
		if(flag){
			Matcher checkCName = CheckUtil.checkMatcherName(rtcalcConfig.getCreateUser());
			//创建人名称只能由汉字或英文字母组成
			if(!checkCName.find()){
				throw new BusiException(ErrorCodeEnum.M000,CheckPrompt.CONFIG_NAME_ERROR);
			}
			checkStrLength(rtcalcConfig.getCreateUser(),"creatUser",50);
		}
		//修改人名称只能由汉字或英文字母组成
		Matcher checkUName = CheckUtil.checkMatcherName(rtcalcConfig.getUpdateUser());
		if(!checkUName.find()){
			throw new BusiException(ErrorCodeEnum.M000,CheckPrompt.CONFIG_NAME_ERROR);
		}
		checkStrLength(rtcalcConfig.getUpdateUser(),"updateUser",50);
		if(flag){
			//新增时创建人和修改人要相同
			if(!rtcalcConfig.getCreateUser().equals(rtcalcConfig.getUpdateUser())){
				throw new BusiException(ErrorCodeEnum.M000,CheckPrompt.CRE_MNT_NAME);
			}
		}
		//计算公式长度校验
		if(rtcalcConfig.getFormula().length()>2000){
			throw new BusiException(ErrorCodeEnum.M000,CheckPrompt.CONFIG_FOUMULA_ERROR);
		}
		//deviation校验
		if (!CheckUtil.checkStringIsNull(rtcalcConfig.getDeviation())) {
			try {
				Integer.valueOf(rtcalcConfig.getDeviation());
			} catch (Exception e) {
				throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.CONFIG_DEVIATION_ERROR);
			}
		}
		//下次时间校验
		checkDate(rtcalcConfig.getNextTime());
	}
	
	/**
	 * 非空效验
	 * @param rtcalcConfig
	 * @throws BusiException
	 */
	public void checkInputIsNull(RtcalcConfigS rtcalcConfig,boolean flag) throws BusiException {
		//效验appcode
		if (CheckUtil.checkStringIsNull(rtcalcConfig.getAppCode())) {
			throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.CONFIG_CODE_NULL);
		}
		//效验nodeId
		if (CheckUtil.checkStringIsNull(rtcalcConfig.getNodeId())) {
			throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.CONFIG_NODE_NULL);
		}
		//Formula不能为空
		if (CheckUtil.checkStringIsNull(rtcalcConfig.getFormula())) {
			throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.CONFIG_FORMULA_NULL);
		}
		//下次计算时间不能为空
		if (CheckUtil.checkStringIsNull(rtcalcConfig.getNextTime())) {
			throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.CONFIG_NEXTTIME_NULL);
		}
		if(flag){
			//创建人不能为空
			if (CheckUtil.checkStringIsNull(rtcalcConfig.getCreateUser())) {
				throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.CONFIG_USER_NULL);
			}
		}
		//修改人不能为空
		if (CheckUtil.checkStringIsNull(rtcalcConfig.getUpdateUser())) {
			throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.CONFIG_UPDATEUSER_NULL);
		}
		//计算间隔不能为空
		if (CheckUtil.checkStringIsNull(rtcalcConfig.getCalcFrequency())) {
			throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.CONFIG_CALCFREQUENCY_NULL);
		}
	}
	
	/**
	 * appcode's length check 
	 * @return
	 * @throws BusiException 
	 */
	private void checkStrLength(String str,String field,int len) throws BusiException{
		try {
			if(str.getBytes("GBK").length>len){
				//throw new BusiException(ErrorCodeEnum.M000,field+CheckPrompt.STR_LENGTH_BIG);
				throw new BusiException(ErrorCodeEnum.M000,field+"不能超过"+len+"个字符！");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new BusiException(ErrorCodeEnum.M000,CheckPrompt.CHARSET_INVALID);
		} 
	}
	
	/**
	 * date format
	 * @param strDate
	 * @throws BusiException 
	 */
	private void checkDate(String strDate) throws BusiException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date n = null;
		try{
			 n = dateFormat.parse(strDate);
			checkDate(strDate,"/");
			dflag = true;
		}catch(ParseException e){
			SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			try {
				n = dateFormat2.parse(strDate);
				checkDate(strDate,"-");
				dflag = false;
			} catch (ParseException e1) {
				throw new BusiException(ErrorCodeEnum.M000,CheckPrompt.CONFIG_DATE_ERROR);
			}
		}
		Date now = new Date();
		if(n.getTime()<=now.getTime())
			throw new BusiException(ErrorCodeEnum.M000,CheckPrompt.CONFIG_NEXTTIME_MIN);
	}
	
	/**
	 * 校验日期是否是正确的日期
	 * @param date
	 * @param c
	 * @throws BusiException
	 * @throws ParseException
	 */
	private void checkDate(String date,String c) throws BusiException, ParseException{
		String dateStr[] = date.split(" ");
		String time = dateStr[1].trim();
		String[] datefArray = dateStr[0].split(c);
		//日期
//		String datef = date.substring(0,10).trim();
		//时间
//		String time = date.substring(10).trim();
//		String[] datefArray = datef.split(c);
		int year = Integer.parseInt(datefArray[0]);
		int month = Integer.parseInt(datefArray[1]);
		int day = Integer.parseInt(datefArray[2]);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy"+c+"MM"+c+"dd");
		int maxMonthDay = getMonthDay(sdf.parse(datefArray[0]+c+datefArray[1]+c+"01"));
		if(year>2099||month>12||day>maxMonthDay)
		{
			throw new BusiException(ErrorCodeEnum.M000,CheckPrompt.DATE_INVALID);
		}
		String[] timeb = time.split(":");
		if(Integer.valueOf(timeb[0])>23||Integer.valueOf(timeb[1])>59||Integer.valueOf(timeb[2])>59)
			throw new BusiException(ErrorCodeEnum.M000,CheckPrompt.DATE_INVALID);
	}
	
	/**
	 * 取得真的RtcalcConfig
	 * @param rc
	 * @return
	 * @throws Exception
	 */
	private com.pcitc.impl.rtcal.model.RtcalcConfig findRtcalcConfig(com.pcitc.impl.rtcal.model.RtcalcConfigS rc) throws Exception{
		RtcalcConfig rtcalcConfig = new RtcalcConfig();
		rtcalcConfig.setAppCode(rc.getAppCode());
		rtcalcConfig.setCalcFrequency(Integer.parseInt(rc.getCalcFrequency()));
		rtcalcConfig.setCreateUser(rc.getCreateUser());
		rtcalcConfig.setFormula(rc.getFormula());
		rtcalcConfig.setUpdateUser(rc.getUpdateUser());
		if(dflag)
			rtcalcConfig.setNextTime(new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(rc.getNextTime()));
		else
			rtcalcConfig.setNextTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rc.getNextTime()));
		rtcalcConfig.setNodeId(Integer.parseInt(rc.getNodeId()));
		return rtcalcConfig;
	}
	
	public static int getMonthDay(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
}
