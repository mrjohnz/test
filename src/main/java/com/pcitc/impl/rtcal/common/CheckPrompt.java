package com.pcitc.impl.rtcal.common;

/**
 * @Description: 提示信息
 * @author 
 * @date 2016年10月14日 上午10:27:00 
 *
 */
public class CheckPrompt {
	/** APP提示 */
	public static final String APP_CODE_NULL = "appCode编码不能为空，且长度不得超过25";
	public static final String APP_NAME_NULL = "应用名称长度不得超过100";
	public static final String APP_USER_NAME_NULL = "名字长度不能超过25";
	public static final String APP_CODELIST_NULL = "CODELIST 为空";
	public static final String APP_CODE_CHECKMATCHER = "请确认编码是否存在，并且编码不允许为空，只允许由数字、26个英文字母或者下划线组成的字符串组成。";
	public static final String APP_NAME_CHECKMATCHER = "应用名称只允许由中文、数字、26个英文字母或者下划线组成的字符串组成。";
	public static final String APP_USER_NAME_CHECKMATCHER = "创建人名称只允许由中文、数字、26个英文字母或者下划线组成的字符串组成。";
	public static final String APP_CODE_REPEAT = "appCode编码已存在!";
	public static final String APP_CODE_NOT_REPEAT = "appCode编码不存在!";
	public static final String APP_CODE_REPEAT_CONFIG = "此应用存在配置数据，不可删除!";
	public static final String APP_CODE_NOT = "appCode不可更改!";
	public static final String APP_CODE_ERROR = "appCodet长度过大!";

	
	/** 通用提示 */
	public static final String QUERY_RESULT = "查询结果为空";
	public static final String CHECKMATCHER = "appCode编码只允许由数字、26个英文字母或者下划线组成的字符串！";
	public static final String APP_NOT_EXIST = "查询失败，请确认表是否存在，条件是否正确。";
	public static final String Date_NoT_FORMAT = "请输入正确的时间格式！";
	public static final String Date_NoT_FORMAT_2 = "请输入正确的时间段！";
	public static final String CRE_MNT_NAME = "新增时，创建人和修改人要相同!";
	public static final String INPUT_RULE = "只允许由数字、26个英文字母或者下划线组成！";
	public static final String ID_NOT = "输入了非数字！";
	public static final String INT_NOT = "只能是大于0的整数！";
	public static final String CHARSET_INVALID = "字符集错误！";
	public static final String DATE_INVALID = "请填写正确的日期！";
	public static final String STR_LENGTH_BIG = "长度过大!";
	public static final String RESULTS_NOT_EXIST = "你查询的信息不存在!";
	public static final String DATE_ERRO = "日期应为14位数字，如：20170101010101！";
	public static final String DATE_STYLE_ERRO = "输入错误！日期只允许为数字！";
	public static final String DATETIME_ERRO = "日期应为8位数字，如：20170101！";
	/**insert */
	public static final String INSERT_NOTNULL = "编码，名称，创建人,修改人名称不能为空！";
	public static final String UPDETE_NOTNULL = "编码，名称，修改人名称不能为空！";
	
	/**RtcalcConfi提示*/
	public static final String CONFIG_CODE_NULL = "应用编码不能为空!";
	public static final String CONFIG_NODE_NULL = "nodeId不能为空!";
	public static final String CONFIG_APP_NODE_REPEAT = "应用编码appCode下的nodeId已经存在！";
	public static final String CONFIG_FORMULA_NULL = "计算公式不能为空!";
	public static final String CONFIG_CALCFREQUENCY_NULL = "计算间隔不能为空!";
	public static final String CONFIG_NEXTTIME_NULL = "nextTime不能为空!";
	public static final String CONFIG_USER_NULL = "创建人不能为空!";
	public static final String CONFIG_UPDATEUSER_NULL = "修改人不能为空!";
	public static final String CONFIG_CALCFREQUENCY_MIN = "calcFrequency不能小于1!";
	public static final String CONFIG_FOUMULA_ERROR = "计算公式长度过大!";
	public static final String CONFIG_NAME_ERROR = "创建人或修改人名称只能由汉字或英文字母组成！";
	public static final String CONFIG_DEVIATION_ERROR = "DEVIATION输入错误，非数字或者数值过大！";
	public static final String CONFIG_DATE_ERROR = "nextTime格式错误，正确的格式如：2010/12/01 00:00:00 或2010-12-01 00:00:00";
	public static final String CONFIG_NEXTTIME_MIN = "下次时间应该大于当前时间！";
	public static final String CONFIG_NODEID_ERROR = "nodeId输入错误，只能输入大于0的整数并且不宜过大！";
	public static final String CONFIG_CALCFREQUENCY_ERROR = "calcFrequency输入错误，只能输入大于0的整数并且不宜过大！";
	public static final String CONFIG_NODEID_NOT = "nodeId只能是大于0的整数！";
	public static final String CONFIG_CONFIGID_NOT = "configId只能是数字并且不能为空！";
	public static final String INT_NOT_VAID = "configId输入无效,输入了非整数或者数值过大!";
	public static final String CONFIG_NODEID_ILLEGAL = "nodeId输入无效,输入了非整数或者数值过大!";
	public static final String  QUERY = "查询失败";
	public static final String INSERT = "新增失败";
	public static final String DELETE = "删除失败";
	public static final String UPDATE = "修改失败";
	/*CalcResults*/
	public static final String CONFIG_NODEList_NULL = "nodeIdList不能为空!";
}
