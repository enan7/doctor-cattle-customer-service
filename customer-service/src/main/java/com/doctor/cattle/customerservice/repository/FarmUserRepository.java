package com.doctor.cattle.customerservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.doctor.cattle.customerservice.entity.farm_user.FarmUser;

public interface FarmUserRepository extends CrudRepository<FarmUser, Long> {

	public Long countByUserName(String userName);

	public FarmUser findByUserName(String userName);
	
}
