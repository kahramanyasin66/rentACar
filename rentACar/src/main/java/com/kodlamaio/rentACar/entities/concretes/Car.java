package com.kodlamaio.rentACar.entities.concretes;

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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder //mapping 
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cars")
public class Car {
	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "description")
	private String description;

	@Column(name = "daily_price")
	private double dailyPrice;

	@Column(name = "kilometer")
	private double kilometer;

	@Column(name = "plate")
	private String plate;

	@Column(name = "car_state")
	private int carState;

	@Column(name = "car_score") // findexScore ile eşleştirme yapmak için 
	private int carScore;

	@ManyToOne
	@JoinColumn(name = "brand_id") // veritabanında markaları bu şekilde tut bu tabloda
	private Brand brand;

	@ManyToOne
	@JoinColumn(name = "color_id")
	private Color color;

	@OneToMany(mappedBy = "car") // bakımdaki arabalar
	private List<Maintenance> maintenances;

	@OneToMany(mappedBy = "car") // kiradaki arabalar
	private List<Rental> rentals;

//	@ManyToOne
//	@JoinColumn(name = "city_id")  
//	private City city;

}