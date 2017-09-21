package com.pcitc.impl.rtcal.dao;

import java.util.List;

import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.pcitc.impl.rtcal.pojo.App;

public interface AppDao extends JpaRepository<App, Long>{
	
	/**
	 * @Title: getApps
	 * @Description: 查询所有App
	 * @return List<App>
	 */
	@Query("from App ")
	List<com.pcitc.impl.rtcal.pojo.App> getApps();

	/**
	 * @Title: getAppByCode
	 * @Description: 根据条件查询App
	 * @param code 唯一条件
	 * @return App
	 */
	@Query("from App a where appCode = :appCode")
	com.pcitc.impl.rtcal.pojo.App getAppByAppCode(@Param("appCode") String appCode);

	/**
	 * @Description: 判断是否存在指定App
	 * @param code
	 * @return App
	 */
	@Query("from App a where appCode = :appCode")
	com.pcitc.impl.rtcal.pojo.App queryByAppCode(@Param("appCode") String appCode);
	
	/**
	 * @Title: updateAppByCode
	 * @Description: 更新App
	 * @param code
	 * @param name
	 * @param updateUser
	 * @param updateTime
	 */
	@Modifying
	@Transactional
	@Query(" update App set  appName = :appName, updateUser = :updateUser,updateTime = sysdate where appCode = :appCode")
	void updateAppByAppCode(@Param("appCode") String appCode, 
			@Param("appName") String appName, 
			@Param("updateUser") String updateUser);

	/**
	 * @Title: deleteAppByCode
	 * @Description: 删除指定App
	 * @param code 唯一条件
	 */
	@Transactional
	@SQLDelete(sql = "delete from App where appCode = :appCode")
	void deleteAppByAppCode(@Param("appCode") String appCode);

	@Query("from App a where appCode in (:appCode)")
	List<com.pcitc.impl.rtcal.pojo.App> getAppsByCodes(@Param("appCode") List<String> appCodes);
}
