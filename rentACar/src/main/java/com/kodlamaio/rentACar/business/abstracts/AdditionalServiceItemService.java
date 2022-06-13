package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.additionalServiceItems.CreateAdditionalServiceItemRequest;
import com.kodlamaio.rentACar.business.requests.additionalServiceItems.DeleteAdditionalServiceItemRequest;
import com.kodlamaio.rentACar.business.requests.additionalServiceItems.UpdateAdditionalServiceItemRequest;
import com.kodlamaio.rentACar.business.responses.additionelServiceItems.AdditionalServiceItemResponse;
import com.kodlamaio.rentACar.business.responses.additionelServiceItems.ListAdditionalServiceItemResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface AdditionalServiceItemService {
	Result add(CreateAdditionalServiceItemRequest createAdditionalServiceItemRequest);

	Result update(UpdateAdditionalServiceItemRequest updateAdditionalServiceItemRequest);

	Result delete(DeleteAdditionalServiceItemRequest deleteAdditionalServiceItemRequest);

	DataResult<List<ListAdditionalServiceItemResponse>> getall();

	DataResult<AdditionalServiceItemResponse> getById(int id);

}
