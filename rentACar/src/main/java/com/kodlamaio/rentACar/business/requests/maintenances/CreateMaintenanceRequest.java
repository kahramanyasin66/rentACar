package com.kodlamaio.rentACar.business.requests.maintenances;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CreateMaintenanceRequest {
	private String description;
	private LocalDate dateSent;
	private LocalDate dateReturned;
	private int carId;

}