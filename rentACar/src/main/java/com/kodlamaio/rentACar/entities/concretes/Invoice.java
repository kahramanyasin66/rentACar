package com.kodlamaio.rentACar.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="invoices")
public class Invoice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "invoice_number")
	private int invoiceNumber;
	
	@Column(name = "totalPrice")
	private double totalPrice;	

	@Column(name = "state")
	private int  state;
	
	@ManyToOne  //yok 
	@JoinColumn(name = "rental_details_id")
	private RentalDetail rentalDetail; 

}
