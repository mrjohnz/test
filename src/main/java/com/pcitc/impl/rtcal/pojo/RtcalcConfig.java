package com.pcitc.impl.rtcal.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

@Entity
@Table(name = "T_RTCALC_CONFIG")
@DynamicInsert(true)
public class RtcalcConfig implements Serializable{
	
	private static final long serialVersionUID = 1L;
	//配置Id
	@Id
	@Column(name = "CONFIG_ID")
	private int configId;
	//唯一业务编码
	@Column(name = "APP_CODE")
	private String appCode;	
	//节点ID/指标ID
	@Column(name = "NODE_ID")
	private int nodeId;	
	//计算公式。目前支持加减乘除运算。
	@Column(name = "FORMULA")
	private String formula;
	//计算间隔，单位秒。要求大于0的整数，为保证计算的可行性，建议大于60。
	@Column(name = "CALC_FREQUENCY")
	private int calcFrequency;
	//执行偏差，单位秒，用于取历史计算时使用，取实时值计算时不填
	@Column(name = "DEVIATION")
	private int deviation;	
	//下一次计算时间，用于定时计算使用
	@Column(name = "NEXT_TIME")
	private Date nextTime;
	//创建人
	@Column(name = "CRT_USER_NAME")
	private String createUser;
	//创建时间。仅用于显示，创建时不需要提供
	@Column(name = "CRT_DATE")
	private Date createTime;
	
	//最后修改人。更新时提供
	@Column(name = "MNT_USER_NAME")
	private String updateUser;
	//最后修改时间。仅用于显示，创建时不需要提供
	@Column(name = "MNT_DATE")
	private Date updateTime;
	
	public int getConfigId() {
		return configId;
	}

	public void setConfigId(int configId) {
		this.configId = configId;
	}
	
	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public int getNodeId() {
		return nodeId;
	}
	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}
	public String getFormula() {
		return formula;
	}
	public void setFormula(String formula) {
		this.formula = formula;
	}
	public int getCalcFrequency() {
		return calcFrequency;
	}
	public void setCalcFrequency(int calcFrequency) {
		this.calcFrequency = calcFrequency;
	}
	public int getDeviation() {
		return deviation;
	}
	public void setDeviation(int deviation) {
		this.deviation = deviation;
	}
	public Date getNextTime() {
		return nextTime;
	}
	public void setNextTime(Date nextTime) {
		this.nextTime = nextTime;
	}
	
	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}

