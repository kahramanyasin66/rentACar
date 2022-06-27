package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.OrderedAdditionalItemService;
import com.kodlamaio.rentACar.business.requests.orderedAdditionalItems.CreateOrderedAdditionalItemRequest;
import com.kodlamaio.rentACar.business.requests.orderedAdditionalItems.DeleteOrderedAdditionalItemRequest;
import com.kodlamaio.rentACar.business.requests.orderedAdditionalItems.UpdateOrderedAdditionalItemRequest;
import com.kodlamaio.rentACar.business.responses.orderedAdditionalItems.ListOrderedAdditionalItemResponse;
import com.kodlamaio.rentACar.business.responses.orderedAdditionalItems.OrderedAdditionalItemResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("/api/orderedadditionalitems")
public class OrderedAdditionalItemsController {
	private OrderedAdditionalItemService orderedAdditionalItemService;

	public OrderedAdditionalItemsController(OrderedAdditionalItemService orderedAdditionalItemService) {

		this.orderedAdditionalItemService = orderedAdditionalItemService;
	}

	@PostMapping("/add")
	Result add(@RequestBody @Valid CreateOrderedAdditionalItemRequest createOrderedAdditionalItemRequest) {

		return this.orderedAdditionalItemService.add(createOrderedAdditionalItemRequest);

	}

	@PostMapping("/update")
	Result update(@RequestBody @Valid UpdateOrderedAdditionalItemRequest updateOrderedAdditionalItemRequest) {
		return this.orderedAdditionalItemService.update(updateOrderedAdditionalItemRequest);
	}

	@PostMapping("/delete")
	Result delete(@RequestBody @Valid DeleteOrderedAdditionalItemRequest deleteOrderedAdditionalItemRequest) {
		return this.orderedAdditionalItemService.delete(deleteOrderedAdditionalItemRequest);
	}

	@GetMapping("/getall")
	DataResult<List<ListOrderedAdditionalItemResponse>> getAll() {
		return this.orderedAdditionalItemService.getAll();
	}

	@GetMapping("/getbyid")
	DataResult<OrderedAdditionalItemResponse> getById(@RequestParam int id) {
		return this.orderedAdditionalItemService.getById(id);
	}
}
