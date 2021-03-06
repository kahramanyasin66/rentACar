package com.kodlamaio.rentACar.business.responses.orderedAdditionalItems;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderedAdditionalItemResponse {
	private int id;
	private int additionalItemId;
	private int rentalId;
	private double totalPrice;
	private int totalDays;
	private LocalDate pickUpDate;
	private LocalDate returnDate;

}
