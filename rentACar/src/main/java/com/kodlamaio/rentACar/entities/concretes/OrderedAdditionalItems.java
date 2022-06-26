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
@Table(name = "ordered_additional_items")  //OrderedAdditionalItems
public class OrderedAdditionalItems {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "totalPrice")
	private double totalPrice;

	@Column(name = "return_date")
	private LocalDate returnDate;

	@Column(name = "pick_up_date")
	private LocalDate pickUpDate;

	@ManyToOne
	@JoinColumn(name = "additional_item_id")
	private AdditionalItem additionalItem;

	@ManyToOne
	@JoinColumn(name = "rental_id")
	private Rental rental;

	@OneToMany(mappedBy = "ordered_additional_item")
	private List<RentalDetail> rentalDetails;

}