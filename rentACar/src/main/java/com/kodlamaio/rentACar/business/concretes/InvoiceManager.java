package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.InvoiceService;
import com.kodlamaio.rentACar.business.requests.invoices.CreateInvoiceRequest;
import com.kodlamaio.rentACar.business.responses.invoices.InvoiceResponse;
import com.kodlamaio.rentACar.business.responses.invoices.ListInvoiceResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.InvoiceRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.RentalRepository;
import com.kodlamaio.rentACar.entities.concretes.Invoice;

@Service
public class InvoiceManager implements InvoiceService {
	private InvoiceRepository invoiceRepository;
	private RentalRepository rentalRepository ;
	private ModelMapperService modelMapperService;

	@Autowired
	public InvoiceManager(InvoiceRepository invoiceRepository, ModelMapperService modelMapperService, RentalRepository rentalRepository) {
		
		this.invoiceRepository = invoiceRepository;
		this.modelMapperService = modelMapperService;
		this.rentalRepository = rentalRepository ;
		
	}
	@Override
	public Result add(CreateInvoiceRequest createInvoiceRequest) {
		
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
		Invoice invoice = this.invoiceRepository.findById(id);
		InvoiceResponse response = this.modelMapperService.forResponse().map(invoice, InvoiceResponse.class);
		return new SuccessDataResult<InvoiceResponse>(response, "INVOICE.GETTED");
	}

	

}
