package com.kodlamaio.rentACar.business.requests.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
	private int id;
	private String firstName;
	private String lastName;
	private String tcNo;
	private String email;
	private String password;
}
