package com.doctor.cattle.customerservice.dto.farm;


import java.util.List;

import com.doctor.cattle.customerservice.dto.company_owner.Company_Owner_DTO;
import com.doctor.cattle.customerservice.dto.farm_user.Farm_User_DTO;
import com.doctor.cattle.customerservice.entity.access.Access;

import lombok.Data;

@Data
public class Farm_DTO {
	
	private Long id;
	
	private String city;
	
	private String name;
	
	private int liveStock;

	private Access accessGranted = Access.GRANTED;

	private Company_Owner_DTO owner;
	
	private List<Farm_User_DTO> users;


}
