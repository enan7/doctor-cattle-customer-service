package com.doctor.cattle.customerservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.doctor.cattle.customerservice.security.interceptor.VerifyAccessInterceptor;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	

	@Autowired
	private VerifyAccessInterceptor accessInterceptor;
	 @Override
	   public void addInterceptors(InterceptorRegistry registry) {
	      registry.addInterceptor(accessInterceptor);
	   }

}
