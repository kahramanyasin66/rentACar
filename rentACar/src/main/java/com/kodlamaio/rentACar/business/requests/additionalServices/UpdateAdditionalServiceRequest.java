package com.kodlamaio.rentACar.business.requests.additionalServices;

import com.kodlamaio.rentACar.entities.concretes.AdditionalServiceItem;
import com.kodlamaio.rentACar.entities.concretes.Rental;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAdditionalServiceRequest {
	private int day;
	private AdditionalServiceItem additionalServiceItemId;
	private Rental rentalId;
}
