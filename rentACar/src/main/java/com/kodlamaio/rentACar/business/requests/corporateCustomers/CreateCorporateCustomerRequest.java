package com.kodlamaio.rentACar.business.requests.corporateCustomers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCorporateCustomerRequest {
	private String corporateName;
	private String taxNumber;
	private String customerNumber;
	private String email;
	private String password;	
}
