package com.doctor.cattle.customerservice.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.doctor.cattle.customerservice.controller.response.BaseResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = {AccessDeniedException.class})
	public BaseResponse accessDentiedException(AccessDeniedException exception) {
		BaseResponse response  = new BaseResponse(HttpStatus.UNAUTHORIZED, exception.getMessage());
		return response;
	}
}
