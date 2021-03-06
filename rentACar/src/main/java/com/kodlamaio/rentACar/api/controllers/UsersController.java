package com.kodlamaio.rentACar.api.controllers;

import java.rmi.RemoteException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.UserService;
import com.kodlamaio.rentACar.business.requests.users.CreateUserRequest;
import com.kodlamaio.rentACar.business.requests.users.DeleteUserRequest;
import com.kodlamaio.rentACar.business.requests.users.UpdateUserRequest;
import com.kodlamaio.rentACar.business.responses.users.ListUserResponse;
import com.kodlamaio.rentACar.business.responses.users.UserResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("api/users")
public class UsersController {
	private UserService userService;

	public UsersController(UserService userService) {

		this.userService = userService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateUserRequest createUserRequest)
			throws NumberFormatException, RemoteException {
		return this.userService.add(createUserRequest);

	}

	@PostMapping("/update")
	public Result update(@RequestBody @Valid UpdateUserRequest updateUserRequest) {
		return this.userService.update(updateUserRequest);
	}

	@PostMapping("/delete")
	public Result delete(@RequestBody DeleteUserRequest deleteUserRequest) {
		return this.userService.delete(deleteUserRequest);
	}

	@GetMapping("/getall")
	public DataResult<List<ListUserResponse>> getAll() {
		return this.userService.getAll();
	}

	@GetMapping("/getbyid")
	public DataResult<UserResponse> getById(@RequestParam int id) {
		return this.userService.getById(id);
	}

	@GetMapping("/getallbypage")
	public DataResult<List<ListUserResponse>> getAllByPage(@RequestParam int pageSize, int pageNo) {
		return this.userService.getAll(pageSize, pageNo);
	}

}
