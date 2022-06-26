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

import com.kodlamaio.rentACar.business.abstracts.IndividualCustomerService;
import com.kodlamaio.rentACar.business.requests.individualCustomers.CreateIndividualCustomerRequest;
import com.kodlamaio.rentACar.business.requests.individualCustomers.DeleteIndividualCustomerRequest;
import com.kodlamaio.rentACar.business.requests.individualCustomers.UpdateIndividualCustomerRequest;
import com.kodlamaio.rentACar.business.responses.individualCustomers.IndividualCustomerResponse;
import com.kodlamaio.rentACar.business.responses.individualCustomers.ListIndividualCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("api/individual_customers")
public class IndividualCustomersController {
	private IndividualCustomerService individualCustomerService;

	public IndividualCustomersController(IndividualCustomerService individualCustomerService) {

		this.individualCustomerService = individualCustomerService;
	}

	@PostMapping("/add")
	Result add(@RequestBody @Valid CreateIndividualCustomerRequest createIndividualCustomerRequest)
			throws NumberFormatException, RemoteException {
		return this.individualCustomerService.add(createIndividualCustomerRequest);

	}

	@PostMapping("/update")
	Result update(@RequestBody @Valid UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
		return this.individualCustomerService.update(updateIndividualCustomerRequest);
	}

	@PostMapping("/delete")
	Result delete(@RequestBody @Valid DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) {
		return this.delete(deleteIndividualCustomerRequest);
	}

	@GetMapping("/getall")
	DataResult<List<ListIndividualCustomerResponse>> getAll() {
		return this.individualCustomerService.getAll();
	}

	@GetMapping("/getbyid")
	DataResult<IndividualCustomerResponse> getById(@RequestParam int id) {
		return this.individualCustomerService.getById(id);
	}

}
