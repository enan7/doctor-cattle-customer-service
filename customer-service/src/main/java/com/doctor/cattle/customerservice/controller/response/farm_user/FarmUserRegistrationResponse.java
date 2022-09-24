package com.doctor.cattle.customerservice.controller.response.farm_user;

import org.springframework.http.HttpStatus;

import com.doctor.cattle.customerservice.controller.response.BaseResponse;

import lombok.Data;

@Data
public class FarmUserRegistrationResponse extends BaseResponse {

	private Long userId;
	public FarmUserRegistrationResponse(HttpStatus status, String message,Long userId) {
		super(status, message);
		this.userId = userId;
	}

}
