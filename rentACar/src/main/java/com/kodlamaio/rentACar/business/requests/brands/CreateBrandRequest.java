package com.kodlamaio.rentACar.business.requests.brands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateBrandRequest {
	private String name;
	private int id;
}