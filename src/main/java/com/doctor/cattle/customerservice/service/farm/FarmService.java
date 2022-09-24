package com.doctor.cattle.customerservice.service.farm;

import com.doctor.cattle.customerservice.controller.response.livestock.GetLiveStockResponse;
import com.doctor.cattle.customerservice.dto.farm.Farm_DTO;
import com.doctor.cattle.customerservice.dto.farm.livestock.LiveStockDTO;
import com.doctor.cattle.customerservice.exception.farm.FarmNotFoundException;
import com.doctor.cattle.customerservice.exception.farm.FarmRegistrationException;

public interface FarmService {

	public Farm_DTO addFarm(Farm_DTO farmDTO) throws FarmRegistrationException;
	
	public boolean isFarmExists(Long farmId);
	
	public GetLiveStockResponse getLiveStock(Long farmId) throws FarmNotFoundException;
}
