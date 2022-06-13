package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.AdditionalServiceItemService;
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
import com.kodlamaio.rentACar.dataAccess.abstracts.AdditionalServiceItemRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.AdditionalServiceRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.RentalRepository;
import com.kodlamaio.rentACar.entities.concretes.AdditionalService;
import com.kodlamaio.rentACar.entities.concretes.AdditionalServiceItem;
import com.kodlamaio.rentACar.entities.concretes.Rental;

@Service
public class AdditionalServiceManager implements AdditionalServiceService {
	@Autowired
	AdditionalServiceRepository additionalServiceRepository;
	@Autowired
	ModelMapperService modelMapperService;
	@Autowired
	AdditionalServiceItemRepository additionalServiceItemRepository;
	@Autowired
	RentalRepository rentalRepository;

	@Override
	public Result add(CreateAdditionalServiceRequest createAdditionalServiceRequest) {
		AdditionalService additionalService = this.modelMapperService.forRequest().map(createAdditionalServiceRequest,
				AdditionalService.class);
		Rental rental = this.rentalRepository.findById(createAdditionalServiceRequest.getRentalId());
		AdditionalServiceItem additionalServiceItem = this.additionalServiceItemRepository
				.findById(createAdditionalServiceRequest.getAdditionalServiceItemId());
		double  totalPrice =calculateTotalPrice(rental.getTotalDate(),additionalServiceItem.getAdditionalPrice());
		additionalService.setTotalPrice(totalPrice);

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
		AdditionalServiceResponse response = this.modelMapperService.forResponse().map(additionalService,
				AdditionalServiceResponse.class);

		return new SuccessDataResult<AdditionalServiceResponse>(response, "ADDINATIONAL_SERVİCE.GETTED");
	}

	private double calculateTotalPrice(int days, double price) {
		return days*price;

	}

}
