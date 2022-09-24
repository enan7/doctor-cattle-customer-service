package com.doctor.cattle.customerservice.controller.response.login;

import org.springframework.http.HttpStatus;

import com.doctor.cattle.customerservice.controller.response.BaseResponse;
import com.doctor.cattle.customerservice.dto.user.UserDTO;

import lombok.Data;

@Data
public class UserLoginResponse extends BaseResponse {

	private UserDTO user;
	
	public UserLoginResponse(HttpStatus status, String message) {
		super(status, message);
	}

	public UserLoginResponse(HttpStatus status, String message,UserDTO user) {
		super(status, message);
		this.user = user;
	}
}
