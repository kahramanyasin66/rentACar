package com.kodlamaio.rentACar.business.responses.corporateCustomers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorporateCustomerResponse {
	
	private int id;
	private String corporateName;
	private String taxNumber;
	private String customerNumber;
	private String email;
	private String password;	
}
