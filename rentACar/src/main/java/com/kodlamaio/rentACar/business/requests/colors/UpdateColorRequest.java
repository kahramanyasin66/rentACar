package com.kodlamaio.rentACar.business.requests.colors;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateColorRequest {
	private String name;
	private int id;
}