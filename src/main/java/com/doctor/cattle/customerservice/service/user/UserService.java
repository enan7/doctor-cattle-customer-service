package com.doctor.cattle.customerservice.service.user;

import org.springframework.security.access.AccessDeniedException;

import com.doctor.cattle.customerservice.dto.user.UserDTO;

public interface UserService {

	public UserDTO findByUserName(String userName);
	
	public Boolean userExists(String userName);
	
	public UserDTO login(String userName, String password) throws AccessDeniedException;
	
	public UserDTO finById(Long id);
	
	public boolean checkAccesAllowed(String userName) throws AccessDeniedException;
	
	public UserDTO getUserInSession();
}
