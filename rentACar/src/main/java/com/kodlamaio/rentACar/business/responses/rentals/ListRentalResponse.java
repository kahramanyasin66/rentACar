package com.kodlamaio.rentACar.business.responses.rentals;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListRentalResponse {
	private int id;
	private LocalDate pickupDate;
	private LocalDate returnDate;
	private int totalDate;
	private double totalPrice ;
	private int carId;

}