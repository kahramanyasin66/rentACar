package com.kodlamaio.rentACar.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.AdditionalServiceItemService;
import com.kodlamaio.rentACar.business.requests.additionalServiceItems.DeleteAdditionalServiceItemRequest;
import com.kodlamaio.rentACar.business.requests.additionalServices.CreateAdditionalServiceRequest;
import com.kodlamaio.rentACar.business.requests.additionalServices.UpdateAdditionalServiceRequest;
import com.kodlamaio.rentACar.business.responses.additionalServices.AdditionalServiceResponse;
import com.kodlamaio.rentACar.business.responses.additionelServiceItems.ListAdditionalServiceItemResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

@Service
public class AdditionalServiceItemManager implements AdditionalServiceItemService {

	@Override
	public Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result delete(DeleteAdditionalServiceItemRequest deleteAdditionalServiceItemRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataResult<List<ListAdditionalServiceItemResponse>> getall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataResult<AdditionalServiceResponse> getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
