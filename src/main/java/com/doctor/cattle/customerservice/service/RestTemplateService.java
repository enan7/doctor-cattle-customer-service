package com.doctor.cattle.customerservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestTemplateService {

	@Autowired
	private RestTemplate restTemplate;
	
	protected <T extends Object> T getForObject(String serviceName,String url,Class<T> returnType) {
		return   (T) restTemplate.getForObject("https://"+serviceName+url, returnType);

	}
}
