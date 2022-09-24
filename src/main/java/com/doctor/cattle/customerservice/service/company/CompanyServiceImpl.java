package com.doctor.cattle.customerservice.service.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doctor.cattle.customerservice.adapter.company_adapter.Company_Adapter;
import com.doctor.cattle.customerservice.adapter.company_owner_adapter.CompanyOwnerAdapter;
import com.doctor.cattle.customerservice.dto.company.CompanyDTO;
import com.doctor.cattle.customerservice.entity.company.Company;
import com.doctor.cattle.customerservice.entity.company_owner.CompanyOwner;
import com.doctor.cattle.customerservice.exception.company.CompanyRegisterationException;
import com.doctor.cattle.customerservice.repository.CompanyRepository;
import com.doctor.cattle.customerservice.service.user.UserService;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	private final Company_Adapter company_Adapter = new Company_Adapter();

	private final CompanyOwnerAdapter companyOwnerAdapter = new CompanyOwnerAdapter();

	@Autowired
	private UserService userService;

	@Override
	public CompanyDTO registerCompany(CompanyDTO companyDTO) throws Exception {
		if (validCompany(companyDTO)) {
			Company company = company_Adapter.getCompany(companyDTO);
			CompanyOwner owner = companyOwnerAdapter.getCompanyOwner(companyDTO.getOwner());
			owner.setCompany(company);
			company.setOwner(owner);
			company = companyRepository.save(company);
			return company_Adapter.getCompanyDTO(company);
		}

		return null;

	}

	private boolean validCompany(CompanyDTO companyDTO) throws CompanyRegisterationException {

		if (null == companyDTO.getCompanyName() || companyDTO.getCompanyName().trim().equals("")) {
			throw new CompanyRegisterationException("Company Name is required");
		} else if (null == companyDTO.getOwner().getPassword()
				|| companyDTO.getOwner().getPassword().trim().equals("")) {
			throw new CompanyRegisterationException("Password is required");
		} else if (null == companyDTO.getOwner().getUserName()
				|| companyDTO.getOwner().getUserName().trim().equals("")) {
			throw new CompanyRegisterationException("UserName is required");
		}
		if (!companyExists(companyDTO)) {
			if (!userExists(companyDTO.getOwner().getUserName())) {
				return true;
			} else {
				throw new CompanyRegisterationException(
						"User with name " + companyDTO.getOwner().getUserName() + " is already registered");
			}
		} else {
			throw new CompanyRegisterationException(
					"Company with name " + companyDTO.getCompanyName() + " is already registered");
		}

	}

	private boolean companyExists(CompanyDTO companyDTO) throws CompanyRegisterationException {

		return companyRepository.countByCompanyName(companyDTO.getCompanyName()) != 0;

	}

	private Boolean userExists(String userName) {
		return userService.userExists(userName);

	}

}
