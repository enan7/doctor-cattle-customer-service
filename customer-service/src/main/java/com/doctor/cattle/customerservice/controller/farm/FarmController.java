package com.doctor.cattle.customerservice.controller.farm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.doctor.cattle.customerservice.controller.response.livestock.GetLiveStockResponse;
import com.doctor.cattle.customerservice.exception.farm.FarmNotFoundException;
import com.doctor.cattle.customerservice.service.farm.FarmService;

@RestController
@RequestMapping(value = "/api/customer-service/farm/")
public class FarmController {
	
	private static final Logger logger = LoggerFactory.getLogger(FarmController.class);

	@Autowired
	private FarmService farmService;

	@GetMapping(value="get-animals/{farmId}")
	public ResponseEntity<GetLiveStockResponse> getLiveStock(@PathVariable(value="farmId") Long farmId){
		
		GetLiveStockResponse response = null;
		try {
			
			response = farmService.getLiveStock(farmId);
			
		}catch(FarmNotFoundException e) {
			response = new GetLiveStockResponse(HttpStatus.BAD_REQUEST,e.getMessage(), null);
			logger.error(e.getMessage());
		}catch (Exception e) {
			response = new GetLiveStockResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to get live stock",null);
			logger.error("Exception in get-live-stock "+ e);
		}
		
		return new ResponseEntity<GetLiveStockResponse>(response,response.getStatus());
		
	}
}
