package com.kodlamaio.rentACar.business.requests.brands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// email frmatı uygunlğu tc 11 olması veri boş geçilemez validations oluyor

public class CreateBrandRequest {
	private String name;
	private int id;
}