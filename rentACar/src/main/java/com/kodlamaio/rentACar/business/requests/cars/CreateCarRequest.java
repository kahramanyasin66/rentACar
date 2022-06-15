package com.kodlamaio.rentACar.business.requests.cars;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCarRequest {
	@NotEmpty
	@NotBlank
	@Size(min = 2, max = 50)
	private String description;

	@NotEmpty
	private double dailyPrice;
	private double kilometer;
	private String plate;
	private int brandId;
	private int colorId;
	private int cityId;
	private int carScore;
}