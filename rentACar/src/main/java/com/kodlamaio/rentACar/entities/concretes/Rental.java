package com.kodlamaio.rentACar.entities.concretes;

import java.time.LocalDate;

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
	private long totalDate;

	@Column(name = "total_price")
	private double totalPrice;

	@ManyToOne
	@JoinColumn(name = "car_id")
	private Car car;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	User user;	
	
	@ManyToOne
	@JoinColumn (name ="pick_up_city_id", referencedColumnName = "id")
	City pickUpCityId ;
	
	@ManyToOne
	@JoinColumn (name="returned_city_id", referencedColumnName = "id")
	City returnedCityId ;
	
	
	

}