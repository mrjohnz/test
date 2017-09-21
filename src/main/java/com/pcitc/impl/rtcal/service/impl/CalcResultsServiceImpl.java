package com.pcitc.impl.rtcal.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.pcitc.impl.rtcal.common.PcitcLogger;
import com.pcitc.impl.rtcal.common.redis.RDService;
import com.pcitc.impl.rtcal.common.redis.ResultCreator;
import com.pcitc.impl.rtcal.entity.CalcResult;
import com.pcitc.impl.rtcal.service.CalcResultsService;

/**
 * @ClassName:CalcResultsServiceImpl
 * @Description:实时计算结果集
 * @author 123
 * @date 2016年12月27日09:17:09
 */
@Service
public class CalcResultsServiceImpl implements CalcResultsService {

	@Override
	public List<CalcResult> getCalcResults(Map<String, Map<Integer, String>> map, String[] starttime, String[] endtime)
			throws Exception {
		PcitcLogger.logger.info("CalcResultsServiceImpl  getCalcResults<map,String> is start");
		List<CalcResult> lists = new ArrayList<>();
		List<CalcResult> lt = new ArrayList<>();
		// 从redis中获取数据
		Iterator<String> iterator = map.keySet().iterator();
		// 获取每个appCode对应的key-时间集合
		while (iterator.hasNext()) {
			String str = iterator.next();
			Map<Integer, String> mapstr = map.get(str);
			Iterator<Integer> iterator2 = mapstr.keySet().iterator();
			// 获取appCode对应的key-时间集合
			while (iterator2.hasNext()) {
				// 判断当前key是否是开始时间
				int i = iterator2.next();
				if (i == Integer.valueOf(starttime[0])) {
					List<String> list = new ArrayList<>();
					list.add(mapstr.get(i));
					// 从redis中获取数据
					String requestBysubSetKey = RDService.getRequestBysubSetKey(list);
					// 转换为实体类
					lt = ResultCreator.creatResults(requestBysubSetKey,str,i);
					if (lt != null) {
						if(starttime[0].equals(endtime[0])){
							// 截取时间范围内的数据
							List<CalcResult> jqStart = jqStart(lt, Integer.valueOf(starttime[1]));
							jqStart = jqEnd(jqStart, Integer.valueOf(endtime[1]));
							lists.addAll(jqStart);
						}else{
							// 截取时间范围内的数据
							List<CalcResult> jqStart = jqStart(lt, Integer.valueOf(starttime[1]));
							lists.addAll(jqStart);
						}
					}
				} else if (i == Integer.valueOf(endtime[0])) {// 判断当前key是否是结束时间
					List<String> list = new ArrayList<>();
					list.add(mapstr.get(i));
					// 从redis中获取数据
					String requestBysubSetKey = RDService.getRequestBysubSetKey(list);
					// 截取时间范围内的数据
					lt = ResultCreator.creatResults(requestBysubSetKey,str,i);
					if (lt != null) {
						// 截取时间范围内的数据
						List<CalcResult> jqStart = jqEnd(lt, Integer.valueOf(endtime[1]));
						lists.addAll(jqStart);
					}
				} else {// key为中间时间
					List<String> list = new ArrayList<>();
					list.add(mapstr.get(i));
					String requestBysubSetKey = RDService.getRequestBysubSetKey(list);
					lt = ResultCreator.creatResults(requestBysubSetKey,str,i);
					// 排序
					if (lt != null) {
						lt = getPX(lt);
						lists.addAll(lt);
					}
				}
			}
		}
		PcitcLogger.logger.info("CalcResultsServiceImpl  getCalcResults<map,String> is end");
		return lists;
	}

	/**
	 * @Title: getCalcResults
	 * @Description: 查询-指定日期
	 * @param codelist
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<CalcResult> getCalcResults(Map<String, Map<Integer, String>> map) throws Exception {
		PcitcLogger.logger.info("CalcResultsServiceImpl  getCalcResults<map> is start");
		List<CalcResult> lt = new ArrayList<>();
		// 获取appcode的set集合
		Iterator<String> keyAppCode = map.keySet().iterator();
		// 遍历获取对应的时间-key集合
		while (keyAppCode.hasNext()) {
			String str = keyAppCode.next();
			Map<Integer, String> map2 = map.get(str);
			// 获取key
			Iterator<Integer> iterator = map2.keySet().iterator();
			while (iterator.hasNext()) {
				List<String> list = new ArrayList<>();
				List<CalcResult> listCalc = new ArrayList<>();
				Integer time = iterator.next();
				list.add(map2.get(time));
				String requestBysubSetKey = RDService.getRequestBysubSetKey(list);
				listCalc = ResultCreator.creatResults(requestBysubSetKey,str,time);
				if(listCalc!=null && listCalc.size()!=0){
				lt.add(getPX(listCalc).get(getPX(listCalc).size() - 1));
				}
			}
		}
		PcitcLogger.logger.info("CalcResultsServiceImpl  getCalcResults<map> is end");
		return lt;
	}

	/**
	 * @Description: 计算时间段
	 * @param starttime
	 * @param time
	 * @param endtime
	 * @return
	 */
	public boolean compareSN(String starttime, String time, String endtime) {
		boolean falg = false;
		Integer start = Integer.valueOf(starttime);
		Integer s = Integer.valueOf(time);
		Integer end = Integer.valueOf(endtime);
		if (s >= start && s <= end) {
			falg = true;
		}
		return falg;
	}

	public List<CalcResult> getPX(List<CalcResult> list) {
		Collections.sort(list, new Comparator<CalcResult>() {

			/*
			 * int compare(Student o1, Student o2) 返回一个基本类型的整型， 返回负数表示：o1 小于o2，
			 * 返回0 表示：o1和o2相等， 返回正数表示：o1大于o2。
			 */
			public int compare(CalcResult o1, CalcResult o2) {

				// 按照时间大小进行升序排列
				if (Integer.valueOf(o1.getSelectTime()) > Integer.valueOf(o2.getSelectTime())) {
					return 1;
				}
				if (o1.getSelectTime() == o2.getSelectTime()) {
					return 0;
				}
				return -1;
			}
		});
		return list;
	}

	public List<CalcResult> jqStart(List<CalcResult> list, Integer start) {
		List<CalcResult> lt = new ArrayList<>();
		for (CalcResult calcResult : list) {
			if (Integer.valueOf(calcResult.getSelectTime()) >= start) {
				lt.add(calcResult);
			}
		}
		lt = getPX(lt);
		return lt;
	}

	public List<CalcResult> jqEnd(List<CalcResult> list, Integer end) {
		List<CalcResult> lt = new ArrayList<>();
		for (CalcResult calcResult : list) {
			if (Integer.valueOf(calcResult.getSelectTime()) <= end) {
				lt.add(calcResult);
			}
		}
		lt = getPX(lt);
		return lt;
	}
}
