package com.kodlamaio.rentACar.business.responses.rentalDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListRentalDetailResponse {
	private int id;
	private double totalPrice;
	private int rentalId;
	private int additionalServiceId;

}
