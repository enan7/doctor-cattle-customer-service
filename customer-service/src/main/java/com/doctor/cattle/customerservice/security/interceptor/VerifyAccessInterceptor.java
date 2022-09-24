package com.doctor.cattle.customerservice.security.interceptor;

import javax.management.RuntimeErrorException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.doctor.cattle.customerservice.security.JwtTokenUtil;
import com.doctor.cattle.customerservice.service.user.UserService;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class VerifyAccessInterceptor implements HandlerInterceptor {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	

	@Autowired
	private UserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(VerifyAccessInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		final String requestTokenHeader = request.getHeader("Authorization");

		String username = null;
		String jwtToken = null;
		// JWT Token is in the form "Bearer token". Remove Bearer word and get
		// only the Token
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
				
				if (!userService.checkAccesAllowed(username)) {
					throw new AccessDeniedException("Access Denied " );
				}
				
				
			} catch (IllegalArgumentException e) {
				//this.cacheUtils.clearCacheByName(username);
				System.out.println("Unable to get JWT Token");
				return false;
			} catch (ExpiredJwtException e) {
				//this.cacheUtils.clearCacheByName(username);
				System.out.println("JWT Token has expired");
				return false;
			} catch (AccessDeniedException e) {
				throw e;
			}
		} else {
			logger.warn("JWT Token does not begin with Bearer String");
		}

		return true;
	}

	
}
