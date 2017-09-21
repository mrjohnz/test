package com.pcitc.impl.rtcal.controller;

import java.net.URISyntaxException;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class StudentControllerTest extends TestCase {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void testUpdataStudent(){
		String url2 = "/a/students/1";
		try {
			this.restTemplate.put(url2, RTCBuilder.updateStudentParamCollectionStirng(), String.class);
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	public void testAddStudent() {
		try {
			//新增数据
			String url = "/a/students";
			String string = this.restTemplate.postForObject(url, RTCBuilder.getAddStudentParamCollectionStirng(),
					String.class);
			System.out.println(string);
		} catch (Exception e) {
		}
	}
	@Test
	public void testDeleteStudent() {
		try {
			//删除数据
			this.restTemplate.delete("/a/students/2", "");
		} catch (Exception e) {
		}
	}

}
