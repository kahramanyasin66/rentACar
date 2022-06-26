package com.kodlamaio.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.rentACar.entities.concretes.AdditionalItem;

public interface AdditionalServiceItemRepository extends JpaRepository<AdditionalItem, Integer> {
	AdditionalItem findById(int id);

}
