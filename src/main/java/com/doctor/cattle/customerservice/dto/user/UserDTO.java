package com.doctor.cattle.customerservice.dto.user;

import java.util.List;

import com.doctor.cattle.customerservice.dto.company.CompanyDTO;
import com.doctor.cattle.customerservice.dto.farm.Farm_DTO;
import com.doctor.cattle.customerservice.entity.access.Access;
import com.doctor.cattle.customerservice.entity.role.Role;

import lombok.Data;

@Data
public class UserDTO {
	
	private String userName;
	private Role role;
	private String roleName;
	private Long roleId;
	private Boolean isOwner;
	private Long id;
	private String firstName;
	private String lastName;
	private List<Farm_DTO> farms;
	private CompanyDTO company;
	private String jwt_Token;
	private Access accessGranted = Access.GRANTED;
	private String phoneNumber;
	

}
