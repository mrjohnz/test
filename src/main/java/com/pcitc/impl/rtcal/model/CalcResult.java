package com.pcitc.impl.rtcal.model;

import java.io.Serializable;

import pcitc.imp.common.ettool.Annotaion.QueryContract;
import pcitc.imp.common.ettool.Annotaion.ResourceContract;
import pcitc.imp.common.ettool.Annotaion.ResourceMember;
import pcitc.imp.common.ettool.baseresrep.BaseResRep;

/**
 * @ClassName: CalcResult
 * @Description: 计算结果集
 * @author
 * @date 2016年12月23日10:22:58
 */
@SuppressWarnings("serial")

@QueryContract(href = "http://service-root/realTimeCalcService/apps/equipmentOperationAnalysis/configs/calcResults", 
				rel = "Select By NodeId And Time Interval", 
				prompt = "按节点ID/指标ID集合、时间段查询")
@QueryContract(href = "http://service-root/realTimeCalcService/apps/equipmentOperationAnalysis/configs/calcResults", 
				rel = "Select Yesterday Average Value", 
				prompt = "按节点ID/指标ID集合、时间点查询")
@ResourceContract(ReadOnly=true)
public class CalcResult extends BaseResRep implements Serializable {

	private String nodeId; //节点ID/指标ID
	
	@ResourceMember(InQueries = "Select Yesterday Average Value", Name = "$selectDate", OnlyQuery = true)
	private String $selectDate;//查询日期

	private String selectDate;//查询日期

	private String selectTime;//查询时间

	private String periodDateTime;//实时计算结果时间

	private Double periodValue;//实时计算结果

	private Double dayValue;//当天的平均值
	
	@ResourceMember(InQueries = "Select By NodeId And Time Interval,Select Yesterday Average Value", Name = "$nodeIdList",OnlyQuery = true)
	private String nodeIdList;
	
	@ResourceMember(InQueries = "Select By NodeId And Time Interval",  Name = "$selectStartDateTime", OnlyQuery = true)
	private String selectStartDateTime;
	
	@ResourceMember(InQueries = "Select By NodeId And Time Interval", Name = "$selectEndDateTime", OnlyQuery = true)
	private String selectEndDateTime;

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

	public String getNodeIdList() {
		return nodeIdList;
	}

	public void setNodeIdList(String nodeIdList) {
		this.nodeIdList = nodeIdList;
	}

	public String getSelectStartDateTime() {
		return selectStartDateTime;
	}

	public void setSelectStartDateTime(String selectStartDateTime) {
		this.selectStartDateTime = selectStartDateTime;
	}

	public String getSelectEndDateTime() {
		return selectEndDateTime;
	}

	public void setSelectEndDateTime(String selectEndDateTime) {
		this.selectEndDateTime = selectEndDateTime;
	}

	public String get$selectDate() {
		return $selectDate;
	}

	public void set$selectDate(String $selectDate) {
		this.$selectDate = $selectDate;
	}
	
	
}