package com.kodlamaio.rentACar.business.responses.additionalServices;

import com.kodlamaio.rentACar.entities.concretes.AdditionalItem;
import com.kodlamaio.rentACar.entities.concretes.Rental;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdditionalServiceResponse {
	
	private int day;
	private double totalPrice;
	private AdditionalItem additionalServiceItemId;
	private Rental rentalId ;
	
	

}
