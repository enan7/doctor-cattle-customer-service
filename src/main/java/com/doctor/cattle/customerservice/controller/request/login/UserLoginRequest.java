package com.doctor.cattle.customerservice.controller.request.login;

import lombok.Data;

@Data
public class UserLoginRequest {
	private String userName;
	private String password;
}
