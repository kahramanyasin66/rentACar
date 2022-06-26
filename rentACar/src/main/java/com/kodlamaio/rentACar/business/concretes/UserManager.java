package com.kodlamaio.rentACar.business.concretes;

import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.UserService;
import com.kodlamaio.rentACar.business.requests.users.CreateUserRequest;
import com.kodlamaio.rentACar.business.requests.users.DeleteUserRequest;
import com.kodlamaio.rentACar.business.requests.users.UpdateUserRequest;
import com.kodlamaio.rentACar.business.responses.users.ListUserResponse;
import com.kodlamaio.rentACar.business.responses.users.UserResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.UserRepository;
import com.kodlamaio.rentACar.entities.concretes.User;

@Service
public class UserManager implements UserService {
	UserRepository userRepository;
	ModelMapperService modelMapperService;	

	@Autowired
	public UserManager(UserRepository userRepository, ModelMapperService modelMapperService) {
		this.userRepository = userRepository;
		this.modelMapperService = modelMapperService;	
	}

	@Override
	public Result add(CreateUserRequest createUserRequest) throws NumberFormatException, RemoteException {
		
	
		User user = this.modelMapperService.forRequest().map(createUserRequest, User.class);
	
		this.userRepository.save(user);
		return new SuccessResult("USER.ADDED");

	}

	@Override
	public Result update(UpdateUserRequest updateUserRequest) {
		User userToUpdate = this.modelMapperService.forRequest().map(updateUserRequest, User.class);
		this.userRepository.save(userToUpdate);
		return new SuccessResult("USER.UPDATED");
	}

	@Override
	public Result delete(DeleteUserRequest deleteUserRequest) {
		int userId = deleteUserRequest.getId();
		this.userRepository.deleteById(userId);

		return new SuccessResult("USER.DELETED");
	}

	@Override
	public DataResult<List<ListUserResponse>> getAll() {
		List<User> users = this.userRepository.findAll();
		List<ListUserResponse> response = users.stream()
				.map(user -> this.modelMapperService.forResponse().map(user, ListUserResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListUserResponse>>(response, "USERS.GETTED");
	}

	@Override
	public DataResult<UserResponse> getById(int id) {
		User userId = this.userRepository.findById(id);
		UserResponse response = this.modelMapperService.forResponse().map(userId, UserResponse.class);
		return new SuccessDataResult<UserResponse>(response, "USER:GETTED");
	}

	@Override
	public DataResult<List<ListUserResponse>> getAll(Integer pageNo, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

		List<User> users = this.userRepository.findAll(pageable).getContent();
		List<ListUserResponse> response = users.stream()
				.map(user -> this.modelMapperService.forResponse().map(user, ListUserResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<ListUserResponse>>(response);

	}

	

}
