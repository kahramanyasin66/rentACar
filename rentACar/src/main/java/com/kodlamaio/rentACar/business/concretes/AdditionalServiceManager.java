package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.AdditionalServiceService;
import com.kodlamaio.rentACar.business.requests.additionalServices.CreateAdditionalServiceRequest;
import com.kodlamaio.rentACar.business.requests.additionalServices.DeleteAdditionalServiceRequest;
import com.kodlamaio.rentACar.business.requests.additionalServices.UpdateAdditionalServiceRequest;
import com.kodlamaio.rentACar.business.responses.additionalServices.AdditionalServiceResponse;
import com.kodlamaio.rentACar.business.responses.additionalServices.ListAdditionalServiceResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.AdditionalServiceRepository;
import com.kodlamaio.rentACar.entities.concretes.AdditionalService;

@Service
public class AdditionalServiceManager implements AdditionalServiceService {

	AdditionalServiceRepository additionalServiceRepository;
	ModelMapperService modelMapperService;

	@Autowired
	public AdditionalServiceManager(AdditionalServiceRepository additionalServiceRepository,
			ModelMapperService modelMapperService) {

		this.additionalServiceRepository = additionalServiceRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest) {
		AdditionalService additionalService = this.modelMapperService.forRequest().map(createAdditionalServiceRequest,
				AdditionalService.class);
		this.additionalServiceRepository.save(additionalService);
		return new SuccessResult("ADDITIONAL_SERVİCE.ADDED");
	}

	@Override
	public Result update(UpdateAdditionalServiceRequest updateAdditionalServiceRequest) {
		AdditionalService updateToAdditionalService = this.modelMapperService.forRequest()
				.map(updateAdditionalServiceRequest, AdditionalService.class);
		this.additionalServiceRepository.save(updateToAdditionalService);

		return new SuccessResult("ADDITIONAL_SERVİCE.UPDATED");
	}

	@Override
	public Result delete(DeleteAdditionalServiceRequest deleteAdditionalServiceRequest) {
		AdditionalService additionalServiceId = this.additionalServiceRepository
				.findById(deleteAdditionalServiceRequest.getId());
		this.modelMapperService.forRequest().map(additionalServiceId, AdditionalService.class);
		this.additionalServiceRepository.delete(additionalServiceId);
		return new SuccessResult("ADDITIONAL_SERVİCE.DELETED");
	}

	@Override
	public DataResult<List<ListAdditionalServiceResponse>> getall() {
		List<AdditionalService> additionalServices = this.additionalServiceRepository.findAll();
		List<ListAdditionalServiceResponse> response = additionalServices.stream()
				.map(additionalService -> this.modelMapperService.forResponse().map(additionalService,
						ListAdditionalServiceResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListAdditionalServiceResponse>>(response, "ADDITIONALS.LISTED");
	}

	@Override
	public DataResult<AdditionalServiceResponse> getById(int id) {
		AdditionalService additionalService = this.additionalServiceRepository.findById(id);
		AdditionalServiceResponse response = this.modelMapperService.forResponse()
				.map(additionalService, AdditionalServiceResponse.class);		

		return new SuccessDataResult<AdditionalServiceResponse>(response,"ADDINATIONAL_SERVİCE.GETTED");
	}

}
