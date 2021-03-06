package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.cars.CreateCarRequest;
import com.kodlamaio.rentACar.business.requests.cars.DeleteCarRequest;
import com.kodlamaio.rentACar.business.requests.cars.UpdateCarRequest;
import com.kodlamaio.rentACar.business.responses.cars.CarResponse;
import com.kodlamaio.rentACar.business.responses.cars.ListCarResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.Car;

public interface CarService {
	Result add(CreateCarRequest createCarRequest);

	Result delete(DeleteCarRequest deleteCarRequest);

	Result update(UpdateCarRequest updateCarRequest);

	DataResult<List<ListCarResponse>> getAll();
	
	DataResult<CarResponse> getById(int id);

    Car findByCarId(int id);
    
    
    
    DataResult<List<ListCarResponse>> getAllSortedByFindeksNumber();
	DataResult<List<ListCarResponse>> getAllSortedByFindeksNumberAndKilometer();
	DataResult<List<ListCarResponse>> getByState(int state);
	DataResult<List<ListCarResponse>> getByCityName(String cityName);
	DataResult<List<ListCarResponse>> getByBrandAndColor(String brandName,String colorName);
	DataResult<List<ListCarResponse>> getDailyPriceGreaterThan(double dailyPrice);

}