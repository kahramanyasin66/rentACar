package com.kodlamaio.rentACar.business.requests.orderedAdditionalItems;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderedAdditionalItemRequest {
	private int id;
	private int additionalItemId;
	private int rentalId;
	private LocalDate pickUpDate;
	private LocalDate returnDate;
}
