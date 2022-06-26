package com.kodlamaio.rentACar.entities.concretes;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "rentals")
public class Rental {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "pickup_date")
	private LocalDate pickupDate;

	@Column(name = "return_date")
	private LocalDate returnDate;

	@Column(name = "total_date")
	private int totalDate;

	@Column(name = "total_price")
	private double totalPrice;

	@ManyToOne
	@JoinColumn(name = "car_id")
	private Car car;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	Customer customer;	
	
	@ManyToOne
	@JoinColumn (name ="pick_up_city_id", referencedColumnName = "id")
	City pickUpCityId ;
	
	@ManyToOne
	@JoinColumn (name="returned_city_id", referencedColumnName = "id")
	City returnedCityId ;
	
	@OneToMany(mappedBy = "rental")
	private List<OrderedAdditionalItems> orderedAdditionalItems;	
	

}