package com.pcitc.impl.rtcal.model;

import java.io.Serializable;
import java.util.Date;

import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;


/**
 * 
 * @ClassName: App
 * @Description: 业务应用集合
 * @author
 * @date 2016年12月22日15:59:27
 *
 */
@SuppressWarnings("serial")
@ResourceContract(ReadOnly = false)
@QueryContract(href = "", rel = "search", prompt = "查询")
public class App extends BaseResRep implements Serializable {
	
	//应用id
	@ResourceMember( OnlyQuery = true)
	private Integer Id;
	// App的唯一标识，不重复。
	private String appCode;
	// App的描述，如（操作管理，能源管理）。
	private String appName;
	//创建人
	private String createUser;
	//创建时间。仅用于显示，创建时不需要提供
	private Date createTime;
	//最后修改人。更新时提供
	private String updateUser;
	//最后修改时间。仅用于显示，创建时不需要提供
	private Date updateTime;
	
	@ResourceMember(InQueries = "search", OnlyQuery = true, Name = "$codeList")
	private String codeList;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
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

	public String getCodeList() {
		return codeList;
	}

	public void setCodeList(String codeList) {
		this.codeList = codeList;
	}

	

}
