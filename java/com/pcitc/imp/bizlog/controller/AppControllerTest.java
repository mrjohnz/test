package com.pcitc.imp.bizlog.controller;

import java.net.URISyntaxException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AppControllerTest extends TestCase {

	@Autowired
	private TestRestTemplate restTemplate;

//	// 查询所有
	@Test
	public void testGetApps() throws URISyntaxException {

		String body = this.restTemplate.getForObject("/bizlog/apps", String.class);
		//断言 body结果与断言结果一致 为正确
		//assertEquals(CJBuilder.getStirngCollection(), body);
		System.out.println(body);
	}
//
	// 条件查询
	@Test
	public void testGetApps2() {
		try {
			String url = "/bizlog/apps/666";
			String body = this.restTemplate.getForObject(url, String.class);
			//断言 body结果为666 与断言结果一致 为正确
//			assertEquals(CJBuilder.getStirngCollection(), body);
		} catch (Exception e) {
		}
	}
//
//	// 条件查询 不存在
//	@Test
//	public void testGetApps24() {
//		try {
//			String url = "/bizlog/apps/66asd6";
//			String body = this.restTemplate.getForObject(url, String.class);
//			
//			StringBuffer sb = new StringBuffer();
//			sb.append("{\r\n");
//			sb.append("  \"collection\" : {\r\n");
//			sb.append("    \"version\" : \"1.0\",\r\n");
//			sb.append("    \"href\" : \"/bizlog/apps/66asd6\",\r\n");
//			sb.append("    \"error\" : {\r\n");
//			sb.append("      \"title\" : \"\",\r\n");
//			sb.append("      \"code\" : \"\",\r\n");
//			sb.append("      \"message\" : \"请确认编码是否存在，并且编码不允许为空，只允许由数字、26个小写英文字母或者下划线组成的字符串组成。\"\r\n");
//			sb.append("    }\r\n");
//			sb.append("  }\r\n");
//			sb.append("}");
//			//断言 body结果与断言结果一致 为正确
//			assertEquals(sb.toString(), body);
//		} catch (Exception e) {
//		}
//	}
//
//	// 条件查询 带特殊字符 err
//	@Test
//	public void testGetApps21() {
//		try {
//			String url = "/bizlog/apps/666@#￥";
//			String body = this.restTemplate.getForObject(url, String.class);
//			
//			StringBuffer sb = new StringBuffer();
//			sb.append("{\r\n");
//			sb.append("  \"collection\" : {\r\n");
//			sb.append("    \"version\" : \"1.0\",\r\n");
//			sb.append("    \"href\" : \"/bizlog/apps/666@\",\r\n");
//			sb.append("    \"error\" : {\r\n");
//			sb.append("      \"title\" : \"\",\r\n");
//			sb.append("      \"code\" : \"M000\",\r\n");
//			sb.append("      \"message\" : \"应用编码只允许由数字、26个小写英文字母或者下划线组成的字符串！\"\r\n");
//			sb.append("    }\r\n");
//			sb.append("  }\r\n");
//			sb.append("}");
//			//断言 body结果与断言结果一致 为正确
//			assertEquals(sb.toString(), body);
//		} catch (Exception e) {
//		}
//	}
//
//	// 条件查询 code 为空格 err
//	@Test
//	public void testGetApps22() {
//		try {
//			String url = "/bizlog/apps/  ";
//			String body = this.restTemplate.getForObject(url, String.class);
//
//			StringBuffer sb = new StringBuffer();
//			sb.append("{\r\n");
//			sb.append("  \"collection\" : {\r\n");
//			sb.append("    \"version\" : \"1.0\",\r\n");
//			sb.append("    \"href\" : \"/bizlog/apps/%20%20\",\r\n");
//			sb.append("    \"error\" : {\r\n");
//			sb.append("      \"title\" : \"\",\r\n");
//			sb.append("      \"code\" : \"M000\",\r\n");
//			sb.append("      \"message\" : \"应用编码为空\"\r\n");
//			sb.append("    }\r\n");
//			sb.append("  }\r\n");
//			sb.append("}");
//			//断言 body结果与断言结果一致 为正确
//			assertEquals(sb.toString(), body);
//		} catch (Exception e) {
//		}
//	}
//
//	// 分页 查询
//	@Test
//	public void testGetApps11() {
//		try {
//			String url = "/bizlog/apps?$skip=1 &$top=22 ";
//			String body = this.restTemplate.getForObject(url, String.class);
//			//断言 body结果与断言结果一致 为正确
//			assertEquals(CJBuilder.getStirngCollection(), body);
//		} catch (Exception e) {
//		}
//	}
//
//	 分页 查询 带小数
//	@Test
//	public void testGetApps12() {
//		try {
//			String url = "/bizlog/apps?$skip=200&$top=1.11";
//			String body = this.restTemplate.getForObject(url, String.class);
//
//			StringBuffer sb = new StringBuffer();
//			sb.append("{\r\n");
//			sb.append("  \"collection\" : {\r\n");
//			sb.append("    \"version\" : \"1.0\",\r\n");
//			sb.append("    \"href\" : \"/bizlog/apps\",\r\n");
//			sb.append("    \"error\" : {\r\n");
//			sb.append("      \"title\" : \"\",\r\n");
//			sb.append("      \"code\" : \"M000\",\r\n");
//			sb.append("      \"message\" : \"TOP 只可为非0正整数\"\r\n");
//			sb.append("    }\r\n");
//			sb.append("  }\r\n");
//			sb.append("}");
//			//断言 body结果与断言结果一致 为正确
//			assertEquals(sb.toString(), body);
//		} catch (Exception e) {
//		}
//	}
//
	// codelist 查询
//	@Test
//	public void testGetApps1() {
//		try {
//			String url = "/bizlog/apps?$codeList=autocode1,autocode2";
//			String body = this.restTemplate.getForObject(url, String.class);
//			//断言 body结果与断言结果一致 为正确
//			assertEquals(CJBuilder.getStirngCollection(), body);
//		} catch (Exception e) {
//		}
//	}
//
//	// codelist 查询 不存在
//	@Test
//	public void testGetApps13() {
//		try {
//			String url = "/bizlog/apps?$skip=0&$top=10&$codeList=5678as,!#@#@#";
//			String body = this.restTemplate.getForObject(url, String.class);
//			//断言 body结果为不存在 返回空 为正确
//			assertNull(body,null); 
//		} catch (Exception e) {
//		}
//	}
//
//	// codelist 查询 空
//	@Test
//	public void testGetApps111() {
//		try {
//			String url = "/bizlog/apps?$codeList= , ";
//			String body = this.restTemplate.getForObject(url, String.class);
//			//断言 body结果与断言结果一致 为正确
//			assertEquals(CJBuilder.getStirngCollection(), body);
//		} catch (Exception e) {
//		}
//	}
//
//	// 传递参数不存在带空格
//	@Test
//	public void testGetApps14() {
//		try {
//			String url = "/bizlog/apps?$codeLists= asd";
//			String body = this.restTemplate.getForObject(url, String.class);
//			//断言 body结果与断言结果一致 为正确
//			assertEquals(CJBuilder.getStirngCollection(), body); 
//		} catch (Exception e) {
//		}
//	}
//
//	// 测试 已有 删除不了的
//	@Test
//	public void testDeleteApp7() {
//		try {
//			//查询log
//			String url = "/bizlog/apps/666/logs";
//			String body = this.restTemplate.getForObject(url, String.class);
//			
//			//断言 body结果存在  那么就删除不了当前app 必须先删除 log 在删除 app
//			assertNotNull("判断是否为空",body); 
//			
//			String url1 = "/bizlog/apps/666";
//			this.restTemplate.delete(url1, "");
//		} catch (Exception e) {
//		}
//	}
//
//	// 删除 不存在应用
//	@Test
//	public void testDeleteApp4() {
//		try {
//
//			String url = "/bizlog/apps/asdf2345";
//			this.restTemplate.delete(url, "");
//			
//			//查询
//			String url2 = "/bizlog/apps/asdf2345";
//			String body = this.restTemplate.getForObject(url2, String.class);
//			
//			StringBuffer sb = new StringBuffer();
//			sb.append("{\r\n");
//			sb.append("  \"collection\" : {\r\n");
//			sb.append("    \"version\" : \"1.0\",\r\n");
//			sb.append("    \"href\" : \"/bizlog/apps/asdf2345\",\r\n");
//			sb.append("    \"error\" : {\r\n");
//			sb.append("      \"title\" : \"\",\r\n");
//			sb.append("      \"code\" : \"\",\r\n");
//			sb.append("      \"message\" : \"请确认编码是否存在，并且编码不允许为空，只允许由数字、26个小写英文字母或者下划线组成的字符串组成。\"\r\n");
//			sb.append("    }\r\n");
//			sb.append("  }\r\n");
//			sb.append("}");
//			//断言 body结果与断言结果一致 为正确
//			assertEquals(sb.toString(), body);
//		} catch (Exception e) {
//		}
//	}
//
//	// 删除 带特殊字符
//	@Test
//	public void testDeleteApp5() {
//		try {
//
//			String url = "/bizlog/apps/asdf@@@@@2345";
//			this.restTemplate.delete(url, "");
//			
//			//查询
//			String url2 = "/bizlog/apps/asdf@@@@@2345";
//			String body = this.restTemplate.getForObject(url2, String.class);
//			
//			StringBuffer sb = new StringBuffer();
//			sb.append("{\r\n");
//			sb.append("  \"collection\" : {\r\n");
//			sb.append("    \"version\" : \"1.0\",\r\n");
//			sb.append("    \"href\" : \"/bizlog/apps/asdf@@@@@2345\",\r\n");
//			sb.append("    \"error\" : {\r\n");
//			sb.append("      \"title\" : \"\",\r\n");
//			sb.append("      \"code\" : \"M000\",\r\n");
//			sb.append("      \"message\" : \"应用编码只允许由数字、26个小写英文字母或者下划线组成的字符串！\"\r\n");
//			sb.append("    }\r\n");
//			sb.append("  }\r\n");
//			sb.append("}");
//			//断言 body结果与断言结果一致 为正确
//			assertEquals(sb.toString(), body);
//		} catch (Exception e) {
//		}
//	}
//-------------------------------------------------start-------------------------------------------------
//	// 可以删除 每次修改
//	@Test
//	public void testDeleteApp1() {
//		try {
//			//新增数据
//			testDeleteApp11();
//			
//			//查询出新增数据
//			testDeleteApp12();
//			
//			//删除数据
//			testDeleteApp13();
//			
//			//查询删除的数据
//			String url2 = "/bizlog/apps/tst1";
//			String body2 = this.restTemplate.getForObject(url2, String.class);
//			
//			StringBuffer sb = new StringBuffer();
//			sb.append("{\r\n");
//			sb.append("  \"collection\" : {\r\n");
//			sb.append("    \"version\" : \"1.0\",\r\n");
//			sb.append("    \"href\" : \"/bizlog/apps/tst1\",\r\n");
//			sb.append("    \"error\" : {\r\n");
//			sb.append("      \"title\" : \"\",\r\n");
//			sb.append("      \"code\" : \"\",\r\n");
//			sb.append("      \"message\" : \"请确认编码是否存在，并且编码不允许为空，只允许由数字、26个小写英文字母或者下划线组成的字符串组成。\"\r\n");
//			sb.append("    }\r\n");
//			sb.append("  }\r\n");
//			sb.append("}");
//			//断言 body结果与断言结果一致 为正确
//			assertEquals(sb.toString(), body2);
//		} catch (Exception e) {
//		}
//	}
//	//删除调用
//	public void testDeleteApp11() {
//		try {
//			//新增数据
//			String url = "/bizlog/apps";
//			String string = this.restTemplate.postForObject(url, CJBuilder.getAddAppParamCollectionStirng(),
//					String.class);
//		} catch (Exception e) {
//		}
//	}
//	//删除调用 查询数据 确认新增成功
//	public void testDeleteApp12() {
//		try {
//			String body = this.restTemplate.getForObject("/bizlog/apps/tst1", String.class);
//		} catch (Exception e) {
//		}
//	}
//	
//	//删除调用
//	public void testDeleteApp13() {
//		try {
//			//删除数据
//			this.restTemplate.delete("/bizlog/apps/tst1", "");
//		} catch (Exception e) {
//		}
//	}
//-----------------------------------------------end---------------------------------------------------
	
//-----------------------------------------------start---------------------------------------------------	
	// 新增 每次修改
	//TODO 第一步先单独 执行新增数据，第二步在执行查询 并进行断言，第三步删除测试数据
//	@Test
	public void testAddApps() {
		try {
			//新增数据  
//			testAddApps10();
			
//			//查询出新增数据
//			String url1 = "/bizlog/apps/tst1";
//			String body = this.restTemplate.getForObject(url1, String.class);
//			
//			//断言 新增数据与查询数据结果一致 为正确
//			assertEquals(CJBuilder.getStirngCollection1(), body);
//			//删除测试数据
			testAddApps11();
		} catch (Exception e) {
		}
	}
//	//新增调用
//	public void testAddApps10() {
//		try {
//			//新增数据
//			String url = "/bizlog/apps";
//			this.restTemplate.postForObject(url, CJBuilder.getAddAppParamCollectionStirng(),String.class);
//		} catch (Exception e) {
//		}
//	}
	//新增调用 后删除
	public void testAddApps11() {
		try {
			//删除测试数据
			this.restTemplate.delete("/bizlog/apps/autocode6", "");
		} catch (Exception e) {
		}
	}
//-----------------------------------------------end---------------------------------------------------
//	// 新增 code重复
//	@Test
//	public void testAddApps1() {
//		try {
//			String url = "/bizlog/apps";
//
//			String body = this.restTemplate.postForObject(url, CJBuilder.getAddAppParamCollectionStirng123(),
//					String.class);
//		} catch (Exception e) {
//		}
//	}
//
//	// 新增 code 为空
//	@Test
//	public void testAddApps2() {
//		try {
//			String url = "/bizlog/apps";
//
//			this.restTemplate.postForObject(url, CJBuilder.getAddAppParamCollectionStirng456(),
//					String.class);
//		} catch (Exception e) {
//		}
//	}
//
//	// 新增 name 为空
//	@Test
//	public void testAddApps3() {
//		try {
//			String url = "/bizlog/apps";
//
//			this.restTemplate.postForObject(url, CJBuilder.getAddAppParamCollectionStirng789(),
//					String.class);
//		} catch (Exception e) {
//		}
//	}
//
//	// 新增 code 特殊字符
//	@Test
//	public void testAddApps4() {
//		try {
//			String url = "/bizlog/apps";
//
//			this.restTemplate.postForObject(url, CJBuilder.getAddAppParamCollectionStirng901(),
//					String.class);
//		} catch (Exception e) {
//		}
//	}
//-------------------------------------------start----------------------------------------------------
//	// 修改 带中文
//	@Test
//	public void testUpdateApp() {
//		try {
//			
//			//新增数据
//			String url = "/bizlog/apps";
//			String string = this.restTemplate.postForObject(url, CJBuilder.getAddAppParamCollectionStirng(),
//					String.class);
//			
//			//查询出新增数据
//			String url1 = "/bizlog/apps/tst1";
//			String body = this.restTemplate.getForObject(url1, String.class);
//			
//			//断言 新增数据与查询数据结果一致 为正确
//			assertEquals(CJBuilder.getAddAppParamCollectionStirng(), body);
//			
//			//修改数据
//			String url2 = "/bizlog/apps/666";
//			this.restTemplate.put(url2, CJBuilder.updateAppParamCollectionStirng(), String.class);
	//		
//			//查询出新增数据
//			String url3 = "/bizlog/apps/tst1";
//			String body3 = this.restTemplate.getForObject(url3, String.class);
//			
//			//断言 查询数据与修改数据结果不一致 为正确 所以该断言为错 是正确
//			assertEquals(body, body3);
//			
//			//删除数据
//			this.restTemplate.delete("/bizlog/apps/tst1", "");
//			
//		} catch (Exception e) {
//		}
//	}
//-------------------------------------------end----------------------------------------------------
	// 修改 name 为空
//	@Test
//	public void testUpdateApp1() {
//		try {
//			//新增数据
//			String url = "/bizlog/apps";
//			this.restTemplate.postForObject(url, CJBuilder.getAddAppParamCollectionStirng(),
//					String.class);
//			
//			//查询出新增数据
//			String url1 = "/bizlog/apps/tst1";
//			String body = this.restTemplate.getForObject(url1, String.class);
//			
//			//断言 新增数据与查询数据结果一致 为正确
//			assertEquals(CJBuilder.getStirngCollection1(), body);
//			
//			String url2 = "/bizlog/apps/tst1";
//			this.restTemplate.put(url2, CJBuilder.updateAppParamCollectionStirng123(), String.class);
//			
//			//查询出修改数据
//			String url3 = "/bizlog/apps/tst1";
//			String body3 = this.restTemplate.getForObject(url3, String.class);
//			
//			//断言 查询数据与修改数据结果一致 为正确 因name为空 无法被修改
//			assertEquals(body, body3);
//			
//			//删除数据
//			this.restTemplate.delete("/bizlog/apps/tst1", "");
//		} catch (Exception e) {
//		}
//	}
//
//	// 修改 name 特殊字符
//	@Test
//	public void testUpdateApp2() {
//		try {
//			//新增数据
//			String url = "/bizlog/apps";
//			String string = this.restTemplate.postForObject(url, CJBuilder.getAddAppParamCollectionStirng(),
//					String.class);
//			
//			//查询出新增数据
//			String url1 = "/bizlog/apps/tst1";
//			String body = this.restTemplate.getForObject(url1, String.class);
//			
//			//断言 新增数据与查询数据结果一致 为正确
//			assertEquals(string, body);
//			
//			String url2 = "/bizlog/apps/tst1";
//			this.restTemplate.put(url2, CJBuilder.updateAppParamCollectionStirng123(), String.class);
//			
//			//查询出修改数据
//			String url3 = "/bizlog/apps/tst1";
//			String body3 = this.restTemplate.getForObject(url3, String.class);
//			
//			//断言 查询数据与修改数据结果一致 为正确 因name为特殊字符 无法被修改
//			assertEquals(body, body3);
//			
//			//删除数据
//			this.restTemplate.delete("/bizlog/apps/tst1", "");
//		} catch (Exception e) {
//		}
//	}
//	
//	// 修改 code 不存在
//	@Test
//	public void testUpdateApp3() {
//		try {
//			//新增数据
//			String url = "/bizlog/apps";
//			String string = this.restTemplate.postForObject(url, CJBuilder.getAddAppParamCollectionStirng(),
//					String.class);
//			
//			//查询出新增数据
//			String url1 = "/bizlog/apps/tst1";
//			String body = this.restTemplate.getForObject(url1, String.class);
//			
//			//断言 新增数据与查询数据结果一致 为正确
//			assertEquals(string, body);
//			
//			String url2 = "/bizlog/apps/tst1";
//			this.restTemplate.put(url2, CJBuilder.updateAppParamCollectionStirng123(), String.class);
//			
//			//查询出修改数据
//			String url3 = "/bizlog/apps/tst1234qwe";
//			String body3 = this.restTemplate.getForObject(url3, String.class);
//			
//			//断言 code结果为不存在 返回为空 为正确
//			assertNull(body3,null); 
//			
//			//删除数据
//			this.restTemplate.delete("/bizlog/apps/tst1", "");
//			
//		} catch (Exception e) {
//		}
//	}
}
