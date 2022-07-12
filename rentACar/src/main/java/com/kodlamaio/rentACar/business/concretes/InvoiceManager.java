package com.kodlamaio.rentACar.business.concretes;

import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.AdditionalItemService;
import com.kodlamaio.rentACar.business.abstracts.InvoiceService;
import com.kodlamaio.rentACar.business.abstracts.OrderedAdditionalItemService;
import com.kodlamaio.rentACar.business.abstracts.RentalService;
import com.kodlamaio.rentACar.business.requests.invoices.CreateInvoiceRequest;
import com.kodlamaio.rentACar.business.requests.invoices.DeleteInvoiceRequest;
import com.kodlamaio.rentACar.business.requests.invoices.UpdateInvoiceRequest;
import com.kodlamaio.rentACar.business.responses.invoices.InvoiceResponse;
import com.kodlamaio.rentACar.business.responses.invoices.ListInvoiceResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.InvoiceRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.RentalRepository;
import com.kodlamaio.rentACar.entities.concretes.AdditionalItem;
import com.kodlamaio.rentACar.entities.concretes.Invoice;
import com.kodlamaio.rentACar.entities.concretes.OrderedAdditionalItem;
import com.kodlamaio.rentACar.entities.concretes.Rental;

@Service
public class InvoiceManager implements InvoiceService {
	private InvoiceRepository invoiceRepository;
	private RentalService rentalService;
	private OrderedAdditionalItemService orderedAdditionalItemService;
	private AdditionalItemService additionalItemService;
	
	private ModelMapperService modelMapperService;

	@Autowired

	public InvoiceManager(InvoiceRepository invoiceRepository, RentalService rentalService,
			OrderedAdditionalItemService orderedAdditionalItemService, AdditionalItemService additionalItemService,
			ModelMapperService modelMapperService) {	
		this.invoiceRepository = invoiceRepository;
		this.rentalService = rentalService;
		this.orderedAdditionalItemService = orderedAdditionalItemService;
		this.additionalItemService = additionalItemService;
		this.modelMapperService = modelMapperService;
	}


	@Override
	public Result add(CreateInvoiceRequest createInvoiceRequest) {
		checkIfInvoiceStatusZero(createInvoiceRequest.getRentalId());
		checkIfInvoiceNumberExists(createInvoiceRequest.getInvoiceNumber());
		checkIfRentalIdExists(createInvoiceRequest.getRentalId());
		
		
		Invoice invoice = this.modelMapperService.forRequest().map(createInvoiceRequest, Invoice.class);
		invoice.setTotalPrice(calculateTotalPrice(createInvoiceRequest.getRentalId()));
		invoice.setState(0);
		this.invoiceRepository.save(invoice);		
		
		
		return new SuccessResult("INVOICE.ADDED");
	}

	@Override
	public DataResult<List<ListInvoiceResponse>> getAll() {
		List<Invoice> invoices = this.invoiceRepository.findAll();
		List<ListInvoiceResponse> response = invoices.stream()
				.map(invoice -> this.modelMapperService.forResponse().map(invoices, ListInvoiceResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<ListInvoiceResponse>>(response, "INVOICES.LISTED");
	}

	@Override
	public DataResult<InvoiceResponse> getById(int id) {
		checkIfInvoiceIdExists(id);
		Invoice invoice = this.invoiceRepository.findById(id);
		InvoiceResponse response = this.modelMapperService.forResponse().map(invoice, InvoiceResponse.class);
		return new SuccessDataResult<InvoiceResponse>(response, "INVOICE.GETTED");
	}


	@Override
	public Result delete(DeleteInvoiceRequest deleteInvoiceRequest) {
		checkIfInvoiceIdExists(deleteInvoiceRequest.getId());
		Invoice invoice = this.modelMapperService.forRequest().map(deleteInvoiceRequest, Invoice.class);
		invoice.setState(1);
		this.invoiceRepository.delete(invoice);
		return new SuccessResult("INVOICE.DELETED");
	}


	@Override
	public Result update(UpdateInvoiceRequest updateInvoiceRequest) {
		
		return null;
	}


	@Override
	public DataResult<List<AdditionalItem>> getAllAdditionalItemByRentalId(int rentalId) {
//		List<OrderedAdditionalItem> orderedAdditionalItems = this.orderedAdditionalItemService.findByOrderedAdditionalItemId(rentalId);
//		
//		List<AdditionalItem> additionalItems = new ArrayList<AdditionalItem>();
//		for(OrderedAdditionalItem orderedAdditionalItem : orderedAdditionalItems) {
//			AdditionalItem additionalItem = this.additionalItemService.findByAdditionalItemId(orderedAdditionalItem.getAdditionalItem().getId());
//			additionalItems.add(additionalItem);
//			
//		}
//		
//		return new SuccessDataResult<List<AdditionalItem>>("ADDITIONAL.ITEM.GETTED",additionalItems);
		return null ;
	}
	
	private double calculateTotalPrice(int rentalId) {
		Rental rental = this.rentalService.findByRentalId(rentalId);
		double price = calculateTotalPriceOfOrderedAdditionalItemByRentalId(rentalId);
		double totalPrice = rental.getTotalPrice() + price;
		return totalPrice;
	}

	private double calculateTotalPriceOfOrderedAdditionalItemByRentalId(int id)
	{
		Rental rental = this.rentalService.findByRentalId(id);
		List<OrderedAdditionalItem> lists = rental.getOrderedAdditionalItems();
		double totalPrice = 0;
		for (OrderedAdditionalItem additionalFeatureService : lists) {
			totalPrice += additionalFeatureService.getTotalPrice();
		}
		return totalPrice;
		
	}
	private void checkIfInvoiceStatusZero(int id) {
		Invoice invoicefromDb = this.invoiceRepository.findById(id); //rentalId
		if ((invoicefromDb != null) && (invoicefromDb.getState() != 1)) {
			throw new BusinessException("RENTAL.HAVE.ALREADY.A.INVOICE");
		}
	}
	private void checkIfInvoiceNumberExists(int invoiceNumber) {
		Invoice invoice = this.invoiceRepository.findByInvoiceNumber(invoiceNumber);
		if (invoice != null) {
			throw new BusinessException("INVOICE.NUMBER.EXISTS");
		}
	}
	private void checkIfInvoiceIdExists(int id) {
		Invoice invoice = this.invoiceRepository.findById(id);
		if(invoice == null) {
			throw new BusinessException("THERE.IS.NOT.INVOICE");
		}
	}
	private void checkIfRentalIdExists(int rentalId) {
		Rental rental = this.rentalService.findByRentalId(rentalId);
		if(rental == null) {
			throw new BusinessException("THERE.IS.NO.RENTED.CAR2");
		}
	}

}
