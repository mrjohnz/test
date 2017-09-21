package com.pcitc.impl.rtcal.common.redis;

import java.util.List;

import com.pcitc.impl.rtcal.common.PcitcLogger;

/**
 * @Description: 从redis中获取数据
 * @author 
 *
 */

public class RDService {
	static String http = "http://";
	//static String url = "/apps/imp/resources/rtcalc/dataitems";
	static String url = "/apps/imp/resources/rtcalc_device/dataitems";
	//static String url ="/apps/imp/resources/rtcalc_rtdata/dataitems";
	public static String postRequestNoCheck(String collection){
		StringBuffer urlSB = new StringBuffer(http);
		urlSB.append(CalcConfigure.RDHost);
		urlSB.append(url);
		urlSB.append("?$options=byNoCheck");
		return MicroServiceClient.postRequest(urlSB.toString(), collection);
	}
	
	public static String getRequestBysubSetKey(List<String> keyList){
		PcitcLogger.logger.info("RDService  getRequestBysubSetKey is start");
		StringBuffer urlSB = new StringBuffer(http);
		urlSB.append(CalcConfigure.RDHost);
		urlSB.append(url);
		urlSB.append("?$keyList=");
		for (int i = 0; i < keyList.size(); i++) {
			if(i == keyList.size()-1){
				
				urlSB.append(keyList.get(i));
			}else{
			
				urlSB.append(keyList.get(i));
				urlSB.append(",");
			}
		}		
		PcitcLogger.logger.info("RDService  getRequestBysubSetKey is end");
		return MicroServiceClient.getRequest(urlSB.toString());
	}

}
