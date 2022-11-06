package com.doctor.cattle.customerservice.controller.farm;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.doctor.cattle.customerservice.controller.response.BaseResponse;
import com.doctor.cattle.customerservice.controller.response.farm.GetFarmsResponse;
import com.doctor.cattle.customerservice.controller.response.livestock.GetLiveStockResponse;
import com.doctor.cattle.customerservice.dto.farm.Farm_DTO;
import com.doctor.cattle.customerservice.exception.farm.FarmNotFoundException;
import com.doctor.cattle.customerservice.exception.user.UserNotFoundException;
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
	
	@GetMapping(value = "getFarms/{userId}")
	public ResponseEntity<GetFarmsResponse> getFarms(@PathVariable(value = "userId", required = true) Long userId) {
		List<Farm_DTO> farms;
		GetFarmsResponse response = null;
		try {
			farms = farmService.getAllFarms(userId);
			response = new GetFarmsResponse(HttpStatus.OK, "Farms Found", farms);
		} catch (UserNotFoundException e) {
			response = new GetFarmsResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null);
			logger.error("Exception in get Farms : " + e.getMessage());

		} catch (Exception e) {
			response = new GetFarmsResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to get farms", null);
			logger.error("Exception in get Farms : " + e.getMessage());
		}
		return new ResponseEntity<GetFarmsResponse>(response, response.getStatus());
	}
	
	@DeleteMapping(value="/{farmId}",produces="application/json")
	public ResponseEntity<BaseResponse> deleteFarm(@PathVariable(value="farmId") Long farmId){
		BaseResponse response = null;
		try {
			Farm_DTO farm = farmService.deleteFarm(farmId);
			if (null !=farm) {
			response = new BaseResponse(HttpStatus.OK,"Farm Deleted Successfully");
			}else {
				response = new BaseResponse(HttpStatus.BAD_REQUEST,"Farm Not Found");
			}
			} catch (FarmNotFoundException e) {
				response = new BaseResponse(HttpStatus.BAD_REQUEST,e.getMessage());
				logger.error("Error in Delete Farm : "+e.toString());

		}
		return new ResponseEntity<BaseResponse>(response,response.getStatus());
		
	}
}
