package com.doctor.cattle.customerservice.controller.response.farm;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.doctor.cattle.customerservice.controller.response.BaseResponse;
import com.doctor.cattle.customerservice.dto.farm.Farm_DTO;

import lombok.Data;

@Data
public class GetFarmsResponse extends BaseResponse {
	
private List<Farm_DTO> farms;

public GetFarmsResponse(HttpStatus status, String message,List<Farm_DTO> farms) {
		super(status, message);
		this.farms = farms;
		// TODO Auto-generated constructor stub
	}

}
