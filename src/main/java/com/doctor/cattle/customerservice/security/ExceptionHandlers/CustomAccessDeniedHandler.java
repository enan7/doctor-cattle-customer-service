package com.doctor.cattle.customerservice.security.ExceptionHandlers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

	private String message = "";

	public CustomAccessDeniedHandler() {
		// TODO Auto-generated constructor stub
	}

	public CustomAccessDeniedHandler(String message) {
		this.message = message;
	}

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			// LOG.warn("User: " + auth.getName()
			// + " attempted to access the protected URL: "
			// + request.getRequestURI());
		}
		if (this.message.equals("")) {
			message = "User " + auth.getName() + " is not authorized for this request";
		}
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, message);

	}

}
