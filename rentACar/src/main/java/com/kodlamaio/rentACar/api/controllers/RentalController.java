package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.RentalService;
import com.kodlamaio.rentACar.business.requests.rentals.CreateRentalRequest;
import com.kodlamaio.rentACar.business.requests.rentals.DeleteRentalRequest;
import com.kodlamaio.rentACar.business.requests.rentals.UpdateRentalRequest;
import com.kodlamaio.rentACar.business.responses.rentals.ListRentalResponse;
import com.kodlamaio.rentACar.business.responses.rentals.RentalResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
	RentalService rentalService;

	public RentalController(RentalService rentalService) {

		this.rentalService = rentalService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody CreateRentalRequest createRentalRequest) {
		return this.rentalService.add(createRentalRequest);

	}

	@PostMapping("/update")
	public Result update(@RequestBody UpdateRentalRequest updateRentalRequest) {
		return this.rentalService.update(updateRentalRequest);
	}

	@PostMapping("/delete")
	public Result delete(@RequestBody DeleteRentalRequest deleteRentalRequest) {
		return this.rentalService.delete(deleteRentalRequest);
	}

	@GetMapping("/getall")
	public DataResult<List<ListRentalResponse>> getAll() {
		return this.rentalService.getall();

	}

	@GetMapping("/getbyid")
	public DataResult<RentalResponse> getById(int id) {
		return this.rentalService.getById(id);
	}

}