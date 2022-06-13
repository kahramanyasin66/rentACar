package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.CityService;
import com.kodlamaio.rentACar.business.requests.cities.CreateCityRequest;
import com.kodlamaio.rentACar.business.requests.cities.DeleteCityRequest;
import com.kodlamaio.rentACar.business.requests.cities.UpdateCityRequest;
import com.kodlamaio.rentACar.business.responses.cities.CityResponse;
import com.kodlamaio.rentACar.business.responses.cities.ListCityResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.CityRepository;
import com.kodlamaio.rentACar.entities.concretes.City;

@Service
public class CityManager implements CityService {
	CityRepository cityRepository;
	ModelMapperService modelMapperService;

	@Autowired
	public CityManager(CityRepository cityRepository, ModelMapperService modelMapperService) {
		this.cityRepository = cityRepository;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateCityRequest createCityRequest) {
		City city = modelMapperService.forRequest().map(createCityRequest, City.class);
		this.cityRepository.save(city);
		return new SuccessResult("CITY.ADDED");
	}

	@Override
	public Result update(UpdateCityRequest updateCityRequest) {
		City cityToUpdate = this.modelMapperService.forRequest().map(updateCityRequest, City.class);
		this.cityRepository.save(cityToUpdate);

		return new SuccessResult("CITY.UPDATED");
	}

	@Override
	public Result delete(DeleteCityRequest deleteCityRequest) {
		int cityId = deleteCityRequest.getId();
		this.cityRepository.deleteById(cityId);
		return new SuccessResult("CITY.DELETED");
	}

	@Override
	public DataResult<List<ListCityResponse>> getall() {
		List<City> cities = this.cityRepository.findAll();
		List<ListCityResponse> response = cities.stream()
				.map(city -> this.modelMapperService.forResponse().map(city, ListCityResponse.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<ListCityResponse>>(response, "CITIES:GETTED");
	}

	@Override
	public DataResult<CityResponse> getById(int id) {
		City city = this.cityRepository.findById(id);
		CityResponse response = this.modelMapperService.forResponse().map(city, CityResponse.class);

		return new SuccessDataResult<CityResponse>(response, "CITY.GETTED");
	}

}
