package com.pcitc.impl.rtcal.entity;

import java.io.Serializable;

import pcitc.imp.common.ettool.baseresrep.BaseResRep;

/**
 * @ClassName: CalcResult
 * @Description: 计算结果集
 * @author
 * @date 2016年12月23日10:22:58
 */

public class Result extends BaseResRep implements Serializable {

	private static final long serialVersionUID = 1L;
	private String periodDateTime;//实时计算结果时间
	private Double periodValue;//实时计算结果
	private Double dayValue;//当天的平均值
	private Double dayCumulative;//当天累计（不做输出）
	
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
	public String getPeriodDateTime() {
		return periodDateTime;
	}
	public void setPeriodDateTime(String periodDateTime) {
		this.periodDateTime = periodDateTime;
	}
	public Double getDayCumulative() {
		return dayCumulative;
	}
	public void setDayCumulative(Double dayCumulative) {
		this.dayCumulative = dayCumulative;
	}
	
	
}