package com.kodlamaio.rentACar.business.requests.cars;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DeleteCarRequest {
	private int id;
}