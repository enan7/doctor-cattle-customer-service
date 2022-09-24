package com.doctor.cattle.customerservice.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.doctor.cattle.customerservice.dto.user.UserDTO;
import com.doctor.cattle.customerservice.entity.access.Access;
import com.doctor.cattle.customerservice.entity.role.Role;
import com.doctor.cattle.customerservice.service.user.UserService;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;

	private UserDTO user;

	
	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		
			user = userService.findByUserName(username);
			
			if (user == null) {
	            throw new UsernameNotFoundException(username);
	        } else {
			      return new User(username, "" , getAuthorities(user));
			}
		
	}


	public Collection<? extends GrantedAuthority> getAuthorities(UserDTO user) {
	    Role role = user.getRole();
	    List<SimpleGrantedAuthority> authorities = new ArrayList<>();
	    
	    authorities.add(new SimpleGrantedAuthority(role.getDescription()));
	    
	    if (null == user.getAccessGranted()) {
		    authorities.add(new SimpleGrantedAuthority(Access.DENIED.getDescription()));

	    }else {
	    authorities.add(new SimpleGrantedAuthority(user.getAccessGranted().getDescription()));
	    }
	    return authorities;
	}


	public UserDTO getUser() {
		return user;
	}
	
	
	
}
