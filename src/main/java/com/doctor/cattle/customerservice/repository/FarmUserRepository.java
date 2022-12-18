package com.doctor.cattle.customerservice.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.doctor.cattle.customerservice.entity.farm_user.FarmUser;

public interface FarmUserRepository extends CrudRepository<FarmUser, Long> {

	public Long countByUserName(String userName);

	public FarmUser findByUserName(String userName);
	
	@Query(value="Select farm_user_id from farm_user where farm_user_user_name = :userName ",nativeQuery=true)
	public Long getUserIdByUserName(@Param("userName")String userName);
	
}
