package com.kodlamaio.rentACar.business.responses.additionalServices;

import com.kodlamaio.rentACar.entities.concretes.AdditionalServiceItem;
import com.kodlamaio.rentACar.entities.concretes.Rental;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListAdditionalServiceResponse {
	private int day;
	private double totalPrice;
	private AdditionalServiceItem additionalServiceItemId;
	private Rental rentalId;

}
