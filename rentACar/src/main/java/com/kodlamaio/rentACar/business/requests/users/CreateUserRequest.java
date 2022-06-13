package com.kodlamaio.rentACar.business.requests.users;

import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {
	private String firstName;
	private String lastName;
	@Size(min=11,max=11)
	private String tcNo;
	private String email;
	private String password;
}
