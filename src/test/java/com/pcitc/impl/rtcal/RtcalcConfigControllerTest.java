package com.pcitc.impl.rtcal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.pcitc.impl.rtcal.controller.RTCBuilder;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RtcalcConfigControllerTest extends TestCase {
	@Autowired
	private TestRestTemplate restTemplate;
	
	// 可以删除 每次修改
	@Test
	public void testDeleteRtcalcConfig() {
		try {
			this.restTemplate.delete("/realTimeCalcService/apps/66216/configs/1", "");
		} catch (Exception e) {
		}
	}
	
	// 修改 带中文
/*	@Test
	public void testUpdateApp() {
		try {
			String url = "/realTimeCalcService/apps/2";
			this.restTemplate.put(url, CJBuilder.updateRtcalcConfigCollectionStirng(), String.class);
		} catch (Exception e) {
		}
	}*/
	@Test
	public void testAdd(){
		try {
			//新增数据
			String url = "/realTimeCalcService/apps/AAA6116/configs";
			String string = this.restTemplate.postForObject(url, RTCBuilder.getAddAppParamCollectionStirng1(),
					String.class);
			System.out.println(string);
		} catch (Exception e) {
		}
	}
}
