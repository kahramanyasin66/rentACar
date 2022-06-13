package com.kodlamaio.rentACar.business.responses.cities;

import java.util.List;

import com.kodlamaio.rentACar.entities.concretes.Car;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityResponse {
	private String name;
	List<Car> cars;

}
