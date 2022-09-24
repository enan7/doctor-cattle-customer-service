package com.doctor.cattle.customerservice.entity.role;


public enum Role {
	
	 ADMIN(1L,"Admin"),OWNER(2L,"Owner"),USER(3L,"user");
	
	private String description;
	private Long id;
	
	
	
	private Role(Long id,String description){
		this.description = description;
		this.id = id;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public Long getId() {
		return this.id;
	}

}
