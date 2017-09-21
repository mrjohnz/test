package com.pcitc.impl.rtcal.service;

import java.util.List;

import com.pcitc.impl.rtcal.exception.BusiException;
import com.pcitc.impl.rtcal.pojo.RtcalcConfig;

public interface RtcalcConfigService{
	/**
	 * @Title: searchConfig 
	 * @Description: 查询-查询所有
	 * @param appCode
	 * @param nodeIdList
	 * @return
	 * @throws Exception
	 */
	public List<RtcalcConfig> searchConfig(String appCode, List<Integer> nodeIdList) throws Exception;
	/**
	 * @Title: searchConfigByACCI 
	 * @Description: 查询-查询指定配置
	 * @param appCode
	 * @param configId
	 * @return
	 * @throws Exception
	 */
	public List<RtcalcConfig> searchConfigByACCI(String appCode,String configId) throws Exception;
	/**
	 * @Title: deleteRtcalcConfigByConfigId 
	 * @Description: 删除
	 * @param configId
	 * @throws BusiException
	 */
	public void deleteRtcalcConfigByConfigId(String appcode,String configId ) throws Exception;
	
	/**
	 * @Title: updateRtcalcConfigByconfigId 
	 * @Description: 更新
	 * @param configId
	 * @param rtcalcConfig
	 * @return
	 * @throws BusiException
	 */
	public int updateRtcalcConfigByconfigId(String appcode,String configId,com.pcitc.impl.rtcal.entity.RtcalcConfig rtcalcConfig) throws Exception;
	
	/**
	 * @Title: addRtcalcConfig 
	 * @Description: 新增配置
	 * @param rtcalcConfig
	 * @return
	 * @throws BusiException
	 */
	int addRtcalcConfig(String appcode,com.pcitc.impl.rtcal.entity.RtcalcConfig rtcalcConfig) throws Exception;
}
