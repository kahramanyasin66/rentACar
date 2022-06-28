package com.kodlamaio.rentACar.business.responses.individualCustomers;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListIndividualCustomerResponse {
	private String customerNumber;
	private String firstName;
	private String lastName;
	private String identityNumber;
	private LocalDate birthDate;
	private String email;
	private String password;
}
