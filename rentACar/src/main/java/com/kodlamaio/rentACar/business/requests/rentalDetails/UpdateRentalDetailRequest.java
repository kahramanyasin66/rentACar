package com.kodlamaio.rentACar.business.requests.rentalDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRentalDetailRequest {
	private int id;
	private double totalPrice;
	private int rentalId;
	private int additionalServiceId;

}
