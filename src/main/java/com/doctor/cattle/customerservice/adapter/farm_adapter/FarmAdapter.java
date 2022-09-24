package com.doctor.cattle.customerservice.adapter.farm_adapter;

import java.util.ArrayList;
import java.util.List;

import com.doctor.cattle.customerservice.adapter.company_owner_adapter.CompanyOwnerAdapter;
import com.doctor.cattle.customerservice.adapter.farm_user_adapter.FarmUserAdapter;
import com.doctor.cattle.customerservice.dto.farm.Farm_DTO;
import com.doctor.cattle.customerservice.entity.farm.Farm;
import com.doctor.cattle.customerservice.entity.farm_user.FarmUser;

public class FarmAdapter {

	public Farm getFarm(Farm_DTO farmDto) {
		
		if (farmDto == null) {
		   return null;	
		}
		Farm farm = new Farm();
		farm.setId(farmDto.getId());
		farm.setCity(farmDto.getCity());
		farm.setLiveStock(farmDto.getLiveStock());
		farm.setName(farmDto.getName());
		CompanyOwnerAdapter companyOwnerAdapter = new CompanyOwnerAdapter();
		farm.setOwner(companyOwnerAdapter.getCompanyOwner(farmDto.getOwner()));
		if (farmDto.getUsers() != null) {
			FarmUserAdapter farmUserAdapter = new FarmUserAdapter();
			List<FarmUser> farmUsers = new ArrayList<FarmUser>();
			farmDto.getUsers().stream().forEach(user->{
				farmUsers.add(farmUserAdapter.getFarmUser(user));
			});
			farm.setUsers(farmUsers);
		}
		return farm;
	}

	public Farm_DTO getFarmDTO(Farm farm) {
		
		if (farm == null) {
			return null ; 
		}
		Farm_DTO dto = new Farm_DTO();
		dto.setLiveStock(farm.getLiveStock());
		dto.setCity(farm.getCity());
		dto.setId(farm.getId());
		dto.setName(farm.getName());
		CompanyOwnerAdapter adapter = new CompanyOwnerAdapter();
		dto.setOwner(adapter.getCompanyOwnerDTO(farm.getOwner()));
		dto.setAccessGranted(farm.getaccessGranted());
		return dto;
	}
}
