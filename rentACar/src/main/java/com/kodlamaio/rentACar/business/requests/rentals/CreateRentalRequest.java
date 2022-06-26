package com.kodlamaio.rentACar.business.requests.rentals;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRentalRequest {

	private LocalDate pickupDate;
	private LocalDate returnDate;
	private double totalPrice;
	private int carId;
	private int returnedCityId;
	private int pickUpCityId ;
	private int individualCustomerId;
	private int corporateCustomerId;
	
}