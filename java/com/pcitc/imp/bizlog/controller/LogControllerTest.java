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
public class LogControllerTest extends TestCase {

	@Autowired
	private TestRestTemplate restTemplate;

	// 查询 所有
//	@Test
//	public void testGetApps() throws URISyntaxException {
//
//		String body = this.restTemplate.getForObject("/bizlog/apps/6616/logs", String.class);
////		断言 body结果与断言结果一致 为正确
////		assertEquals(CJBuilder.getAddLogParamCollectionStirngq(), body);
//		System.out.println(body);
//	}
//
//	// 查询 不存在
//	@Test
//	public void testGetApps2() {
//		String url = "/bizlog/apps/563278/logs";
//		String string = this.restTemplate.getForObject(url, String.class);
//	}
//
//	// 查询 特殊字符
//	@Test
//	public void testGetApps3() {
//		String url = "/bizlog/apps/  #￥%56rth78/logs";
//		String string = this.restTemplate.getForObject(url, String.class);
//	}
//	
//	// 查询 特殊字符
//	@Test
//	public void testGetApps4() {
//		String url = "/bizlog/apps/  null/logs";
//		String string = this.restTemplate.getForObject(url, String.class);
//	}
//
//	// 条件查询 topic
//	@Test
//	public void testGetLogsByCon() throws URISyntaxException {
//
//		String url1 = "/bizlog/apps/666/logs";
//		String string1 = this.restTemplate.getForObject(url1, String.class);
//		System.out.println(string1);
//	}
//	
//	// skip -1
//	@Test
//	public void testGetLogsByCon11() throws URISyntaxException {
//
//		String url1 = "/bizlog/apps/perfcode97/logs?$skip=11&$top=22";
//		String string1 = this.restTemplate.getForObject(url1, String.class);
//		System.out.println(string1);
//	}
//	
//	// skip 1.1
//	@Test
//	public void testGetLogsByCon12() throws URISyntaxException {
//
//		String url1 = "/bizlog/apps/5678/logs/search?$skip=1.1";
//		String string1 = this.restTemplate.getForObject(url1, String.class);
//		System.out.println(string1);
//	}
//	
//	// top 1.1
//	@Test
//	public void testGetLogsByCon13() throws URISyntaxException {
//
//		String url1 = "/bizlog/apps/5678/logs/search?$top=0";
//		String string1 = this.restTemplate.getForObject(url1, String.class);
//		System.out.println(string1);
//	}
//	// startTime 
//	@Test
//	public void testGetLogsByCon14() throws URISyntaxException {
//
//		String url1 = "/bizlog/apps/5678/logs/search?$startTime=201609291644000";
//		String string1 = this.restTemplate.getForObject(url1, String.class);
//		System.out.println(string1);
//	}
//	
//	// endTime 
//	@Test
//	public void testGetLogsByCon15() throws URISyntaxException {
//
//		String url1 = "/bizlog/apps/5678/logs/search?$endTime=201609291644000";
//		String string1 = this.restTemplate.getForObject(url1, String.class);
//		System.out.println(string1);
//	}
//
//	// 条件查询 endTime
//	@Test
//	public void testGetLogsByCon1() throws URISyntaxException {
//
//		String url1 = "/bizlog/apps/5678/logs/search?$skip=1&$top=20&$endTime=201609291644000";
//		String string1 = this.restTemplate.getForObject(url1, String.class);
//		System.out.println("testGetLogsByCon+++++++++++++++++++++++++++++++");
//		System.out.println(string1);
//	}
//
//	// 条件查询 usercode
//	@Test
//	public void testGetApps11() {
//		try {
//			String url = "/bizlog/apps/5678/logs/search?$skip=1&$top=20&usercode=whw";
//			String string = this.restTemplate.getForObject(url, String.class);
//		} catch (Exception e) {
//		}
//	}
//
//	// 条件查询 username
//	@Test
//	public void testGetApps12() {
//		try {
//			String url = "/bizlog/apps/5678/logs/search?$skip=1&$top=20&username=pp";
//			String string = this.restTemplate.getForObject(url, String.class);
//		} catch (Exception e) {
//		}
//	}
//
//	// 条件查询 operation
//	@Test
//	public void testGetApps13() {
//		try {
//			String url = "/bizlog/apps/5678/logs/search?$skip=1&$top=20&operation=er";
//			String string = this.restTemplate.getForObject(url, String.class);
//		} catch (Exception e) {
//		}
//	}
//
//	// 条件查询 operobj
//	@Test
//	public void testGetApps14() {
//		try {
//			String url = "/bizlog/apps/5678/logs/search?$skip=1&$top=20&operobj=rt";
//			String string = this.restTemplate.getForObject(url, String.class);
//		} catch (Exception e) {
//		}
//	}
//
//	// 条件查询 description
//	@Test
//	public void testGetApps15() {
//		try {
//			String url = "/bizlog/apps/5678/logs/search?$skip=1&$top=20&description=we";
//			String string = this.restTemplate.getForObject(url, String.class);
//		} catch (Exception e) {
//		}
//	}
//
//	// 条件查询 timestamp
//	@Test
//	public void testGetApps16() {
//		try {
//			String url = "/bizlog/apps/5678/logs/search?$skip=1&$top=20&timestamp=201609291644000";
//			String string = this.restTemplate.getForObject(url, String.class);
//		} catch (Exception e) {
//		}
//	}
//
//	// 条件查询 分页小数
//	@Test
//	public void testGetApps7() {
//		try {
//			String url = "/bizlog/apps/5678/logs/search?$skip=1.2&$top=10.2";
//			String string = this.restTemplate.getForObject(url, String.class);
//		} catch (Exception e) {
//		}
//	}
//
//	// 条件查询 时间 err
//	@Test
//	public void testGetApps8() {
//		try {
//			String url = "/bizlog/apps/5678/logs/search?$skip=1&$top=20&$startTime=201609291644000&$endTime=10.2";
//			String string = this.restTemplate.getForObject(url, String.class);
//		} catch (Exception e) {
//		}
//	}
//
//	// 新增
	@Test
	public void testAddLogs() {
		try {
			String url = "/bizlog/apps/autocode1/logs";
			this.restTemplate.postForObject(url, CJBuilder.getAddLogParamCollectionStirng(), String.class);
		} catch (Exception e) {
		}
	}
//	
//	// 新增 code 特殊字符
//	@Test
//	public void testAddLogs11() {
//		try {
//			String url = "/bizlog/apps/56@@@@9/logs";
//			this.restTemplate.postForObject(url, CJBuilder.getAddLogParamCollectionStirng(), String.class);
//		} catch (Exception e) {
//		}
//	}
//
//	// 新增 clientip 空
//	@Test
//	public void testAddLogs1() {
//		try {
//			String url = "/bizlog/apps/666/logs";
//			this.restTemplate.postForObject(url, CJBuilder.getAddLogParamCollectionStirng1(), String.class);
//		} catch (Exception e) {
//		}
//	}
//
//	// 新增 usercode 空
//	@Test
//	public void testAddLogs3() {
//		try {
//			String url = "/bizlog/apps/666/logs";
//			this.restTemplate.postForObject(url, CJBuilder.getAddLogParamCollectionStirng2(), String.class);
//		} catch (Exception e) {
//		}
//	}
//
//	// 新增 username 空
//	@Test
//	public void testAddLogs4() {
//		try {
//			String url = "/bizlog/apps/666/logs";
//			this.restTemplate.postForObject(url, CJBuilder.getAddLogParamCollectionStirng3(), String.class);
//		} catch (Exception e) {
//		}
//	}
//
//	// 新增 operation 空
//	@Test
//	public void testAddLogs5() {
//		try {
//			String url = "/bizlog/apps/5678/logs";
//			this.restTemplate.postForObject(url, CJBuilder.getAddLogParamCollectionStirng4(), String.class);
//		} catch (Exception e) {
//		}
//	}
//
//	// 新增 timestamp 空
//	@Test
//	public void testAddLogs6() {
//		try {
//			String url = "/bizlog/apps/666/logs";
//			this.restTemplate.postForObject(url, CJBuilder.getAddLogParamCollectionStirng5(), String.class);
//		} catch (Exception e) {
//		}
//	}
//
	// 新增 timestamp 格式错误
//	@Test
//	public void testAddLogs7() {
//		try {
//			String url = "/bizlog/apps/666/logs";
//			this.restTemplate.postForObject(url, CJBuilder.getAddLogParamCollectionStirng6(), String.class);
//		} catch (Exception e) {
//		}
//	}
//
//	// 新增 clientip 格式错误
//	@Test
//	public void testAddLogs8() {
//		try {
//			String url = "/bizlog/apps/5678/logs";
//			this.restTemplate.postForObject(url, CJBuilder.getAddLogParamCollectionStirng7(), String.class);
//		} catch (Exception e) {
//		}
//	}
//
	// 测试删除 调用
//	@Test
//	public void testAddLogs2() {
//		try {
//			String url = "/bizlog/apps/autocode1/logs";
//			this.restTemplate.delete(url, "");
//		} catch (Exception e) {
//		}
//	}
//
//	// 测试删除 空code
//	@Test
//	public void testAddLogs21() {
//		try {
//			String url = "/bizlog/apps/663sd46/logs";
//			this.restTemplate.delete(url, "");
//		} catch (Exception e) {
//		}
//	}
//
//	// 测试删除 空code
//	@Test
//	public void testAddLogs22() {
//		try {
//			this.restTemplate.delete("/bizlog/apps/  @@@@ /logs", "");
//		} catch (Exception e) {
//		}
//	}
	
}
