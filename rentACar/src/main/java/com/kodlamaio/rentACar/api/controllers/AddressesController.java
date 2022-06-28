package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.AddressService;
import com.kodlamaio.rentACar.business.requests.addresses.CreateAddressRequest;
import com.kodlamaio.rentACar.business.requests.addresses.DeleteAddressRequest;
import com.kodlamaio.rentACar.business.requests.addresses.UpdateAddressRequest;
import com.kodlamaio.rentACar.business.responses.addresses.AddressResponse;
import com.kodlamaio.rentACar.business.responses.addresses.ListAddressResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/addresses")
public class AddressesController {
	private AddressService addressService;

	public AddressesController(AddressService addressService) {

		this.addressService = addressService;
	}

	@PostMapping("/add")
	Result add(@RequestBody @Valid CreateAddressRequest createAddressRequest) {
		return this.addressService.add(createAddressRequest);
	}

	@PostMapping("/update")
	Result update(@RequestBody @Valid UpdateAddressRequest updateAddressRequest) {
		return this.addressService.update(updateAddressRequest);
	}

	@PostMapping("/delete")
	Result delete(@RequestBody @Valid DeleteAddressRequest deleteAddressRequest) {
		return this.addressService.delete(deleteAddressRequest);
	}

	@GetMapping("/getall")
	DataResult<List<ListAddressResponse>> getall() {
		return this.addressService.getAll();
	}

	@GetMapping("/getbyid")
	DataResult<AddressResponse> getById(@RequestParam int id) {
		return this.addressService.getById(id);
	}

	@GetMapping("/getallinvoiceaddress")
	DataResult<List<ListAddressResponse>> getAllInvoiceAddress(@RequestParam int userId, int addressType) {
		return this.addressService.getAllInvoiceAddress(userId, addressType);
	}

	@GetMapping("/getallrealaddress")
	DataResult<List<ListAddressResponse>> getAllRealAddress(@RequestParam int userId, int addressType) {
		return this.addressService.getAllRealAddress(userId, addressType);
	}

}
