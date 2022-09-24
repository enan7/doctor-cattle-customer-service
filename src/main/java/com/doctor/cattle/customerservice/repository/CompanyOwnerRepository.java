package com.doctor.cattle.customerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.doctor.cattle.customerservice.entity.company_owner.CompanyOwner;

public interface CompanyOwnerRepository extends JpaRepository<CompanyOwner, Long> {

	public Long countByUserName(String userName);
	
	public CompanyOwner findByUserName(String userName);
	
	public CompanyOwner findByUserNameAndPassword(String userName, String password);
}
