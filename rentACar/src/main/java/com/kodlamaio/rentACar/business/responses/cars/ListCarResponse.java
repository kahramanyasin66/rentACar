package com.kodlamaio.rentACar.business.responses.cars;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListCarResponse {
	private String description;
	private double dailyPrice;
	private double kilometer;
	private String plates;
	private int carState;
	private int brandId;
	private String brandName ;
	private int colorId;
	private String colorName;
	private int cityId;
	private String cityName;
	private int carScore;

}