package com.doctor.cattle.customerservice.adapter.farm_user_adapter;

import com.doctor.cattle.customerservice.adapter.farm_adapter.FarmAdapter;
import com.doctor.cattle.customerservice.dto.farm_user.Farm_User_DTO;
import com.doctor.cattle.customerservice.entity.farm_user.FarmUser;
import com.doctor.cattle.customerservice.entity.role.Role;

public class FarmUserAdapter {
	
	private final String DEFAULT_PASSWORD = "1";
	
	public FarmUser getFarmUser(Farm_User_DTO farmUserDto) {
		
		if (farmUserDto == null) {
			return null;
		}
		
		FarmUser farmUser = new FarmUser();
		
		farmUser.setId(farmUserDto.getId());
	
		if (null!= farmUserDto.getFarm()) {
		FarmAdapter farmAdapter = new FarmAdapter();
		farmUser.setFarm(farmAdapter.getFarm(farmUserDto.getFarm()));
		}
		
		farmUser.setFirstName(farmUserDto.getFirstName());
		farmUser.setLastName(farmUserDto.getLastName());
		farmUser.setPhoneNumber(farmUserDto.getPhoneNumber());
		farmUser.setIsOwner(false);
		if(farmUserDto.getIsOwner() != null) {
			farmUser.setIsOwner(farmUserDto.getIsOwner());
		}
		farmUser.setUserName(farmUserDto.getUserName());
		farmUser.setPassword(DEFAULT_PASSWORD);
		if(farmUserDto.getPassword() != null) {
			if(farmUserDto.getPassword().trim().length() != 0) {
				farmUser.setPassword(farmUserDto.getPassword());	
			}
		}
		farmUser.setRole(Role.USER);
		return farmUser;
	}

	
	public Farm_User_DTO getFarmUserDTO (FarmUser user) {
		
		if (user == null) {
			return null;
		}
		
		Farm_User_DTO dto = new Farm_User_DTO();
		
		dto.setId(user.getId());
		FarmAdapter adapter = new FarmAdapter();
		dto.setFarm(adapter.getFarmDTO(user.getFarm()));
		dto.setFirstName(user.getFirstName());
		dto.setLastName(user.getLastName());
		dto.setIsOwner(user.getIsOwner());
		dto.setRole(user.getRole());
		dto.setUserName(user.getUserName());
		dto.setAccessGranted(user.getaccessGrantedd());
		return dto;
	}
}
