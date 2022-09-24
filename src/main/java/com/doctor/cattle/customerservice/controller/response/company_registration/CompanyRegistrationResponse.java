package com.doctor.cattle.customerservice.controller.response.company_registration;

import org.springframework.http.HttpStatus;

import com.doctor.cattle.customerservice.controller.response.BaseResponse;

public class CompanyRegistrationResponse extends BaseResponse {	
	
	public CompanyRegistrationResponse(HttpStatus status,String message) {
		super(status,message);
	}
}
