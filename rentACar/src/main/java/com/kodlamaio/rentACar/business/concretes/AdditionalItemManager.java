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
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
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
		checkIfAdditionalItemExistsByName(createAdditionalItemRequest.getName());	
		AdditionalItem additionalItem = this.modelMapperService.forRequest().map(createAdditionalItemRequest, AdditionalItem.class);
		checkIfAdditionalItemExistsByName(createAdditionalItemRequest.getName());
		this.additionalItemRepository.save(additionalItem);		
		
		return new SuccessResult("ADDITONAL.ITEM.ADDED");
	}

	@Override
	public Result update(UpdateAdditionalItemRequest updateAdditionalItemRequest) {
		checkIfAdditionalItemExistsById(updateAdditionalItemRequest.getId());
		AdditionalItem updateToAdditionalItem = this.modelMapperService.forRequest().map(updateAdditionalItemRequest, AdditionalItem.class);
		checkIfAdditionalItemExistsByName(updateAdditionalItemRequest.getName());
		this.additionalItemRepository.save(updateToAdditionalItem);
		
		return new SuccessResult("ADDITONAL.ITEM.UPDATED");
	}

	@Override
	public Result delete(DeleteAdditionalItemRequest deleteAdditionalItemRequest) {
		checkIfAdditionalItemExistsById(deleteAdditionalItemRequest.getId());
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
	
	
	
	
	
	
	
	
	private void checkIfAdditionalItemExistsByName(String name) {
		AdditionalItem additionalItem = this.additionalItemRepository.findByName(name);
		if (additionalItem != null) {
			throw new BusinessException("THERE.IS.NOT.ADDITIONAL.ITEM.NAME");
		}
	}
	private void checkIfAdditionalItemExistsById(int id) {
		AdditionalItem additionaltem = this.additionalItemRepository.findById(id);
		if(additionaltem == null) {
			throw new BusinessException("THERE.IS.NOT.ADDITIONAL.ITEM");
		}
	}

	@Override
	public AdditionalItem findByAdditionalItemId(int additionalItemId) {
		checkIfAdditionalItemExistsById(additionalItemId);
		AdditionalItem additionalItem = this.additionalItemRepository.findById(additionalItemId);
		return additionalItem;
	}

}
