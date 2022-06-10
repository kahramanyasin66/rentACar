package com.kodlamaio.rentACar.business.responses.brands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListBrandResponse {
	private int id;
	private String name;

}