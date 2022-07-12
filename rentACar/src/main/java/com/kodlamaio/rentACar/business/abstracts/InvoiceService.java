package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.invoices.CreateInvoiceRequest;
import com.kodlamaio.rentACar.business.requests.invoices.DeleteInvoiceRequest;
import com.kodlamaio.rentACar.business.requests.invoices.UpdateInvoiceRequest;
import com.kodlamaio.rentACar.business.responses.invoices.InvoiceResponse;
import com.kodlamaio.rentACar.business.responses.invoices.ListInvoiceResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.AdditionalItem;

public interface InvoiceService {
	
	Result add(CreateInvoiceRequest createInvoiceRequest);

	Result delete(DeleteInvoiceRequest deleteInvoiceRequest);

	Result update(UpdateInvoiceRequest updateInvoiceRequest);
	
	DataResult<List<ListInvoiceResponse>> getAll();

	DataResult<InvoiceResponse> getById(int id);
	
	DataResult<List<AdditionalItem>> getAllAdditionalItemByRentalId (int rentalId);
	
	


}
