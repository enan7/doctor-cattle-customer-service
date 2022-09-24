package com.doctor.cattle.customerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.doctor.cattle.customerservice.entity.company.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
	
	public long countByCompanyName(String companyName);

}
