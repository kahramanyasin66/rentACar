package com.kodlamaio.rentACar.business.requests.additionalServices;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAdditionalServiceRequest {
	private int additionalServiceItemId;
	private int rentalId;
}
