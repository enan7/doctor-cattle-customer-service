package com.doctor.cattle.customerservice.dto.company;

import com.doctor.cattle.customerservice.dto.company_owner.Company_Owner_DTO;
import com.doctor.cattle.customerservice.entity.access.Access;

import lombok.Data;

@Data
public class CompanyDTO {

    private Long id;
	
	private String companyName;
		
	private Company_Owner_DTO owner;
	
	private Access accessGranted = Access.GRANTED;

	
}
