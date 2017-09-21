package com.pcitc.impl.rtcal.service;

import java.util.List;
import java.util.Map;

import com.pcitc.impl.rtcal.entity.CalcResult;

public interface CalcResultsService {

	/**
	 * @Title: getCalcResults 
	 * @Description: 查询-查询时间段内数据
	 * @param codelist
	 * @param starttime
	 * @param endtime
	 * @return
	 * @throws Exception
	 */
	List<CalcResult> getCalcResults(Map<String, Map<Integer, String>> map, String[] starttime, String[] endtime) throws Exception;

	/**
	 * @Title: getCalcResults 
	 * @Description: 查询-指定日期
	 * @param codelist
	 * @return
	 * @throws Exception
	 */
	List<CalcResult> getCalcResults(Map<String, Map<Integer, String>> map) throws Exception;
}
