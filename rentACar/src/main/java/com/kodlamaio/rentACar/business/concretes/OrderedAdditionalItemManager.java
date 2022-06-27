package com.kodlamaio.rentACar.business.concretes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.OrderedAdditionalItemService;
import com.kodlamaio.rentACar.business.requests.orderedAdditionalItems.CreateOrderedAdditionalItemRequest;
import com.kodlamaio.rentACar.business.requests.orderedAdditionalItems.DeleteOrderedAdditionalItemRequest;
import com.kodlamaio.rentACar.business.requests.orderedAdditionalItems.UpdateOrderedAdditionalItemRequest;
import com.kodlamaio.rentACar.business.responses.orderedAdditionalItems.ListOrderedAdditionalItemResponse;
import com.kodlamaio.rentACar.business.responses.orderedAdditionalItems.OrderedAdditionalItemResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.AdditionalItemRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.OrderedAdditionalRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.RentalRepository;
import com.kodlamaio.rentACar.entities.concretes.AdditionalItem;
import com.kodlamaio.rentACar.entities.concretes.OrderedAdditionalItem;
import com.kodlamaio.rentACar.entities.concretes.Rental;

@Service
public class OrderedAdditionalItemManager implements OrderedAdditionalItemService {
	private OrderedAdditionalRepository orderedAdditionalRepository;
	private AdditionalItemRepository additionalItemRepository;
	private ModelMapperService modelMapperService;
	private RentalRepository rentalRepository;
	
	@Autowired
	public OrderedAdditionalItemManager(OrderedAdditionalRepository orderedAdditionalRepository,
			AdditionalItemRepository additionalItemRepository, ModelMapperService modelMapperService,
			RentalRepository rentalRepository) {
		super();
		this.orderedAdditionalRepository = orderedAdditionalRepository;
		this.additionalItemRepository = additionalItemRepository;
		this.modelMapperService = modelMapperService;
		this.rentalRepository = rentalRepository;
	}



	

	@Override
	public Result add(CreateOrderedAdditionalItemRequest createOrderedAdditionalItemRequest) {
	    checkIfAdditionalItemIdExist(createOrderedAdditionalItemRequest.getAdditionalItemId());
		checkIfDatesAreCorrect(createOrderedAdditionalItemRequest.getPickUpDate(),createOrderedAdditionalItemRequest.getReturnDate());
        checkIfRentalIdExists(createOrderedAdditionalItemRequest.getRentalId());
		
		AdditionalItem additionalItem = this.additionalItemRepository
				.findById(createOrderedAdditionalItemRequest.getAdditionalItemId());
	

		OrderedAdditionalItem orderedAdditionalItem = this.modelMapperService.forRequest()
				.map(createOrderedAdditionalItemRequest, OrderedAdditionalItem.class);

		orderedAdditionalItem.setTotalPrice(calculateTotalPrice(orderedAdditionalItem, additionalItem.getDailyPrice()));
		
		this.orderedAdditionalRepository.save(orderedAdditionalItem);

		return new SuccessResult("ORDERED.ADDITONAL.ITEM.ADDED");
	}

	@Override
	public Result update(UpdateOrderedAdditionalItemRequest updateOrderedAdditionalItemRequest) {
		checkIfAdditionalItemIdExist(updateOrderedAdditionalItemRequest.getAdditionalItemId());
		checkIfRentalIdExists(updateOrderedAdditionalItemRequest.getRentalId());
		checkIfOrderedAdditionalItemIdExists(updateOrderedAdditionalItemRequest.getId());
		checkIfDatesAreCorrect(updateOrderedAdditionalItemRequest.getPickUpDate(),updateOrderedAdditionalItemRequest.getReturnDate());
		
				
		OrderedAdditionalItem updateToOrderedAdditionalItem = this.modelMapperService.forRequest()
				.map(updateOrderedAdditionalItemRequest, OrderedAdditionalItem.class);
		this.orderedAdditionalRepository.save(updateToOrderedAdditionalItem);

		return new SuccessResult("ORDERED.ADDITONAL.ITEM.ADDED");

	}

	@Override
	public Result delete(DeleteOrderedAdditionalItemRequest deleteOrderedAdditionalItemRequest) {
		OrderedAdditionalItem deleteToOrderedAdditionalItem = this.modelMapperService.forRequest()
				.map(deleteOrderedAdditionalItemRequest, OrderedAdditionalItem.class);
		
		this.orderedAdditionalRepository.delete(deleteToOrderedAdditionalItem);

		return new SuccessResult("ORDERED.ADDITONAL.ITEM.DELETED");
	}

	@Override
	public DataResult<List<ListOrderedAdditionalItemResponse>> getAll() {
		List<OrderedAdditionalItem> orderedAdditionalItems = this.orderedAdditionalRepository.findAll();
		List<ListOrderedAdditionalItemResponse> response = orderedAdditionalItems.stream()
				.map(orderedAdditionalItem -> this.modelMapperService.forResponse().map(orderedAdditionalItems,
						ListOrderedAdditionalItemResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<ListOrderedAdditionalItemResponse>>(
				response + "ORDERED.ADDITONAL.ITEMS.LISTED");

	}

	@Override
	public DataResult<OrderedAdditionalItemResponse> getById(int id) {
		OrderedAdditionalItem orderedAdditionalItem = this.orderedAdditionalRepository.findById(id);
		OrderedAdditionalItemResponse  response = this.modelMapperService.forResponse()
				.map(orderedAdditionalItem, OrderedAdditionalItemResponse.class);

		return new SuccessDataResult<OrderedAdditionalItemResponse>(response+"ORDERED.ADDITONAL.ITEM.GETTED");
	}

	private double calculateTotalPrice(OrderedAdditionalItem orderedAdditionalItem, double price) {
		//Seçilen AdditioanalItem nesnesinin kaç gün olacaksa fiyatını  hesaplattırıyoruz.
		double totalPrice = 0;
		int daysDifference = (int) ChronoUnit.DAYS.between(orderedAdditionalItem.getPickUpDate(),
				orderedAdditionalItem.getReturnDate());
		totalPrice = price * daysDifference;
		orderedAdditionalItem.setTotalDays(daysDifference);
		return totalPrice;
	}
	private void checkIfOrderedAdditionalItemIdExists (int id) {
		OrderedAdditionalItem orderedAdditionalItem = this.orderedAdditionalRepository.findById(id);
		if(orderedAdditionalItem == null) {
			throw new BusinessException("THERE.IS.NOT.OPEREDED.ADDITIONAL.ITEM");
		}
	}
	
	private void checkIfAdditionalItemIdExist(int id) {
		AdditionalItem additionalItem = this.additionalItemRepository.findById(id);
		if(additionalItem == null ) {
			throw new BusinessException("THERE.IS.NOT.ADDITIONAL.ITEM");
		}
		
	}
	private void checkIfRentalIdExists(int id) {
		Rental rental = this.rentalRepository.findById(id);
		
		if(rental == null ) {
			throw new BusinessException("THERE.IS.NOT.RENTAL");
		}
		
	}
	private void  checkIfDatesAreCorrect(LocalDate pickUpDate , LocalDate returnDate ) {
     
		if (!pickUpDate.isBefore(returnDate) || pickUpDate.isBefore(LocalDate.now())) {
			throw new BusinessException("DATE.ERROR");
		}
		
	}

}
