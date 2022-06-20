package com.kodlamaio.rentACar.business.responses.rentalDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalDetailResponse {
	private int id;
	private double totalPrice;
	private int rentalId;
	private int additionalServiceId;
}
