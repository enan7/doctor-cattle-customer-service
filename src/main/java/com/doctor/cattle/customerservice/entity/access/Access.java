package com.doctor.cattle.customerservice.entity.access;

import lombok.Data;

public enum Access {
	
	GRANTED(true,"Granted",1L),DENIED(false,"Denied",0L);

	private boolean granted ;
	private String description;
	private Long id; 
	
	private Access(boolean granted,String description,Long id) {
		this.granted = granted;
		this.description = description;
		this.id = id;
	}

	public boolean isGranted() {
		return granted;
	}

	public String getDescription() {
		return description;
	}

	public Long getId() {
		return id;
	}
	
	
	
	
}
