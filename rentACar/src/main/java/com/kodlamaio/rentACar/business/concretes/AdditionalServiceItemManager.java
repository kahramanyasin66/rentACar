package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.AdditionalServiceItemService;
import com.kodlamaio.rentACar.business.requests.additionalServiceItems.CreateAdditionalServiceItemRequest;
import com.kodlamaio.rentACar.business.requests.additionalServiceItems.DeleteAdditionalServiceItemRequest;
import com.kodlamaio.rentACar.business.requests.additionalServiceItems.UpdateAdditionalServiceItemRequest;
import com.kodlamaio.rentACar.business.responses.additionelServiceItems.AdditionalServiceItemResponse;
import com.kodlamaio.rentACar.business.responses.additionelServiceItems.ListAdditionalServiceItemResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.AdditionalServiceItemRepository;
import com.kodlamaio.rentACar.entities.concretes.AdditionalItem;

@Service
public class AdditionalServiceItemManager implements AdditionalServiceItemService {
	AdditionalServiceItemRepository additionalServiceItemRepository;
	ModelMapperService modelMapperService;
    @Autowired
	public AdditionalServiceItemManager(AdditionalServiceItemRepository additionalServiceItemRepository,
			ModelMapperService modelMapperService) {

		this.additionalServiceItemRepository = additionalServiceItemRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateAdditionalServiceItemRequest createAdditionalServiceItemRequest) {
		AdditionalItem additionalServiceItem = this.modelMapperService.forRequest()
				.map(createAdditionalServiceItemRequest, AdditionalItem.class);
		this.additionalServiceItemRepository.save(additionalServiceItem);

		return new SuccessResult("ADDINATIONAL_SERVICE_ITEM.ADDED");
	}

	@Override
	public Result update(UpdateAdditionalServiceItemRequest updateAdditionalServiceItemRequest) {
		AdditionalItem updateToAdditionalServiceItem = this.modelMapperService.forRequest()
				.map(updateAdditionalServiceItemRequest, AdditionalItem.class);
		this.additionalServiceItemRepository.save(updateToAdditionalServiceItem);
		return new SuccessResult("ADDINATIONAL_SERVICE_ITEM.UPDATED");
	}

	@Override
	public Result delete(DeleteAdditionalServiceItemRequest deleteAdditionalServiceItemRequest) {
		AdditionalItem additionalServiceItemId = this.additionalServiceItemRepository
				.findById(deleteAdditionalServiceItemRequest.getId());
		this.modelMapperService.forRequest().map(additionalServiceItemId, AdditionalItem.class);

		return new SuccessResult("ADDINATIONAL_SERVICE_ITEM.DELETED");
	}

	@Override
	public DataResult<List<ListAdditionalServiceItemResponse>> getall() {
		List<AdditionalItem> additionalServiceItems = this.additionalServiceItemRepository.findAll();
		List<ListAdditionalServiceItemResponse> response = additionalServiceItems.stream()
				.map(additionalServiceItem -> this.modelMapperService.forResponse().map(additionalServiceItem,
						ListAdditionalServiceItemResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListAdditionalServiceItemResponse>>(response,"ADDINATIONAL_SERVICE_ITEMS.GETTED");
	}

	@Override
	public DataResult<AdditionalServiceItemResponse> getById(int id) {
		AdditionalItem additionalServiceItemId = this.additionalServiceItemRepository.findById(id);

		AdditionalServiceItemResponse response = this.modelMapperService.forResponse().map(additionalServiceItemId,
				AdditionalServiceItemResponse.class);

		return new SuccessDataResult<AdditionalServiceItemResponse>(response,"ADDINATIONAL_SERVICE_ITEM.GETTED");
	}

}
