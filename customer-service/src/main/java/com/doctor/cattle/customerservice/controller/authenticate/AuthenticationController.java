package com.doctor.cattle.customerservice.controller.authenticate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.doctor.cattle.customerservice.controller.request.login.UserLoginRequest;
import com.doctor.cattle.customerservice.controller.response.login.UserLoginResponse;
import com.doctor.cattle.customerservice.dto.user.UserDTO;
import com.doctor.cattle.customerservice.entity.access.Access;
import com.doctor.cattle.customerservice.security.JwtTokenUtil;
import com.doctor.cattle.customerservice.service.user.UserService;

@RestController
@RequestMapping(value = "/api/customer-service/")
public class AuthenticationController {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

	@PostMapping(value = "login")
	public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest request) {
		UserLoginResponse response = null;
		UserDTO user = null;
		try {
		 user = userService.login(request.getUserName(), request.getPassword());
		} catch (AccessDeniedException e) {
			logger.error(e.getMessage());
			throw e;
		}
		if (null == user) {
			response = new UserLoginResponse(HttpStatus.FORBIDDEN, "User name or password does not matched");
			return new ResponseEntity<UserLoginResponse>(response, response.getStatus());
		}

		String jwtToken = jwtTokenUtil.generateToken(request.getUserName());

		user.setJwt_Token(jwtToken);
		response = new UserLoginResponse(HttpStatus.OK, "Login Successfull", user);
		return new ResponseEntity<UserLoginResponse>(response, response.getStatus());

	}

}
