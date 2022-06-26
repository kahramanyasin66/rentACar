package com.kodlamaio.rentACar.business.requests.individualCustomers;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateIndividualCustomerRequest {	
	private String firstName;
	private String lastName;
	private String identityNumber;	
	private LocalDate birthDate ;	
}
