package com.kodlamaio.rentACar.business.requests.addresses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAddressRequest {

	private String realAddress;
	private String invoiceAddress;
	private int customerId;
}
