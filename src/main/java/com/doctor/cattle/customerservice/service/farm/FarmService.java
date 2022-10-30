package com.doctor.cattle.customerservice.service.farm;

import java.util.List;

import com.doctor.cattle.customerservice.controller.response.livestock.GetLiveStockResponse;
import com.doctor.cattle.customerservice.dto.farm.Farm_DTO;
import com.doctor.cattle.customerservice.dto.farm.livestock.LiveStockDTO;
import com.doctor.cattle.customerservice.exception.farm.FarmNotFoundException;
import com.doctor.cattle.customerservice.exception.farm.FarmRegistrationException;
import com.doctor.cattle.customerservice.exception.user.UserNotFoundException;

public interface FarmService {

	public Farm_DTO addFarm(Farm_DTO farmDTO) throws FarmRegistrationException;
	
	public Boolean isFarmExists(Long farmId,boolean updaateLiveStock);
	
	public Boolean isFarmExists(Long farmId);

	public GetLiveStockResponse getLiveStock(Long farmId) throws FarmNotFoundException;
	
	public List<Farm_DTO> getAllFarms (Long userId) throws UserNotFoundException;
}
