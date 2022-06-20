package com.kodlamaio.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.rentACar.entities.concretes.RentalDetail;

public interface RentalDetailRepository extends JpaRepository<RentalDetail, Integer> {
	RentalDetail findById(int id);

}
