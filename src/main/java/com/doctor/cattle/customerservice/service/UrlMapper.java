package com.doctor.cattle.customerservice.service;

public enum UrlMapper {
	
	GETLIVESTOCK("cattle-service/get-live-stock/");
	
	String baseUrl = "https://doctor-cattle-cattle-service.herokuapp.com/api/";
	//String baseUrl = "http://localhost:9001/api/";

	String endPoint = "";
	
	 UrlMapper(String endPoint) {
		this.endPoint = endPoint;
	}
	 
	 
	public String getUrl() {
		return this.baseUrl + this.endPoint;
	}

}
