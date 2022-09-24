package com.doctor.cattle.customerservice.dto.farm_user;

import com.doctor.cattle.customerservice.dto.farm.Farm_DTO;
import com.doctor.cattle.customerservice.entity.access.Access;
import com.doctor.cattle.customerservice.entity.role.Role;

import lombok.Data;
import lombok.Getter;

@Data
public class Farm_User_DTO {

    private Long id;
	
	private String userName;
	
	private String password;
	
	private String firstName;
	
	private String lastName;
	
	private String registrationNumber;
	
	private Boolean isOwner;
	
	@Getter
	private Role role = Role.USER;
	
	private Farm_DTO farm;
	
	private Access accessGranted = Access.GRANTED;


}
