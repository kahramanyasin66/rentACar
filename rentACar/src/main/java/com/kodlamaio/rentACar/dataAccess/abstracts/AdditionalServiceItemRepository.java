package com.kodlamaio.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.rentACar.entities.concretes.AdditionalServiceItem;

public interface AdditionalServiceItemRepository extends JpaRepository<AdditionalServiceItem, Integer> {
	AdditionalServiceItem findById(int id);

}
