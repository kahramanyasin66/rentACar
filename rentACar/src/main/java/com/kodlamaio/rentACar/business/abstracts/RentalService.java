package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.rentals.CreateRentalRequest;
import com.kodlamaio.rentACar.business.requests.rentals.DeleteRentalRequest;
import com.kodlamaio.rentACar.business.requests.rentals.UpdateRentalRequest;
import com.kodlamaio.rentACar.business.responses.rentals.ListRentalResponse;
import com.kodlamaio.rentACar.business.responses.rentals.RentalResponse;
import com.kodlamaio.rentACar.business.responses.users.ListUserResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.Rental;

public interface RentalService {
	Result addIndividualCustomer(CreateRentalRequest createRentalRequest);

	Result addCorporateCustomer(CreateRentalRequest createRentalRequest);

	Result updateIndividualCustomer(UpdateRentalRequest updateRentalRequest);

	Result updateCorporateCustomer(UpdateRentalRequest updateRentalRequest);
	
	Result delete(DeleteRentalRequest deleteRentalRequest);
	
	

	DataResult<List<ListRentalResponse>> getall();
	DataResult<List<ListRentalResponse>> getAll(Integer pageNo, Integer pageSize);
	DataResult<RentalResponse> getById(int id);	

	Rental findByRentalId(int id);

}