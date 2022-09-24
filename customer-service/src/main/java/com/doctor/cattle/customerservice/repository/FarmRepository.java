package com.doctor.cattle.customerservice.repository;

import org.springframework.data.repository.CrudRepository;

import com.doctor.cattle.customerservice.entity.farm.Farm;

public interface FarmRepository extends CrudRepository<Farm, Long> {
	
	public Long countById(Long Id);
}
