package com.pcitc.impl.rtcal.common.redis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.pcitc.common.collectionjson.model.Collection;
import com.pcitc.common.collectionjson.model.Item;
import com.pcitc.common.collectionjson.serialization.Serializer;
import com.pcitc.impl.rtcal.common.PcitcLogger;
import com.pcitc.impl.rtcal.entity.CalcResult;
import com.pcitc.impl.rtcal.entity.Result;

import pcitc.imp.common.ettool.utils.RestfulTool;

/**
 * 构建结果数据
 * 
 * @author haiwen.wang
 *
 */
public class ResultCreator {

	/**
	 * 把ResultVO转换为collection
	 * 
	 * @param results
	 * @return
	 * @throws Exception
	 */
	public static String creatCollection(CalcResult results) throws Exception {
		List<CalcResult> list = new ArrayList<CalcResult>();
		list.add(results);
		String collectionStr = RestfulTool.buildCollection(list);
		return collectionStr;
	}

	/**
	 * 把collection转换为ResultVO
	 * 
	 * @param collectionStr
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<CalcResult> creatResults(String collectionStr,String nodeId,Integer time) throws Exception {
		PcitcLogger.logger.info("ResultCreator  creatResults is start");
		Serializer serializer = new Serializer();
		//将获取的字符串转化为collectionJson串
		Collection collection = serializer.deserialize(collectionStr, Collection.class);
		//获取查询数据
		List<Item> itemList = collection.getItems();
		List<CalcResult> resultList = new ArrayList<CalcResult>();
		for (Item item : itemList) {//遍历所有数据
			String str="";
			try{
				str = item.getString("value");
			}catch(Exception e){
				return null;
			}
			String keystr = item.getString("key");
			String[] key = keystr.split("_");
			Map<String, String> strMap = serializer.deserialize(str, Map.class);
			//转换数据格式
			for (Map.Entry<String, String> entry : strMap.entrySet()) {
				Result resultVO = new Result();
				
				//将json串转化为CalcResult对象
				resultVO=serializer.deserialize(entry.getValue(), Result.class);
				CalcResult calcResult = deserialize(resultVO);
				calcResult.setSelectDate(Integer.toString(time));
				calcResult.setNodeId(nodeId);
				calcResult.setSelectTime(entry.getKey());
				resultList.add(calcResult);
			}
		}
		PcitcLogger.logger.info("ResultCreator  creatResults is end");
		return resultList;
	}

	public static CalcResult deserialize(Result result){
		CalcResult cr = new CalcResult();
		cr.setDayValue(result.getDayValue());
		cr.setPeriodValue(result.getPeriodValue());
		cr.setPeriodDateTime(result.getPeriodDateTime());
		return cr;
	}
	
}
