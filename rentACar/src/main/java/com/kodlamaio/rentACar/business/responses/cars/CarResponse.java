package com.kodlamaio.rentACar.business.responses.cars;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarResponse {
	private int id;	
	private String description;
	private double dailyPrice;
	private double kilometer;
	private String plate;
	private int carState;
	private int brandId;
	private int colorId;	
	private int cityId;
	private int carScore;
}