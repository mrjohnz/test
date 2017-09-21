package com.pcitc.impl.rtcal.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.impl.rtcal.pojo.RtcalcConfig;


public interface RtcalcConfigDao extends JpaRepository<RtcalcConfig, Long> {
	
	/**
	 * 根据appcode 查询
	 * @param appCode
	 * @return
	 */
	@Query("from RtcalcConfig where appCode = :appCode")
	public List<RtcalcConfig> searchConfigByAppCode(@Param("appCode") String appCode);
	
	/**
	 * 
	 * @param appCode
	 * @return
	 */
	@Query("from RtcalcConfig where appCode = :appCode and configId = :configId")
	public List<RtcalcConfig> searchConfigByACCI(@Param("appCode") String appCode,@Param("configId") int configId);
	/**
	 * 
	 * @param appCode
	 * @return
	 *//*
	@Query("from RtcalcConfig where configId = :configId")
	public RtcalcConfig searchConfigByNode(@Param("configId") int configId);
	*/
	/**
	 * 根据appcode 查询
	 * @param appCode
	 * @return
	 */
	@Query("from RtcalcConfig where appCode = :appCode and nodeId in (:nodeId)")
	public List<RtcalcConfig> searchConfigByAppCode(@Param("appCode") String appCode,@Param("nodeId")List<Integer> nodeId);
	
	/**
	 * 查询所有的
	 * @return
	 */
	@Query("from RtcalcConfig")
	public List<RtcalcConfig> searchConfig();
	
	/**
	 * 根据configId查询
	 * @param configId
	 * @return
	 */
	@Query("from RtcalcConfig t where configId = :configId")
	public RtcalcConfig findConfigByConfigId(@Param("configId") int configId);
	
	/**
	 * 
	 * @return
	 */
	@Query("from RtcalcConfig where appCode = :appCode and nodeId = :nodeId ")
	public RtcalcConfig searchConfig(@Param("appCode") String appCode,@Param("nodeId") int nodeId);
	
	/**
	 * 
	 * @param appCode
	 * @param configId
	 * @return
	 */
	@Query("from RtcalcConfig where appCode = :appCode and configId = :configId ")
	public RtcalcConfig searchConfigs(@Param("appCode") String appCode,@Param("configId") int configId);
	
	/**
	 * 根据configIdm删除
	 * @param configId
	 */
	@Transactional
	@SQLDelete(sql = "delete from RtcalcConfig where configId = :configId")
	public void deleteRtcalcConfigByConfigId(@Param("configId") int configId );
	
//	@Transactional
//	@SQLDelete(sql = "delete from AlertPoint where tagCode = :tagCode")
//	public void deleteAlertPointBytagCode(@Param("tagCode") String tagCode);

	/**
	 * @Title: updateAlertPointBytagCode
	 * @Description: TODO(根据位号修改设备告警)
	 * @param tagCode 位号
	 * @param subscribeFlag
	 * @param pickInterval
	 * @param appCode
	 * @param appCode
	 * @param updateUser
	 * @param updateTime
	 */

	@Modifying
	@Transactional
	//@Query("update RtcalcConfig set nodeId = :nodeId, formula = :formula, calcFrequency = :calcFrequency, deviation = :deviation, nextTime = :nextTime, crtUuserId = :crtUuserId, crtUserName = :crtUserName, crt_Date = :crt_Date, mntUserId = :mntUserId, mntUserName = :mntUserName, mntDate = :mntDate, des = :des, sort_num = :sort_num, version = :version, modeltype = :modeltype  where configId = :configId")
	/*public void updateRtcalcConfigByconfigId
			(@Param("configId") int configId,
			@Param("nodeId") int nodeId,
			@Param("formula") String formula,
			@Param("calcFrequency") int  calcFrequency,
			@Param("deviation") int deviation,
			@Param("nextTime") Date nextTime,
			@Param("crtUuserId") String crtUuserId,
			@Param("crtUserName") String crtUserName,
			@Param("crt_Date") Date crt_Date,
			@Param("mntUserId") String  mntUserId,
			@Param("mntUserName") String mntUserName,
			@Param("mntDate") Date mntDate,
			@Param("des") String des,
			@Param("sort_num") int sort_num,
			@Param("version") int version,
			@Param("modeltype") int modeltype);*/
	@Query("update RtcalcConfig set nodeId = :nodeId, formula = :formula, calcFrequency = :calcFrequency, deviation = :deviation, nextTime = :nextTime, updateUser = :updateUser, updateTime = sysdate where configId= :configId")
	public void updateRtcalcConfigByconfigId
				(@Param("configId") int configId,
				@Param("nodeId") int nodeId,
				@Param("formula") String formula,
				@Param("calcFrequency") int  calcFrequency,
				@Param("deviation") int deviation,
				@Param("nextTime") Date nextTime,
				@Param("updateUser") String updateUser);

	

}

