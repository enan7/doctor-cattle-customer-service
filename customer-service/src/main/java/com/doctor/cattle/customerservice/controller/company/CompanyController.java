package com.doctor.cattle.customerservice.controller.company;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.doctor.cattle.customerservice.controller.request.company_registration.CompanyRegistrationRequest;
import com.doctor.cattle.customerservice.controller.request.farm.AddFarmRequest;
import com.doctor.cattle.customerservice.controller.response.company_registration.CompanyRegistrationResponse;
import com.doctor.cattle.customerservice.controller.response.farm.AddFarmResponse;
import com.doctor.cattle.customerservice.dto.company.CompanyDTO;
import com.doctor.cattle.customerservice.dto.farm.Farm_DTO;
import com.doctor.cattle.customerservice.exception.company.CompanyRegisterationException;
import com.doctor.cattle.customerservice.exception.farm.FarmRegistrationException;
import com.doctor.cattle.customerservice.service.company.CompanyService;
import com.doctor.cattle.customerservice.service.farm.FarmService;

@RestController
@RequestMapping(value = "/api/customer-service/company/")
public class CompanyController {

	private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);

	@Autowired
	private CompanyService companyService;

	@Autowired
	private FarmService farmService;

	@PostMapping(value = "register")
	public ResponseEntity<CompanyRegistrationResponse> registerCompany(
			@RequestBody CompanyRegistrationRequest request) {
		CompanyRegistrationResponse response = null;
		try {
			CompanyDTO company = companyService.registerCompany((CompanyDTO) request);
			if (null != company) {
			response = new CompanyRegistrationResponse(HttpStatus.OK, "Company Registered Successfully");
			}
			} catch (CompanyRegisterationException e) {

			response = new CompanyRegistrationResponse(HttpStatus.BAD_REQUEST, e.getMessage());

			logger.error("Exception occured in Company Registration " + e);

		} catch (Exception e) {
			response = new CompanyRegistrationResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to register Customer");

			logger.error("Exception occured in Company Registration " + e);

		}
		return new ResponseEntity<CompanyRegistrationResponse>(response, HttpStatus.OK);

	}

	@PostMapping(value = "add-farm")
	public ResponseEntity<AddFarmResponse> addFarm(@RequestBody AddFarmRequest request) {
		AddFarmResponse response = null;
		try {
			Farm_DTO dto = farmService.addFarm((Farm_DTO) request);
			if (null != dto) {
				response = new AddFarmResponse(HttpStatus.OK, "Farm Added Successfully", dto.getId());
			} else {
				response = new AddFarmResponse(HttpStatus.BAD_REQUEST, "Unable to Register Farm", null);
			}
		} catch (Exception e) {
			if (e instanceof FarmRegistrationException) {
				response = new AddFarmResponse(HttpStatus.BAD_REQUEST, e.getMessage(), null);
				logger.error("Error in Farm Registration " + e);
			} else {
				// TODO Auto-generated catch block
				response = new AddFarmResponse(HttpStatus.BAD_REQUEST, "Unable to Register Farm", null);
				logger.error("Error in Farm Registration " + e);
			}
		}
		return new ResponseEntity<AddFarmResponse>(response,response.getStatus());
	}
	
	@GetMapping(value="farm-exists/{id}")
	public ResponseEntity<Boolean> isFarmExists(@PathVariable("id") Long id){
		boolean exists = farmService.isFarmExists(id);
		return new ResponseEntity<>(exists,HttpStatus.OK);
		
	}

}
