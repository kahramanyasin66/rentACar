package com.kodlamaio.rentACar.business.responses.addresses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {

	private int id;

	private String realAddress;
	private String invoiceAddress;
	private int customerId;
}
