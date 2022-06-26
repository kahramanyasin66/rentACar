package com.kodlamaio.rentACar.business.responses.corporateCustomers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListCorporeteCustomerResponse {
	private String corporateName;
	private String taxNumber;

}
