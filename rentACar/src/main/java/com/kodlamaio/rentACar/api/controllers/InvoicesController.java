package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.InvoiceService;
import com.kodlamaio.rentACar.business.responses.invoices.InvoiceResponse;
import com.kodlamaio.rentACar.business.responses.invoices.ListInvoiceResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;

@RestController
@RequestMapping("/invoices")
public class InvoicesController {
	private InvoiceService invoiceService;

	public InvoicesController(InvoiceService invoiceService) {

		this.invoiceService = invoiceService;
	}

	@GetMapping("/getall")
	public DataResult<List<ListInvoiceResponse>> getAll() {
		return this.invoiceService.getAll();
	}

	@GetMapping("/getbyid")
	public DataResult<InvoiceResponse> getById(@RequestParam int id) {
		return this.invoiceService.getById(id);
	}

}
