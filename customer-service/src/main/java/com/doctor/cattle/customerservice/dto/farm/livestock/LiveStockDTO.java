package com.doctor.cattle.customerservice.dto.farm.livestock;

import java.util.List;

import com.doctor.cattle.customerservice.dto.farm.animal.AnimalDTO;

import lombok.Data;

@Data
public class LiveStockDTO {

	private List<AnimalDTO> animals;
}
