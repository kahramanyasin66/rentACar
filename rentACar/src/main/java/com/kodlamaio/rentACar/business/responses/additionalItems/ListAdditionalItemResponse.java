package com.kodlamaio.rentACar.business.responses.additionalItems;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListAdditionalItemResponse {
	private int id;
	private String name;
	private double dailyPrice;
}
