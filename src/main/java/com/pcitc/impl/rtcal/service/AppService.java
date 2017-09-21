package com.pcitc.impl.rtcal.service;

import java.util.List;

import com.pcitc.impl.rtcal.entity.App;
import com.pcitc.impl.rtcal.exception.BusiException;

public interface AppService {

	/**
	 * @Title: getApps 
	 * @Description: 查询-查询所有
	 * @throws BusiException
	 * @return List<App>    返回类型 
	 */
	List<App> getApps(List<String> codes) throws BusiException;

	/**
	 * @Title: getAppByCode 
	 * @Description: 查询-条件查询code 
	 * @param code
	 * @throws BusiException
	 * @return com.pcitc.impl.rtcal.entity.App    返回类型 
	 */
	com.pcitc.impl.rtcal.entity.App getAppByCode(String codetrim) throws BusiException;

	/**
	 * @Title: addApp 
	 * @Description: 添加
	 * @param appEntity
	 * @return void    返回类型 
	 * @throws BusiException
	 */
	void addApp(com.pcitc.impl.rtcal.entity.App appEntity) throws BusiException;

	/**
	 * @Title: updateApp 
	 * @Description: 修改
	 * @param code
	 * @param appEntity
	 * @return String    返回类型 
	 * @throws BusiException
	 */
	void updateApp(String code, com.pcitc.impl.rtcal.entity.App appEntity) throws BusiException;

	/**
	 * @Title: deleteApp 
	 * @Description: 修改
	 * @param code
	 * @param appEntity
	 * @return String    返回类型 
	 * @throws BusiException
	 */
	void deleteApp(String codeVal) throws BusiException;

}
