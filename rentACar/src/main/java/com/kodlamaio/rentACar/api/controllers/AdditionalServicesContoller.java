package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.AdditionalServiceService;
import com.kodlamaio.rentACar.business.requests.additionalServices.CreateAdditionalServiceRequest;
import com.kodlamaio.rentACar.business.requests.additionalServices.DeleteAdditionalServiceRequest;
import com.kodlamaio.rentACar.business.requests.additionalServices.UpdateAdditionalServiceRequest;
import com.kodlamaio.rentACar.business.responses.additionalServices.AdditionalServiceResponse;
import com.kodlamaio.rentACar.business.responses.additionalServices.ListAdditionalServiceResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/additional_services")
public class AdditionalServicesContoller {
	private AdditionalServiceService additionalServiceService;

	public AdditionalServicesContoller(AdditionalServiceService additionalServiceService) {
		this.additionalServiceService = additionalServiceService;
	}

	@PostMapping("/add")
	Result add(@RequestBody @Valid CreateAdditionalServiceRequest createAdditionalServiceRequest) {
		return this.additionalServiceService.add(createAdditionalServiceRequest);
	}

	@PostMapping("/update")
	Result update(@RequestBody @Valid UpdateAdditionalServiceRequest updateAdditionalServiceRequest) {
		return this.additionalServiceService.update(updateAdditionalServiceRequest);
	}

	@PostMapping("/delete")
	Result delete(@RequestBody  DeleteAdditionalServiceRequest deleteAdditionalServiceRequest) {
		return this.additionalServiceService.delete(deleteAdditionalServiceRequest);
	}

	@GetMapping("/getall")
	DataResult<List<ListAdditionalServiceResponse>> getall() {

		return this.additionalServiceService.getall();
	}
	
	DataResult<AdditionalServiceResponse> getById(@RequestParam int id) {
		return this.additionalServiceService.getById(id);
	}

}
