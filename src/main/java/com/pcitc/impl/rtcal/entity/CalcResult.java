package com.pcitc.impl.rtcal.entity;

import java.io.Serializable;

import pcitc.imp.common.ettool.baseresrep.BaseResRep;

/**
 * @ClassName: CalcResult
 * @Description: 计算结果集
 * @author
 * @date 2016年12月23日10:22:58
 */

public class CalcResult extends BaseResRep implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nodeId; //节点ID/指标ID
	private String selectDate;//查询日期
	private String selectTime;//查询时间
	private String periodDateTime;//实时计算结果时间
	private Double periodValue;//实时计算结果
	private Double dayValue;//当天的平均值
	public String getNodeId() {
		return nodeId;
	}
	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}
	public String getSelectDate() {
		return selectDate;
	}
	public void setSelectDate(String selectDate) {
		this.selectDate = selectDate;
	}
	public String getSelectTime() {
		return selectTime;
	}
	public void setSelectTime(String selectTime) {
		this.selectTime = selectTime;
	}
	
	public String getPeriodDateTime() {
		return periodDateTime;
	}
	public void setPeriodDateTime(String periodDateTime) {
		this.periodDateTime = periodDateTime;
	}
	public Double getPeriodValue() {
		return periodValue;
	}
	public void setPeriodValue(Double periodValue) {
		this.periodValue = periodValue;
	}
	public Double getDayValue() {
		return dayValue;
	}
	public void setDayValue(Double dayValue) {
		this.dayValue = dayValue;
	}
	
	
}