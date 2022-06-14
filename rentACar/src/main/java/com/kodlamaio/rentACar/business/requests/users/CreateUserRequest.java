package com.kodlamaio.rentACar.business.requests.users;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
	private String firstName;
	private String lastName;
	private String tcNo;
	private String email;
	private String password;
	private LocalDate birthDate ;	
}
