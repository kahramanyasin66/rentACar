package com.kodlamaio.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.rentACar.entities.concretes.AdditionalService;

public interface AdditionalServiceRepository extends JpaRepository<AdditionalService, Integer> {
	AdditionalService findById(int id);

}
