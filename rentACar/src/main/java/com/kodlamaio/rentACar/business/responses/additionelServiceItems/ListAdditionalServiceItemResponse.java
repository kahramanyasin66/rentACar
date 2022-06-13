package com.kodlamaio.rentACar.business.responses.additionelServiceItems;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ListAdditionalServiceItemResponse {
	private String name;
	private double additionalPrice;

}
