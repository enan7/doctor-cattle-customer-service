package com.doctor.cattle.customerservice.controller.response.livestock;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.doctor.cattle.customerservice.controller.response.BaseResponse;
import com.doctor.cattle.customerservice.dto.farm.animal.AnimalDTO;

import lombok.Getter;


public class GetLiveStockResponse extends BaseResponse {

	@Getter
	private List<AnimalDTO> animals;
	
	public GetLiveStockResponse() {
		
	}
	public GetLiveStockResponse(HttpStatus status, String message,List<AnimalDTO> animals) {
		super(status, message);
		this.animals = animals;
	}
	

}
