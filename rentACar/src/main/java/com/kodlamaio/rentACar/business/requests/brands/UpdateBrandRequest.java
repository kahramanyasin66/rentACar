package com.kodlamaio.rentACar.business.requests.brands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBrandRequest {
	
	private String name;
	private int id;
}