package com.doctor.cattle.customerservice.dto.farm.animal;

import lombok.Data;

@Data
public class AnimalDTO {

	private Long id;
	private String deviceNumber;
	private Long farmId;
	private Double temprature;
}
