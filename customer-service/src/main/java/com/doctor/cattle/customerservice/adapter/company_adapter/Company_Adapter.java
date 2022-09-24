package com.doctor.cattle.customerservice.adapter.company_adapter;

import com.doctor.cattle.customerservice.adapter.company_owner_adapter.CompanyOwnerAdapter;
import com.doctor.cattle.customerservice.dto.company.CompanyDTO;
import com.doctor.cattle.customerservice.entity.company.Company;
import com.doctor.cattle.customerservice.entity.company_owner.CompanyOwner;

public class Company_Adapter {
	
	public Company getCompany(CompanyDTO companyDTO) {
		if (companyDTO == null) {
			return null;
		}
		Company company = new Company();
		company.setId(companyDTO.getId());
		company.setCompanyName(companyDTO.getCompanyName());
		return company;
	}

	public CompanyDTO getCompanyDTO(Company company) {
		if (company == null) {
			return null;
		}
		CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.setId(company.getId());
		companyDTO.setCompanyName(company.getCompanyName());
		CompanyOwnerAdapter companyOwnerAdapter = new CompanyOwnerAdapter();
		companyDTO.setOwner(companyOwnerAdapter.getCompanyOwnerDTO(company.getOwner()));
		companyDTO.setAccessGranted(company.getaccessGranted());
		return companyDTO;
	}
}
