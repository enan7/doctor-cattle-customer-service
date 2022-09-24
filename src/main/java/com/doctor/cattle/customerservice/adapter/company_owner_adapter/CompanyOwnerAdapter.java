package com.doctor.cattle.customerservice.adapter.company_owner_adapter;

import com.doctor.cattle.customerservice.dto.company_owner.Company_Owner_DTO;
import com.doctor.cattle.customerservice.entity.company_owner.CompanyOwner;
import com.doctor.cattle.customerservice.entity.role.Role;

public class CompanyOwnerAdapter {
	
	/**
	 * This method is only responsible for creating a companyowner which contains 
	 * primitive attributes of company owner dto 
	 * @param companyOwnerDTO
	 * @return
	 */
	public CompanyOwner getCompanyOwner(Company_Owner_DTO companyOwnerDTO) {
		if (companyOwnerDTO == null) {
			return null;
		}
		CompanyOwner company_Owner = new CompanyOwner();
		company_Owner.setId(companyOwnerDTO.getId());
		company_Owner.setFirstName(companyOwnerDTO.getFirstName());
		company_Owner.setLastName(companyOwnerDTO.getLastName());
		if (companyOwnerDTO .getPassword() != null) {
		company_Owner.setPassword(companyOwnerDTO.getPassword());
		}
		company_Owner.setRole(Role.OWNER);
		company_Owner.setUserName(companyOwnerDTO.getUserName());
		return company_Owner;
	}


	/**
	 * This method is only responsible for creating a company owner dto which contains 
	 * primitive attributes of company owner  
	 * @param companyOwner
	 * @return
	 */
	public Company_Owner_DTO getCompanyOwnerDTO(CompanyOwner companyOwner) {
		if (companyOwner == null) {
			return null;
		}
		Company_Owner_DTO owner_dto = new Company_Owner_DTO();
		owner_dto.setFirstName(companyOwner.getFirstName());
		owner_dto.setLastName(companyOwner.getLastName());
		owner_dto.setId(companyOwner.getId());
		owner_dto.setUserName(companyOwner.getUserName());
		owner_dto.setAccessGranted(companyOwner.getaccessGranted());
		return owner_dto;
	}

}
