package com.pcitc.impl.rtcal.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pcitc.impl.rtcal.common.CheckPrompt;
import com.pcitc.impl.rtcal.common.CheckUtil;
import com.pcitc.impl.rtcal.common.ErrorCodeEnum;
import com.pcitc.impl.rtcal.common.PcitcLogger;
import com.pcitc.impl.rtcal.exception.BusiException;
import com.pcitc.impl.rtcal.model.CalcResult;
import com.pcitc.impl.rtcal.service.CalcResultsService;

import pcitc.imp.common.ettool.baseresrep.ErrorInfo;
import pcitc.imp.common.ettool.utils.ObjectConverter;
import pcitc.imp.common.ettool.utils.RestfulTool;

/**
 * @ClassName: CalcResultsCoutroller
 * @Description: 实时计算结果集
 * @author
 * 
 * @date 2016年12月23日10:23:40
 */
@RestController
@RequestMapping(value = "/realTimeCalcService/apps/{code}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CalcResultsCoutroller {

	@Autowired
	private CalcResultsService calcResultsService;

	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = "/calcResults", method = RequestMethod.GET)
	public String getCalcResults(@PathVariable(value = "code") String code, HttpServletRequest request)
			throws Exception {

		String conllictionstr = "";
		PcitcLogger.logger.info("getCalcResults is start");
		// 获取$nodeIdList参数
		String nodeIdList = request.getParameter("$nodeIdList") == null ? null : request.getParameter("$nodeIdList");
		// 获取查询的 起止时间 参数，指定的时间段
		String selectStartDateTime = request.getParameter("$selectStartDateTime") == null ? null
				: request.getParameter("$selectStartDateTime");
		String selectEndDateTime = request.getParameter("$selectEndDateTime") == null ? null
				: request.getParameter("$selectEndDateTime");
		// 获取$selectDate参数，指定的时间
		String selectDate = request.getParameter("$selectDate") == null ? null : request.getParameter("$selectDate");

		//校验nodeIdList
		Map<String, Map<Integer, String>> map = null;
		String[] starttime = null;
		String[] endtime = null;
		List<com.pcitc.impl.rtcal.entity.CalcResult> calcResult = null;
		try {
			checkNodeList(nodeIdList);
			// 判断查询方式
			if (selectStartDateTime != null && selectEndDateTime != null) {
				 checkDate(selectStartDateTime,1);
				 checkDate(selectEndDateTime,1);
				// 按时间段查询
				// 拼接key
				map = getJointKey(code, nodeIdList, selectStartDateTime, selectEndDateTime);
				String date = selectStartDateTime.substring(0,8);
				String time = selectStartDateTime.substring(8);
				// 起止时间段
				starttime = new String[]{date,time};
				endtime = new String[]{selectEndDateTime.substring(0,8),selectEndDateTime.substring(8)};
				// 查询
				calcResult = calcResultsService.getCalcResults(map, starttime, endtime);
			} else if (selectDate != null) {
				 checkDate(selectDate,0);
				// 按指定时间查询
				// 拼接key
				map = getJointKey(code, nodeIdList, selectDate, selectDate);
				// 查询
				calcResult=calcResultsService.getCalcResults(map);
			}
			// 将entity转换为model
			List<CalcResult> calcList = ObjectConverter.listConverter(calcResult, CalcResult.class);
				// 拼接conllictionJson
				conllictionstr = RestfulTool.buildCollection(calcList, "/calcResult", CalcResult.class);
			PcitcLogger.logger.info("getCalcResults is end");
		} catch (BusiException e) {
			PcitcLogger.logger.info("getCalcResults is error", e);
			conllictionstr = RestfulTool.buildCollection(new ErrorInfo("", e.getCode().toString(), e.getMessage()),
					request.getRequestURI());
		}
		return conllictionstr;
	}

	/**
	 * @Description: 拼接大key
	 * @param code
	 * @param nodeIdList
	 * @param selectStartDateTime
	 * @param selectEndDateTime
	 * @return
	 * @throws BusiException
	 */
	private Map<String, Map<Integer, String>> getJointKey(String code, String nodeIdList, String selectStartDateTime,
			String selectEndDateTime) throws BusiException {
		PcitcLogger.logger.info("getJointKey is start");
		String selectStartDateTimeNYR = selectStartDateTime.substring(0,8);
		String selectEndDateTimeNYR = selectEndDateTime.substring(0,8);
		// 将时间转换为数字格式
		int start = Integer.valueOf(selectStartDateTimeNYR);
		int end = Integer.valueOf(selectEndDateTimeNYR);
		// 以nodeId为key
		Map<String, Map<Integer, String>> map = new HashMap<>();
		String str = "";
		List<String> list1 = checkNodeList(nodeIdList);
		// 判断时间段是否合理
		if (selectStartDateTime.compareTo(selectEndDateTime) > 0) {//时间不合理
			throw new BusiException(ErrorCodeEnum.M001, CheckPrompt.Date_NoT_FORMAT_2);
		} else if (selectStartDateTime.compareTo(selectEndDateTime) < 0) {//当传的是时间段时

			for (String str1 : list1) {
				// 以时间节点为key
				Map<Integer, String> mapstr = new HashMap<>();
				for (int i = start; i <= end; i++) {
					String num = Integer.toString(i);
					String r = num.substring(6, 8);
					if(Integer.valueOf(r)>31){
						continue;
					}
					str = code + "_" + str1 + "_rtcalc_" + i;
					mapstr.put(i, str);
				}
				map.put(str1, mapstr);
			}

		} else {//当传的是具体一天时
			for (String str1 : list1) {
				// 以时间节点为key
				Map<Integer, String> mapstr = new HashMap<>();

				str = code + "_" + str1 + "_rtcalc_" + start;
				mapstr.put(start, str);

				map.put(str1, mapstr);
			}
		}
		PcitcLogger.logger.info("getJointKey is end");
		return map;
	}

	/**
	 * @return
	 * @Title: checkNodeList
	 * @Description: 校验nodeList
	 */
	public static List<String> checkNodeList(String nodeList) throws BusiException {
		PcitcLogger.logger.info("checkNodeList is start");
		List<String> list = new ArrayList<String>();
		String[] strs = nodeList.split(",");
		for (int i = 0; i < strs.length; i++) {
			// 空格校验
			String nodeLists = strs[i].trim();
			// 非空校验
			boolean checkStringIsNull = CheckUtil.checkStringIsNull(nodeLists);
			if (checkStringIsNull) {
				continue;
			}
			// 特殊字符判断
			if (!CheckUtil.isNumeric(nodeLists)) {
				continue;
			}
			list.add(nodeLists);
		}
		if(list.size()==0){
			throw new BusiException(ErrorCodeEnum.M001, CheckPrompt.CONFIG_NODEList_NULL);
		}
		PcitcLogger.logger.info("checkNodeList is end");
		return list;
	}

	/**
	 * @Description: 分离时间的年月日
	 * @param date
	 * @return
	 * @throws BusiException
	 */
	public String checkNYR(String date) throws BusiException {
		date = timeCycle(date);
		String[] dt = date.split(" ");
		String nyr = "";
		String[] nyr1 = dt[0].split("-");
		for (int i = 0; i < nyr1.length; i++) {
			nyr += nyr1[i];
		}
		return nyr;
	}

	/**
	 * @Description: 分离时间的时分秒
	 * @param date
	 * @return
	 * @throws BusiException
	 */
	public String checkSFM(String date) throws BusiException {
		// 时间格式转换
		date = timeCycle(date);
		String[] dt = date.split(" ");
		String sfm = "";
		String[] sfm1 = dt[1].split(":");
		for (int i = 0; i < sfm1.length; i++) {
			sfm += sfm1[i];
		}
		return sfm;
	}

	/**
	 * @Description: 时间转换
	 * @param date
	 * @return
	 * @throws BusiException
	 */
	@SuppressWarnings("deprecation")
	public String timeCycle(String date) throws BusiException {
		// 指定时间格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			// 转换为date
			Date parse = new Date(date);
			// 转换为指定格式
			date = sdf.format(parse);
		} catch (Exception e) {
			throw new BusiException(ErrorCodeEnum.M001, CheckPrompt.Date_NoT_FORMAT, e);
		}
		return date;
	}
	
	/**
	 * 校验日期是否是正确的日期
	 * @param date
	 * @param c
	 * @throws BusiException
	 * @throws ParseException
	 */
	private void checkDateValue(String strDate,int flag) throws BusiException, ParseException{
		//日期，年月日
		 String dateItem = strDate.substring(0,8);
		 String timeItem="";
		 String hour="";
		 String min="";
		 String second="";
		 if(flag==1){
			  timeItem = strDate.substring(8);
			  hour = timeItem.substring(0, 2);
			  min = timeItem.substring(2, 4);
			  second = timeItem.substring(4);
		 }
		 //时间，时分秒
		 String year = dateItem.substring(0, 4);
		 String month = dateItem.substring(4, 6);
		 String day = dateItem.substring(6);
		 
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		int maxMonthDay = getMonthDay(sdf.parse(year+month+"01"));
		if(Integer.parseInt(year)>2099||Integer.parseInt(month)>12||Integer.parseInt(day)>maxMonthDay)
		{
			throw new BusiException(ErrorCodeEnum.M000,CheckPrompt.DATE_INVALID);
		}
		if(flag==1){
			if(Integer.valueOf(hour)>23||Integer.valueOf(min)>59||Integer.valueOf(second)>59)
				throw new BusiException(ErrorCodeEnum.M000,CheckPrompt.DATE_INVALID);
		}
	}
	
	public static int getMonthDay(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
	
	/**
	 * date format
	 * @param strDate
	 * @throws BusiException 
	 */
	private void checkDate(String strDate,int flag) throws BusiException{
		if(flag==1){	
			if(strDate.trim().length()!=14){
				throw new BusiException(ErrorCodeEnum.M000,CheckPrompt.DATE_ERRO);
			}
		}else{
			if(strDate.trim().length()!=8){
				throw new BusiException(ErrorCodeEnum.M000,CheckPrompt.DATETIME_ERRO);
			}
		}
		try{
			Long.parseLong(strDate.trim());
			checkDateValue(strDate.trim(),flag);
		}catch(BusiException e){
			throw new BusiException(ErrorCodeEnum.M000,e.getMessage());
		}
		catch(Exception e){
			throw new BusiException(ErrorCodeEnum.M000,CheckPrompt.DATE_STYLE_ERRO);
		}
	}
}
