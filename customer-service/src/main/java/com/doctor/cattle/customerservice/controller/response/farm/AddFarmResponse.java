package com.doctor.cattle.customerservice.controller.response.farm;

import org.springframework.http.HttpStatus;

import com.doctor.cattle.customerservice.controller.response.BaseResponse;

import lombok.Data;

@Data
public class AddFarmResponse extends BaseResponse {

	private Long id;
	
	public AddFarmResponse(HttpStatus status, String message,Long id) {
		super(status, message);
		this.id = id;
	}

	
}
