package com.kodlamaio.rentACar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kodlamaio.rentACar.entities.concretes.OrderedAdditionalItems;


public interface AdditionalServiceRepository extends JpaRepository<OrderedAdditionalItems, Integer> {
	 OrderedAdditionalItems findById(int id);

}
