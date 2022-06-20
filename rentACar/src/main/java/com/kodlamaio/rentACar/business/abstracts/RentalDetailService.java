package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.rentalDetails.CreateRentalDetailRequest;
import com.kodlamaio.rentACar.business.requests.rentalDetails.DeleteRentalDetailRequest;
import com.kodlamaio.rentACar.business.requests.rentalDetails.UpdateRentalDetailRequest;
import com.kodlamaio.rentACar.business.responses.rentalDetails.ListRentalDetailResponse;
import com.kodlamaio.rentACar.business.responses.rentalDetails.RentalDetailResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface RentalDetailService {
	Result add(CreateRentalDetailRequest createRentalDetailRequest);

	Result update(UpdateRentalDetailRequest updateRentalDetailRequest);

	Result delete(DeleteRentalDetailRequest deleteRentalDetailRequest);

	DataResult<List<ListRentalDetailResponse>> getAll();

	DataResult<RentalDetailResponse> getById(int id);

}
