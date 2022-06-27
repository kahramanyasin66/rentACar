package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.AdditionalItemService;
import com.kodlamaio.rentACar.business.requests.additionalItems.CreateAdditionalItemRequest;
import com.kodlamaio.rentACar.business.requests.additionalItems.DeleteAdditionalItemRequest;
import com.kodlamaio.rentACar.business.requests.additionalItems.UpdateAdditionalItemRequest;
import com.kodlamaio.rentACar.business.responses.additionalItems.AdditionalItemResponse;
import com.kodlamaio.rentACar.business.responses.additionalItems.ListAdditionalItemResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/additionalitems")
public class AdditionalItemsController {

	AdditionalItemService additionalItemService;

	public AdditionalItemsController(AdditionalItemService additionalItemService) {

		this.additionalItemService = additionalItemService;
	}

	@PostMapping("/add")
	Result add(@RequestBody @Valid CreateAdditionalItemRequest createAdditionalItemRequest) {
		return this.additionalItemService.add(createAdditionalItemRequest);
	}

	@PostMapping("/uptade")
	Result update(@RequestBody @Valid UpdateAdditionalItemRequest updateAdditionalItemRequest) {
		return this.additionalItemService.update(updateAdditionalItemRequest);
	}

	@PostMapping("/delete")
	Result delete(@RequestBody @Valid DeleteAdditionalItemRequest deleteAdditionalItemRequest) {
		return this.additionalItemService.delete(deleteAdditionalItemRequest);
	}

	@GetMapping("/getall")
	DataResult<List<ListAdditionalItemResponse>> getAll() {
		return this.additionalItemService.getAll();
	}

	@GetMapping("/getbyid")
	DataResult<AdditionalItemResponse> getById(@RequestParam int id) {
		return this.additionalItemService.getById(id);
	}

}
