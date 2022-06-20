package com.kodlamaio.rentACar.business.responses.invoices;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListInvoiceResponse {
	private int id;
	private int invoiceNumber;
	private int rentalDetailId; 
	private double totalPrice;
	
}
