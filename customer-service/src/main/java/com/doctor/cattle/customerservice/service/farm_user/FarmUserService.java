package com.doctor.cattle.customerservice.service.farm_user;

import com.doctor.cattle.customerservice.dto.farm_user.Farm_User_DTO;
import com.doctor.cattle.customerservice.exception.farm_user.FarmUserRegistrationException;
import com.doctor.cattle.customerservice.exception.user.UserNotFoundException;

public interface FarmUserService {
	
	public Farm_User_DTO registerUser(Farm_User_DTO user) throws FarmUserRegistrationException;

	public void invertAccess(Farm_User_DTO user) throws UserNotFoundException;
}
