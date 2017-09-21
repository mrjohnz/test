package com.pcitc.impl.rtcal.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pcitc.impl.rtcal.common.CheckPrompt;
import com.pcitc.impl.rtcal.common.CheckUtil;
import com.pcitc.impl.rtcal.common.ErrorCodeEnum;
import com.pcitc.impl.rtcal.dao.AppDao;
import com.pcitc.impl.rtcal.dao.RtcalcConfigDao;
import com.pcitc.impl.rtcal.exception.BusiException;
import com.pcitc.impl.rtcal.pojo.RtcalcConfig;
import com.pcitc.impl.rtcal.service.RtcalcConfigService;

import pcitc.imp.common.ettool.utils.ObjectConverter;


@Service
@Component
public class RtcalcConfigServiceImpl implements RtcalcConfigService {

	private static final Logger log = Logger.getLogger(RtcalcConfigServiceImpl.class);
	@Autowired
	public RtcalcConfigDao rtcalcConfigDao;
	@Autowired
	private AppDao appDao;

	/**
	 * @Title: searchConfigByACCI
	 * @Description: 查询-查询指定配置
	 * @param appCode
	 * @param configId
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<RtcalcConfig> searchConfigByACCI(String appCode, String configId) throws Exception {
		// if(configIdList != null && !"".equals(configIdList)){
		// return rtcalcConfigDao.searchConfigByappCode(appCode,configIdList);
		// }
		int id = Integer.parseInt(configId);
		return rtcalcConfigDao.searchConfigByACCI(appCode, id);
	}

	/**
	 * @Title: searchConfig
	 * @Description: 查询-查询所有
	 * @param appCode
	 * @param nodeIdList
	 * @return
	 * @throws Exception
	 */
	@Override
	public List<RtcalcConfig> searchConfig(String appCode, List<Integer> nodeIdList) throws Exception {

		List<RtcalcConfig> list = null;

		if (nodeIdList != null && nodeIdList.size() != 0) {

			list = rtcalcConfigDao.searchConfigByAppCode(appCode, nodeIdList);
		} else {
			list = rtcalcConfigDao.searchConfigByAppCode(appCode);
		}
		return list;
	}

	/**
	 * @Title: deleteRtcalcConfigByConfigId
	 * @Description: 删除
	 * @param configId
	 * @throws BusiException
	 */
	@Override
	public void deleteRtcalcConfigByConfigId(String appcode,String configId) throws Exception {
		// 校验appcode是否存在
		if (null == appDao.getAppByAppCode(appcode)
				|| CheckUtil.checkStringIsNull(appDao.getAppByAppCode(appcode).getAppCode()))
			throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.APP_CODE_NOT_REPEAT);
		// 判断是否存在
		RtcalcConfig findConfigByConfigId = rtcalcConfigDao.searchConfigs(appcode.trim(),Integer.valueOf(configId));
		if (findConfigByConfigId == null) {
			throw new BusiException(ErrorCodeEnum.M000,configId + "不存在！");
		}
		rtcalcConfigDao.delete(findConfigByConfigId);
//		rtcalcConfigDao.deleteRtcalcConfigByConfigId(Integer.parseInt(configId));
	}

	/**
	 * @Title: updateRtcalcConfigByconfigId
	 * @Description: 更新
	 * @param configId
	 * @param rtcalcConfig
	 * @return
	 * @throws BusiException
	 */
	@Override
	public int updateRtcalcConfigByconfigId(String appcode,String configId,
			com.pcitc.impl.rtcal.entity.RtcalcConfig rtcalcConfig) throws BusiException {

		log.debug("*** RtcalcConfigServiceImpl START updateRtcalcConfigByconfigId ***");
		// 校验appcode是否存在
		if (null == appDao.getAppByAppCode(appcode)
				|| CheckUtil.checkStringIsNull(appDao.getAppByAppCode(appcode).getAppCode()))
			throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.APP_CODE_NOT_REPEAT);
		// 将entity转换成pojo
		try {
			com.pcitc.impl.rtcal.pojo.RtcalcConfig srtcalcConfig = ObjectConverter.entityConverter(rtcalcConfig,
					com.pcitc.impl.rtcal.pojo.RtcalcConfig.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BusiException(ErrorCodeEnum.M000,"entityTopojo错误！");
		}
		// 判断是否存在
		RtcalcConfig searchConfigByNode = rtcalcConfigDao.findConfigByConfigId(Integer.valueOf(configId));
		if (searchConfigByNode == null) {
			throw new BusiException(ErrorCodeEnum.M000,configId + "不存在！");
		}
		rtcalcConfig.setAppCode(appcode);
		rtcalcConfigDao.updateRtcalcConfigByconfigId(Integer.parseInt(configId), rtcalcConfig.getNodeId(),
				rtcalcConfig.getFormula(), rtcalcConfig.getCalcFrequency(), rtcalcConfig.getDeviation(),
				rtcalcConfig.getNextTime(),  rtcalcConfig.getUpdateUser());

		log.debug("*** RtcalcConfigServiceImpl END updateRtcalcConfigByConfigId ***");

		return rtcalcConfig.getConfigId();
	}

	/**
	 * @Title: addRtcalcConfig
	 * @Description: 新增配置
	 * @param rtcalcConfig
	 * @return
	 * @throws BusiException
	 */
	@Override
	public int addRtcalcConfig(String appcode,com.pcitc.impl.rtcal.entity.RtcalcConfig rtcalcConfig) throws Exception {
		log.debug("*** AlertPointServiceImpl START addAlertPoint ***");
		// 校验appcode是否已经存在
		if (null == appDao.getAppByAppCode(appcode)
				|| CheckUtil.checkStringIsNull(appDao.getAppByAppCode(appcode).getAppCode()))
			throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.APP_CODE_NOT_REPEAT);
		// 应用编码和指标编码组合是唯一，如果已存在抛出异常
		if (null != rtcalcConfigDao.searchConfig(appcode, rtcalcConfig.getNodeId()))
			throw new BusiException(ErrorCodeEnum.M000, CheckPrompt.CONFIG_APP_NODE_REPEAT);
		rtcalcConfig.setAppCode(appcode);
		// 将entity转换成pojo
		com.pcitc.impl.rtcal.pojo.RtcalcConfig srtcalcConfig = ObjectConverter.entityConverter(rtcalcConfig,
				com.pcitc.impl.rtcal.pojo.RtcalcConfig.class);
		 rtcalcConfigDao.save(srtcalcConfig);
		log.debug("*** AlertPointServiceImpl END addAlertPoint ***");
		//RtcalcConfig rCcalcConfig = rtcalcConfigDao.searchConfig(appcode, srtcalcConfig.getNodeId());
		//return rCcalcConfig.getConfigId();
		return 1;
	}

}
