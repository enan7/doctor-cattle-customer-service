package com.doctor.cattle.customerservice.dto.company_owner;

import java.util.List;

import com.doctor.cattle.customerservice.dto.company.CompanyDTO;
import com.doctor.cattle.customerservice.dto.farm.Farm_DTO;
import com.doctor.cattle.customerservice.entity.access.Access;
import com.doctor.cattle.customerservice.entity.role.Role;

import lombok.Getter;
import lombok.Setter;

public class Company_Owner_DTO {
	
	@Getter
	@Setter
	private Long id;
	
	@Getter
	@Setter
	private String userName;
	
	@Getter
	@Setter
	private String password;
	
	@Getter
	@Setter
	private String firstName;
	
	@Getter
	@Setter
	private String lastName;
	
	
	@Getter
	@Setter
	private CompanyDTO company;
	
	@Getter
	@Setter
	private List<Farm_DTO> farms;
	
	@Getter
	private Role role = Role.OWNER;

	@Getter
	@Setter
	private Access accessGranted = Access.GRANTED;

	@Getter
	@Setter
	private String phoneNumber;

}
