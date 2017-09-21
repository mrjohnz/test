package com.pcitc.impl.rtcal;

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

import com.pcitc.impl.rtcal.controller.RtcalcConfigController;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
public class RtcalcConfigTest extends MockMvcResultMatchers {
	@Autowired
	RtcalcConfigController controller;
	
	@Autowired
	WebApplicationContext context;
	private MockMvc mvc;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		mvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	// /*  
	@Test
	public void testRtcalcConfigController() throws Exception {
		// 测试testDataSourceController
		RequestBuilder request = null;

		// 1、 post提交一个
		/*request = MockMvcRequestBuilders.post("/realTimeCalcService/apps/66216/configs").contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(CJBuilder.addStringCollection());
		mvc.perform(request).andExpect(status().is2xxSuccessful());*/
		
		// 2、 get查一下configs列表
		
		/*request =MockMvcRequestBuilders.get("/realTimeCalcService/apps/66216/configs")
				.contentType(MediaType.APPLICATION_JSON_UTF8);
		mvc.perform(request).andExpect(status().is2xxSuccessful());*/
		 

		// 3、put更新对象
		///realTimeCalcService/apps/66216/configs/68
		/*request = MockMvcRequestBuilders.put("/realTimeCalcService/apps/AutoEquipment1/configs/2402")
		 		.contentType(MediaType.APPLICATION_JSON_UTF8)
		 		.content(CJBuilder.updateRtcalcConfigCollection());
 		mvc.perform(request).andExpect(status().is2xxSuccessful());*/
		

		// 4、 delete对象
		
		request = MockMvcRequestBuilders.delete("/realTimeCalcService/apps/EMS_OLAP_183/configs/1563")
				 .contentType(MediaType.APPLICATION_JSON_UTF8);
		 mvc.perform(request).andExpect(status().is2xxSuccessful());
		 
	   // 5、 get根据位号查询单条
		/*request = MockMvcRequestBuilders.get("/alertPoints/weihao1_1y").contentType(MediaType.APPLICATION_JSON_UTF8);
		
		mvc.perform(request).andExpect(status().is2xxSuccessful());*/
		
	}
}
