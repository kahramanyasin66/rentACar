package com.kodlamaio.rentACar.business.requests.corporateCustomers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCorporateCustomerRequest {
	private int id;
	private String corporateName ;
	private String taxNumber;
}
