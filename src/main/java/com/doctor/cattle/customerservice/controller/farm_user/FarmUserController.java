package com.doctor.cattle.customerservice.controller.farm_user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doctor.cattle.customerservice.controller.request.farm_user.FarmUserInvertAccessRequest;
import com.doctor.cattle.customerservice.controller.request.farm_user.FarmUserRegistrationRequest;
import com.doctor.cattle.customerservice.controller.response.farm_user.FarmUserInvertAccessResponse;
import com.doctor.cattle.customerservice.controller.response.farm_user.FarmUserRegistrationResponse;
import com.doctor.cattle.customerservice.dto.farm_user.Farm_User_DTO;
import com.doctor.cattle.customerservice.exception.farm_user.FarmUserRegistrationException;
import com.doctor.cattle.customerservice.exception.user.UserNotFoundException;
import com.doctor.cattle.customerservice.service.farm_user.FarmUserService;

@RestController
@RequestMapping(value = "/api/customer-service/farm-user/")
public class FarmUserController {

	private static final Logger logger = LoggerFactory.getLogger(FarmUserController.class);

	@Autowired
	private FarmUserService farmUserService;

	@PostMapping(value = "register")
	public ResponseEntity<FarmUserRegistrationResponse> registerUser(@RequestBody FarmUserRegistrationRequest request) {

		FarmUserRegistrationResponse response = null;

		try {

			Farm_User_DTO dto = farmUserService.registerUser((Farm_User_DTO) request);
			if (null != dto) {
				response = new FarmUserRegistrationResponse(HttpStatus.OK, "Farm User Registered Successfully",
						dto.getId());
			}

		} catch (Exception e) {
			if (e instanceof FarmUserRegistrationException) {
				response = new FarmUserRegistrationResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null);
			} else {
				response = new FarmUserRegistrationResponse(HttpStatus.INTERNAL_SERVER_ERROR,
						"Unable to register farm user", null);
			}
			logger.error("Exception occurred while farm user registration " + e);
		}
		return new ResponseEntity<FarmUserRegistrationResponse>(response, response.getStatus());
	}
	
	@PutMapping(value="invertAccess")
	public ResponseEntity<FarmUserInvertAccessResponse> invertAccess(@RequestBody FarmUserInvertAccessRequest request){
		FarmUserInvertAccessResponse response = null;
		try {
			farmUserService.invertAccess((Farm_User_DTO)request);
			response = new FarmUserInvertAccessResponse(HttpStatus.OK, "Access Changed Successfully");
		} catch (UserNotFoundException e) {
			logger.error("Error in invert access "+e);
			response = new FarmUserInvertAccessResponse(HttpStatus.BAD_REQUEST, e.getMessage());
		} catch (Exception e) {
			logger.error("Error in invert access "+e);
			response = new FarmUserInvertAccessResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong");
		}
		return new ResponseEntity<FarmUserInvertAccessResponse>(response,response.getStatus());
	}

}
