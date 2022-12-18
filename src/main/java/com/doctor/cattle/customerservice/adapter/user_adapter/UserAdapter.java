package com.doctor.cattle.customerservice.adapter.user_adapter;

import java.util.ArrayList;
import java.util.List;

import com.doctor.cattle.customerservice.adapter.company_adapter.Company_Adapter;
import com.doctor.cattle.customerservice.adapter.farm_adapter.FarmAdapter;
import com.doctor.cattle.customerservice.dto.company.CompanyDTO;
import com.doctor.cattle.customerservice.dto.farm.Farm_DTO;
import com.doctor.cattle.customerservice.dto.user.UserDTO;
import com.doctor.cattle.customerservice.entity.company_owner.CompanyOwner;
import com.doctor.cattle.customerservice.entity.farm.Farm;
import com.doctor.cattle.customerservice.entity.farm_user.FarmUser;
import com.doctor.cattle.customerservice.entity.role.Role;

public class UserAdapter {

	public UserDTO getUserDTO(CompanyOwner owner,Boolean getFarms) {

		if (owner == null) {
			return null;
		}
		UserDTO dto = new UserDTO();

		if (null != owner.getUserName()) {
			dto.setUserName(owner.getUserName());
		}

		if (null != owner.getRole()) {
			dto.setRole(owner.getRole());
			dto.setRoleName(owner.getRole().getDescription());
			dto.setRoleId(owner.getRole().getId());
		}

		if (null != owner.getFirstName()) {
			dto.setFirstName(owner.getFirstName());
		}

		if (null != owner.getLastName()) {
			dto.setLastName(owner.getLastName());
		}

		if (null != owner.getId()) {
			dto.setId(owner.getId());
		}
		
		if (getFarms == true) {
		if (null != owner.getFarms()) {
			FarmAdapter adapter = new FarmAdapter();
			List<Farm_DTO> farms = new ArrayList<>();
			owner.getFarms().stream().forEach(farm -> {
				farms.add(adapter.getFarmDTO(farm));
			});
			dto.setFarms(farms);
		}
		}

		dto.setIsOwner(true);
		
		Company_Adapter company_Adapter = new Company_Adapter();
		CompanyDTO companyDTO = company_Adapter.getCompanyDTO(owner.getCompany());
		dto.setCompany(companyDTO);
		dto.setAccessGranted(owner.getaccessGranted());
		dto.setPhoneNumber(owner.getPhoneNumber());
		return dto;

	}

	public UserDTO getUserDTO(FarmUser farm_user,Boolean getFarms) {

		if (farm_user == null) {
			return null;
		}
		UserDTO dto = new UserDTO();

		if (null != farm_user.getUserName()) {
			dto.setUserName(farm_user.getUserName());
		}

		if (null != farm_user.getRole()) {
			dto.setRole(farm_user.getRole());
			dto.setRoleName(farm_user.getRole().getDescription());
			dto.setRoleId(farm_user.getRole().getId());
		}

		if (null != farm_user.getFirstName()) {
			dto.setFirstName(farm_user.getFirstName());
		}

		if (null != farm_user.getLastName()) {
			dto.setLastName(farm_user.getLastName());
		}

		if (null != farm_user.getId()) {
			dto.setId(farm_user.getId());
		}

		if(getFarms == true) {
		if (null != farm_user.getFarm()) {
			FarmAdapter adapter = new FarmAdapter();
			List<Farm_DTO> farms = new ArrayList<>();
			farms.add(adapter.getFarmDTO(farm_user.getFarm()));

			dto.setFarms(farms);
		}
		}
		if (null != farm_user.getIsOwner()) {
			dto.setIsOwner(farm_user.getIsOwner());
		}
		Company_Adapter companyAdapter = new Company_Adapter();
		if(null != farm_user.getFarm()) {
		dto.setCompany(companyAdapter.getCompanyDTO(farm_user.getFarm().getCompany()));
		}
		dto.setAccessGranted(farm_user.getaccessGrantedd());
		dto.setPhoneNumber(farm_user.getPhoneNumber());
		return dto;

	}
	
	public CompanyOwner getOwner(UserDTO dto) {
		
		if (dto == null) {
			return null;
		}
		
		CompanyOwner owner = new CompanyOwner();
		
		owner.setId(dto.getId());
		Company_Adapter company_Adapter = new Company_Adapter();
		owner.setCompany(company_Adapter.getCompany(dto.getCompany()));
		if (dto.getFarms() != null) {
			List<Farm> farms = new ArrayList<>();
			FarmAdapter farmAdapter = new FarmAdapter();
			dto.getFarms().stream().forEach(farm->{
				farms.add(farmAdapter.getFarm(farm));
			});
			owner.setFarms(farms);
		}
		
		owner.setFirstName(dto.getFirstName());
		owner.setLastName(dto.getLastName());
		owner.setRole(Role.OWNER);
		owner.setUserName(dto.getUserName());
		owner.setPhoneNumber(dto.getPhoneNumber());
		return owner;
	}

	public FarmUser getFarmUser(UserDTO dto) {
		
		if (dto == null) {
			return null;
		}
		
		FarmUser user = new FarmUser();
		
		user.setId(dto.getId());
		if (dto.getFarms() != null) {
			FarmAdapter farmAdapter = new FarmAdapter();
			dto.getFarms().stream().forEach(farm->{
				user.setFarm(farmAdapter.getFarm(farm));
			});
		}
		
		user.setFirstName(dto.getFirstName());
		user.setLastName(dto.getLastName());
		user.setPhoneNumber(dto.getPhoneNumber());
		user.setRole(Role.USER);
		user.setUserName(dto.getUserName());
		return user;
	}
}
