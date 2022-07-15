package com.kodlamaio.rentACar.api.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kodlamaio.rentACar.business.abstracts.CarService;
import com.kodlamaio.rentACar.business.requests.cars.CreateCarRequest;
import com.kodlamaio.rentACar.business.requests.cars.DeleteCarRequest;
import com.kodlamaio.rentACar.business.requests.cars.UpdateCarRequest;
import com.kodlamaio.rentACar.business.responses.cars.CarResponse;
import com.kodlamaio.rentACar.business.responses.cars.ListCarResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

@RestController
@RequestMapping("api/cars")
public class CarsController {
	private CarService carService;

	public CarsController(CarService carService) {
		this.carService = carService;
	}

	@PostMapping("/add")
	public Result add(@RequestBody @Valid CreateCarRequest createCarRequest) {
		return this.carService.add(createCarRequest);

	}

	@PostMapping("/update")
	public Result update(@RequestBody @Valid UpdateCarRequest updateCarRequest) {
		return this.carService.update(updateCarRequest);
	}

	@PostMapping("/delete")
	public Result delete(@RequestBody DeleteCarRequest deleteCarRequest) {
		return this.carService.delete(deleteCarRequest);
	}

	@GetMapping("/getall")
	public DataResult<List<ListCarResponse>> getAll() {
		return this.carService.getAll();
	}

	@GetMapping("/getbyid")
	public DataResult<CarResponse> getById(@RequestParam int id) {
		return this.carService.getById(id);
		// this.carService.getById(id);
	}
	/*-------------------------OTHERS----------------------------*/
	@GetMapping("/getbystate") 
	public DataResult<List<ListCarResponse>> getByState(@RequestParam int state) {
		return this.carService.getByState(state);
	}
	
	@GetMapping("/getbycityname")
	public DataResult<List<ListCarResponse>> getByCityName(@RequestParam String cityname) {
		return this.carService.getByCityName(cityname);
	}
	
	@GetMapping("/getbybrandandcolorname")
	public DataResult<List<ListCarResponse>> getByBrandAndColor(String brandName,String colorName) {
		return this.carService.getByBrandAndColor(brandName,colorName);
	}
	
	@GetMapping("/getallsortedbyfindeksnumber")
	public DataResult<List<ListCarResponse>> getAllSortedByFindeksNumber() {
		return this.carService.getAllSortedByFindeksNumber();
	}
	
	@GetMapping("/getallsortedbyfindeksnumberandkilometer")
	public DataResult<List<ListCarResponse>> getAllSortedByFindeksNumberAndKilometer() {
		return this.carService.getAllSortedByFindeksNumberAndKilometer();
	}
	
	@GetMapping("/getdailypricegreaterthan")
	public DataResult<List<ListCarResponse>> getDailyPriceGreaterThan(double dailyPrice) {
		return this.carService.getDailyPriceGreaterThan(dailyPrice);
	}
	
	
	
	

}