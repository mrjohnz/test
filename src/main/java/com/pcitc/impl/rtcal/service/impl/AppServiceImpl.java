package com.pcitc.impl.rtcal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcitc.impl.rtcal.common.CheckPrompt;
import com.pcitc.impl.rtcal.common.EntityUtil;
import com.pcitc.impl.rtcal.common.ErrorCodeEnum;
import com.pcitc.impl.rtcal.common.PcitcLogger;
import com.pcitc.impl.rtcal.dao.AppDao;
import com.pcitc.impl.rtcal.dao.RtcalcConfigDao;
import com.pcitc.impl.rtcal.entity.App;
import com.pcitc.impl.rtcal.exception.BusiException;
import com.pcitc.impl.rtcal.pojo.RtcalcConfig;
import com.pcitc.impl.rtcal.service.AppService;

import pcitc.imp.common.ettool.utils.ObjectConverter;


/**
 * @ClassName: AppServiceImpl
 * @Description: 业务应用集合
 * @author
 * @date 2016年12月23日 上午8:49:30
 */
@Service
public class AppServiceImpl implements AppService {

	@Autowired
	private AppDao appDao;
	@Autowired
	public RtcalcConfigDao rtcalcConfigDao;

	/**
	 * @Title: getApps
	 * @Description: 查询-所有App
	 * @throws BusiException
	 * @return List<App> 返回类型
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<App> getApps(List<String> codes) throws BusiException {
		List<com.pcitc.impl.rtcal.pojo.App> appList = null;
		List<App> applists = new ArrayList<>();
		PcitcLogger.logger.info("AppServiceImpl  getApps is start");
		try {
			if (codes.size() == 0) {
				appList = appDao.getApps();
			} else {
				appList = appDao.getAppsByCodes(codes);
			}
			applists = ObjectConverter.listConverter(appList, App.class);
			PcitcLogger.logger.info("AppServiceImpl  getApps is end");
		} catch (BusiException e) {
			PcitcLogger.logger.info("AppServiceImpl  getApps is error", e);
			throw new BusiException(ErrorCodeEnum.M001, CheckPrompt.APP_CODE_CHECKMATCHER, e);
		} catch (Exception e) {
			PcitcLogger.logger.info("AppServiceImpl  getApps is error", e);
			throw new BusiException(ErrorCodeEnum.M001, e.getMessage(), e);
		}

		return applists;
	}

	/**
	 * @Title: getAppByCode
	 * @Description: 查询-条件查询code
	 * @param code
	 * @throws BusiException
	 * @return com.pcitc.impl.rtcal.entity.App 返回类型
	 */
	@Override
	public App getAppByCode(String appCode) throws BusiException {
		com.pcitc.impl.rtcal.pojo.App app = new com.pcitc.impl.rtcal.pojo.App();
		App apps = new App();
		PcitcLogger.logger.info("AppServiceImpl  getAppByCode is start");
		try {
			app = appDao.getAppByAppCode(appCode);
			apps = ObjectConverter.entityConverter(app, App.class);
			PcitcLogger.logger.info("AppServiceImpl  getAppByCode is end");
		} catch (BusiException e) {
			PcitcLogger.logger.info("AppServiceImpl  getAppByCode is error");
			throw new BusiException(ErrorCodeEnum.M001, CheckPrompt.APP_CODE_CHECKMATCHER, e);
		} catch (Exception e) {
			PcitcLogger.logger.info("AppServiceImpl  getAppByCode is error");
			throw new BusiException(ErrorCodeEnum.M001, e.getMessage(), e);
		}
		return apps;

	}

	/**
	 * @Title: addApp
	 * @Description: 添加
	 * @param appEntity
	 * @return void 返回类型
	 * @throws BusiException
	 */
	@Override
	public void addApp(App appEntity) throws BusiException {
		PcitcLogger.logger.info("AppServiceImpl  addApp is start");
		// entity转换为pojo
		com.pcitc.impl.rtcal.pojo.App appPojo = EntityUtil.entity2Pojo(appEntity);
		// 校验appcode是否已经存在
		com.pcitc.impl.rtcal.pojo.App app = appDao.queryByAppCode(appPojo.getAppCode());
		if (app != null) {
			PcitcLogger.logger.info("AppServiceImpl  addApp is error");
			throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.APP_CODE_REPEAT);
		}

		// 创建app
		appDao.save(appPojo);
		PcitcLogger.logger.info("AppServiceImpl  addApp is end");
	}

	/**
	 * @Title: updateApp
	 * @Description: 修改
	 * @param code
	 * @param appEntity
	 * @return String 返回类型
	 * @throws BusiException
	 */
	@Override
	public void updateApp(String code, App appEntity) throws BusiException {
		PcitcLogger.logger.info("AppServiceImpl  updateApp is start");
		// 校验appcode是否已经存在
		com.pcitc.impl.rtcal.pojo.App queryByAppCode = appDao.queryByAppCode(code);
		if (queryByAppCode == null) {
			PcitcLogger.logger.info("AppServiceImpl  updateApp is error");
			throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.APP_CODE_NOT_REPEAT);
		}
		// entity转换为pojo
		com.pcitc.impl.rtcal.pojo.App appPojo = EntityUtil.entity2Pojo(appEntity);

		appDao.updateAppByAppCode(code, appPojo.getAppName(), appPojo.getUpdateUser());
		PcitcLogger.logger.info("AppServiceImpl  updateApp is end");
	}

	/**
	 * @Title: deleteApp
	 * @Description: 删除
	 * @param code
	 * @param appEntity
	 * @return String 返回类型
	 * @throws BusiException
	 */
	@Override
	public void deleteApp(String codeVal) throws BusiException {
		PcitcLogger.logger.info("AppServiceImpl  deleteApp is start");
		// 校验appcode是否已经存在

		com.pcitc.impl.rtcal.pojo.App queryByAppCode = appDao.queryByAppCode(codeVal);
		if (queryByAppCode == null) {
			throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.APP_CODE_NOT_REPEAT);
		}

		// 校验此appCode下是否有配置
		List<RtcalcConfig> list = rtcalcConfigDao.searchConfigByAppCode(codeVal);
		if (list == null || list.size() == 0) {
			appDao.deleteAppByAppCode(codeVal);
			PcitcLogger.logger.info("AppServiceImpl  deleteApp is end");
		} else {
			PcitcLogger.logger.info("AppServiceImpl  deleteApp is error");
			throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.APP_CODE_REPEAT_CONFIG);
		}
	}
}
