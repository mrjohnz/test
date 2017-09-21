package com.pcitc.impl.rtcal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import pcitc.imp.common.ettool.baseresrep.BaseResRep;


/**
 * 
 * @ClassName: App
 * @Description: 业务应用集合
 * @author
 * @date 2016年12月22日15:59:18
 *
 */
@Entity
@Table(name = "T_RTCALC_APP")
@DynamicInsert(true)
public class App extends BaseResRep implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Column(name = "APP_ID")
	private Integer id;

	@Id
	// App的唯一标识，不重复。
	@Column(name = "APP_CODE")
	private String appCode;
	// App的描述，如（操作管理，能源管理）。
	@Column(name = "APP_NAME")
	private String appName;

	// 创建人
	@Column(name = "CRT_USER_NAME")
	private String createUser;
	// 创建时间。仅用于显示，创建时不需要提供
	@Column(name = "CRT_DATE")
	private Date createTime;
	// 最后修改人。更新时提供
	@Column(name = "MNT_USER_NAME")
	private String updateUser;
	// 最后修改时间。仅用于显示，创建时不需要提供
	@Column(name = "MNT_DATE")
	private Date updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}


}
