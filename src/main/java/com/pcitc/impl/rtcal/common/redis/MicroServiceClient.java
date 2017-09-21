package com.pcitc.impl.rtcal.common.redis;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class MicroServiceClient {

	public static String postRequest(String url,String collection){
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		
		HttpEntity<String> formEntity = new HttpEntity<String>(collection, headers);
		
		return restTemplate.postForObject(url, formEntity, String.class);
	}
	
	public static String getRequest(String url){
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
		headers.setContentType(type);
		headers.add("Accept", MediaType.APPLICATION_JSON.toString());
		
		return restTemplate.getForObject(url, String.class);
	}
}
