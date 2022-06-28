package com.kodlamaio.rentACar.business.requests.individualCustomers;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateIndividualCustomerRequest {
	private int id;
	private String customerNumber;
	private String firstName;
	private String lastName;
	private String identityNumber;
	private LocalDate birthDate;	
	private String email;
	private String password;	

}
