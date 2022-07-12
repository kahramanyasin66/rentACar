package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.cities.CreateCityRequest;
import com.kodlamaio.rentACar.business.requests.cities.DeleteCityRequest;
import com.kodlamaio.rentACar.business.requests.cities.UpdateCityRequest;
import com.kodlamaio.rentACar.business.responses.cities.CityResponse;
import com.kodlamaio.rentACar.business.responses.cities.ListCityResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface CityService {
	Result add(CreateCityRequest createCityRequest);

	Result update(UpdateCityRequest updateCityRequest);

	Result delete(DeleteCityRequest deleteCityRequest);

	DataResult<List<ListCityResponse>> getall();

	DataResult<CityResponse> getById(int id);

}
