package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.AdditionalServiceItemService;
import com.kodlamaio.rentACar.business.requests.additionalServiceItems.CreateAdditionalServiceItemRequest;
import com.kodlamaio.rentACar.business.requests.additionalServiceItems.DeleteAdditionalServiceItemRequest;
import com.kodlamaio.rentACar.business.requests.additionalServiceItems.UpdateAdditionalServiceItemRequest;
import com.kodlamaio.rentACar.business.responses.additionelServiceItems.AdditionalServiceItemResponse;
import com.kodlamaio.rentACar.business.responses.additionelServiceItems.ListAdditionalServiceItemResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/additional_service_items")
public class AdditionalServiceItemsContoller {
	private AdditionalServiceItemService additionalServiceItemService;

	public AdditionalServiceItemsContoller(AdditionalServiceItemService additionalServiceItemService) {
		this.additionalServiceItemService = additionalServiceItemService;
	}

	@PostMapping("/add")
	Result add(@RequestBody @Valid CreateAdditionalServiceItemRequest createAdditionalServiceItemRequest) {
		return this.additionalServiceItemService.add(createAdditionalServiceItemRequest);
	}

	@PostMapping("/update")
	Result update(@RequestBody @Valid UpdateAdditionalServiceItemRequest additionalServiceItemRequest) {
		return this.additionalServiceItemService.update(additionalServiceItemRequest);
	}

	@PostMapping("/delete")
	Result delete(@RequestBody DeleteAdditionalServiceItemRequest deleteAdditionalServiceItemRequest) {
		return this.additionalServiceItemService.delete(deleteAdditionalServiceItemRequest);
	}

	@GetMapping("/getall")
	DataResult<List<ListAdditionalServiceItemResponse>> getall() {
		return this.additionalServiceItemService.getall();
	}

	DataResult<AdditionalServiceItemResponse> getById(@RequestParam int id) {
		return this.additionalServiceItemService.getById(id);
	}

}
