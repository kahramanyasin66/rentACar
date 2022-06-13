package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.additionalServiceItems.DeleteAdditionalServiceItemRequest;
import com.kodlamaio.rentACar.business.requests.additionalServices.CreateAdditionalServiceRequest;
import com.kodlamaio.rentACar.business.requests.additionalServices.UpdateAdditionalServiceRequest;
import com.kodlamaio.rentACar.business.responses.additionalServices.AdditionalServiceResponse;
import com.kodlamaio.rentACar.business.responses.additionelServiceItems.ListAdditionalServiceItemResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface AdditionalServiceItemService {
	Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest);

	Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest);

	Result delete(DeleteAdditionalServiceItemRequest deleteAdditionalServiceItemRequest);

	DataResult<List<ListAdditionalServiceItemResponse>> getall();

	DataResult<AdditionalServiceResponse> getById(int id);

}
