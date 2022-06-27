package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.AdditionalItemService;
import com.kodlamaio.rentACar.business.requests.additionalItems.CreateAdditionalItemRequest;
import com.kodlamaio.rentACar.business.requests.additionalItems.DeleteAdditionalItemRequest;
import com.kodlamaio.rentACar.business.requests.additionalItems.UpdateAdditionalItemRequest;
import com.kodlamaio.rentACar.business.responses.additionalItems.AdditionalItemResponse;
import com.kodlamaio.rentACar.business.responses.additionalItems.ListAdditionalItemResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.AdditionalItemRepository;
import com.kodlamaio.rentACar.entities.concretes.AdditionalItem;
@Service

public class AdditionalItemManager implements AdditionalItemService{
	private AdditionalItemRepository additionalItemRepository;
	private ModelMapperService modelMapperService;
	
    @Autowired
	public AdditionalItemManager(AdditionalItemRepository additionalItemRepository,
			ModelMapperService modelMapperService) {
		
		this.additionalItemRepository = additionalItemRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateAdditionalItemRequest createAdditionalItemRequest) {
		AdditionalItem additionalItem = this.modelMapperService.forRequest().map(createAdditionalItemRequest, AdditionalItem.class);
		this.additionalItemRepository.save(additionalItem);		
		
		return new SuccessResult("ADDITONAL.ITEM.ADDED");
	}

	@Override
	public Result update(UpdateAdditionalItemRequest updateAdditionalItemRequest) {
		AdditionalItem updateToAdditionalItem = this.modelMapperService.forRequest().map(updateAdditionalItemRequest, AdditionalItem.class);
		this.additionalItemRepository.save(updateToAdditionalItem);
		
		return new SuccessResult("ADDITONAL.ITEM.UPDATED");
	}

	@Override
	public Result delete(DeleteAdditionalItemRequest deleteAdditionalItemRequest) {
		AdditionalItem deleteToAdditionalItem = this.modelMapperService.forRequest().map(deleteAdditionalItemRequest, AdditionalItem.class);
		this.additionalItemRepository.delete(deleteToAdditionalItem);
		
		return new SuccessResult("ADDITONAL.ITEM.DELETED");		
	}

	@Override
	public DataResult<List<ListAdditionalItemResponse>> getAll() {
	    List<AdditionalItem> additionalItems = this.additionalItemRepository.findAll();
	    
		List<ListAdditionalItemResponse> response = additionalItems.stream()				
				.map(additionalItem -> this.modelMapperService.forResponse().map(additionalItem,
						ListAdditionalItemResponse.class))
				.collect(Collectors.toList());
		
		return new SuccessDataResult<List<ListAdditionalItemResponse>>(response,"ADDITIONAL.ITEMS.LISTED");
	}

	@Override
	public DataResult<AdditionalItemResponse> getById(int id) {
		AdditionalItem additionalItemId = this.additionalItemRepository.findById(id);
		AdditionalItemResponse response = this.modelMapperService.forResponse()
				.map(additionalItemId,AdditionalItemResponse.class);
		
		return new SuccessDataResult<AdditionalItemResponse>(response,"ADDITIONAL.ITEM.GETTED");
	}

}
