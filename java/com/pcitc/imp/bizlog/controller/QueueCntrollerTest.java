package com.pcitc.imp.bizlog.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.pcitc.imp.bizlog.MainApp;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MainApp.class)
@WebAppConfiguration
public class QueueCntrollerTest extends MockMvcResultMatchers {

	@Autowired
	AppController qController;

	@Autowired
	WebApplicationContext context;

	private MockMvc mvcQueue;

	@Before
	public void setUp() throws Exception {
		System.out.println("测试开始加载");
		MockitoAnnotations.initMocks(this);
		mvcQueue = MockMvcBuilders.standaloneSetup(qController).build();
	}
	
	@After
	public void tearDown() throws Exception {
		System.out.println("测试结束卸载");
	}

	
//	public void getQueues() {
//
//		RequestBuilder request = null;
//		try {
//			request = MockMvcRequestBuilders.get("/queues").contentType(MediaType.APPLICATION_JSON_UTF8);
//
//			mvcQueue.perform(request).andExpect(status().isOk())
//					.andExpect(content().json(QueueCjBuilder.getQueuesResutCollectionStirng()));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	
//	public void getQueueByCode() {
//		RequestBuilder request = null;
//		request = MockMvcRequestBuilders.get("/queues/Queue1").contentType(MediaType.APPLICATION_JSON_UTF8);
//
//		try {
////			System.out.println("预期结果：");
////			System.out.println(QueueCjBuilder.getQueueByCodeResutCollectionStirng());
//			mvcQueue.perform(request).andExpect(status().isOk())
//					.andExpect(content().json(QueueCjBuilder.getQueueByCodeResutCollectionStirng()));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//	}
	
//	@Test
//	public void addQueue(){
//		RequestBuilder request = null;
//		try {
//			/* 1、post提交一个*/
//			request = MockMvcRequestBuilders.get("/bizlog/apps/search?&$skip=11&$top=20").contentType(MediaType.APPLICATION_JSON_UTF8);
//			mvcQueue.perform(request).andExpect(status().isOk());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
	
//	@Test
//	public void addQueue1(){
//		RequestBuilder request = null;
//		try {
//			/* 1、post提交一个*/
//			request = MockMvcRequestBuilders.get("/bizlog/apps/5678/logs/search?$skip=1&$top=20&topic=add").contentType(MediaType.APPLICATION_JSON_UTF8);
//			mvcQueue.perform(request).andExpect(status().isOk());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
	
//	@Test
//	public void addQueue(){
//		RequestBuilder request = null;
//		try {
//			/* 1、post提交一个*/
//			request = MockMvcRequestBuilders.post("/bizlog/apps").contentType(MediaType.APPLICATION_JSON_UTF8)
//					.content(CJBuilder.getAddAppParamCollectionStirng());
//			mvcQueue.perform(request).andExpect(status().is2xxSuccessful());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
	


//	@Test
//	public void addQueue1(){
//		RequestBuilder request = null;
//		try {
//			/*9、修改一个Topics 正确的*/
//			request = MockMvcRequestBuilders.put("/bizlog/apps/666").contentType(MediaType.APPLICATION_JSON_UTF8)
//					.content(CJBuilder.updateAppParamCollectionStirng());
//			mvcQueue.perform(request).andExpect(status().is2xxSuccessful());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
	
//	@Test
//	public void logQueue(){
//		RequestBuilder request = null;
//		try {
//			/* 1、post提交一个*/
//			request = MockMvcRequestBuilders.post("/bizlog/apps/5678/logs").contentType(MediaType.APPLICATION_JSON_UTF8)
//					.content(CJBuilder.getAddLogParamCollectionStirng());
//			mvcQueue.perform(request).andExpect(status().is2xxSuccessful());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
	
	
	@Test
	public void addQueue3(){
		RequestBuilder request = null;
		try {
			/* 1、post提交一个*/
			request = MockMvcRequestBuilders.post("/bizlog/apps").contentType(MediaType.APPLICATION_JSON_UTF8)
					.content(CJBuilder.getAddAppParamCollectionStirng());
			mvcQueue.perform(request).andExpect(status().isOk());
			
			/* 2、get查询一个*/
			request = MockMvcRequestBuilders.get("/bizlog/apps/tst1").contentType(MediaType.APPLICATION_JSON_UTF8);
			mvcQueue.perform(request).andExpect(status().is2xxSuccessful()).andExpect(content().json(CJBuilder.getStirngCollection1()));
			
			//断言 新增数据与查询数据结果一致 为正确
//			assertEquals(CJBuilder.getStirngCollection1(), request.toString());
			/* 3、delete删除一个*/
			request = MockMvcRequestBuilders.delete("/bizlog/apps/tst1").contentType(MediaType.APPLICATION_JSON_UTF8);
			mvcQueue.perform(request).andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
