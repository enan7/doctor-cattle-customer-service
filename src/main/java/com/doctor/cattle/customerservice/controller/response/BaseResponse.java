package com.doctor.cattle.customerservice.controller.response;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class BaseResponse {
	
	private HttpStatus status;
	private String message;
	
	public BaseResponse() {
		// TODO Auto-generated constructor stub
	}
	public BaseResponse(HttpStatus status,String message) {
		this.status = status;
		this.message = message;
	}
	
	
}
