package com.pcitc.impl.rtcal.model;

import java.io.Serializable;
import java.util.Date;

import pcitc.imp.common.ettool.baseresrep.BaseResRep;


/**
 * @ClassName: RtcalcConfig
 * @Description: 配置信息
 * @author 
 * @date 2016年12月28日13:50:39
 *
 */
public class RtcalcConfigS extends BaseResRep implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 502839972815904757L;
	//配置Id
	private String configId;
	//唯一业务编码
	private String appCode;
	//节点ID/指标ID
	private String nodeId;
	//计算公式。目前支持加减乘除运算。
	private String formula;
	//计算间隔，单位秒。要求大于0的整数，为保证计算的可行性，建议大于60。
	private String calcFrequency;
	//执行偏差，单位秒，用于取历史计算时使用，取实时值计算时不填
	private String deviation;
	//下一次计算时间，用于定时计算使用
	private String nextTime;
	//创建人
	private String createUser;
	//创建时间。仅用于显示，创建时不需要提供
	private String createTime;
	//最后修改人。更新时提供
	private String updateUser;
	//最后修改时间。仅用于显示，创建时不需要提供
	private String updateTime;
	private String nodeIdList;
	public String getConfigId() {
		return configId;
	}
	public void setConfigId(String configId) {
		this.configId = configId;
	}
	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getFormula() {
		return formula;
	}
	public void setFormula(String formula) {
		this.formula = formula;
	}
	public String getCalcFrequency() {
		return calcFrequency;
	}
	public void setCalcFrequency(String calcFrequency) {
		this.calcFrequency = calcFrequency;
	}
	public String getDeviation() {
		return deviation;
	}
	public void setDeviation(String deviation) {
		this.deviation = deviation;
	}
	public String getNextTime() {
		return nextTime;
	}
	public void setNextTime(String nextTime) {
		this.nextTime = nextTime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getNodeIdList() {
		return nodeIdList;
	}
	public void setNodeIdList(String nodeIdList) {
		this.nodeIdList = nodeIdList;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
}

