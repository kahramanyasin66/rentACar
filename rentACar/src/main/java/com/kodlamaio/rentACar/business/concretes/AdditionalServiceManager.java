package com.kodlamaio.rentACar.business.concretes;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.AdditionalServiceService;
import com.kodlamaio.rentACar.business.requests.additionalServices.CreateAdditionalServiceRequest;
import com.kodlamaio.rentACar.business.requests.additionalServices.DeleteAdditionalServiceRequest;
import com.kodlamaio.rentACar.business.requests.additionalServices.UpdateAdditionalServiceRequest;
import com.kodlamaio.rentACar.business.responses.additionalServices.AdditionalServiceResponse;
import com.kodlamaio.rentACar.business.responses.additionalServices.ListAdditionalServiceResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

@Service
public class AdditionalServiceManager implements AdditionalServiceService {

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
	public Result delete(DeleteAdditionalServiceRequest deleteAdditionalServiceRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataResult<ListAdditionalServiceResponse> getall() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DataResult<AdditionalServiceResponse> getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
