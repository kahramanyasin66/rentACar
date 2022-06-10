package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.CarService;
import com.kodlamaio.rentACar.business.requests.cars.CreateCarRequest;
import com.kodlamaio.rentACar.business.requests.cars.DeleteCarRequest;
import com.kodlamaio.rentACar.business.requests.cars.UpdateCarRequest;
import com.kodlamaio.rentACar.business.responses.cars.CarResponse;
import com.kodlamaio.rentACar.business.responses.cars.ListCarResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.ErrorResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.BrandRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.CarRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.ColorRepository;
import com.kodlamaio.rentACar.entities.concretes.Car;

@Service
public class CarManager implements CarService {
	CarRepository carRepository;
	ModelMapperService modelMapperService;

	@Autowired
	public CarManager(CarRepository carRepository, ModelMapperService modelMapperService) {

		this.carRepository = carRepository;
		this.modelMapperService = modelMapperService;

	}

	@Override
	public Result add(CreateCarRequest createCarRequest) {
		if (!checkBrandCount(createCarRequest.getBrandId())) {

			/*
			 * Car car = new Car(); car.setDescription(createCarRequest.getDescription());
			 * // cretaCarRequest'in açıklması geliyor
			 * car.setDailyPrice(createCarRequest.getDailyPrice());
			 * 
			 * Brand brand = new Brand(); brand.setId(createCarRequest.getBrandId());
			 * car.setBrand(brand);
			 * 
			 * Color color = new Color(); color.setId(createCarRequest.getColorId());
			 * car.setColor(color);
			 */

			Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
			car.setCarState(1);

			this.carRepository.save(car);

			return new SuccessResult("CAR.ADDED");

		}

		return new ErrorResult("CAR.CAN NOT ADDED ");

	}

	@Override
	public Result delete(DeleteCarRequest deleteCarRequest) {
		int carId = deleteCarRequest.getId();
		this.carRepository.deleteById(carId);
		return new SuccessResult("CAR.DELETED");

	}

	@Override
	public Result update(UpdateCarRequest updateCarRequest) {
		/*
		 * Car carToUpdate = carRepository.findById(updateCarRequest.getId()); Brand
		 * brand = new Brand(); brand.setId(updateCarRequest.getBrandId());
		 * 
		 * Color color = new Color(); color.setId(updateCarRequest.getColorId());
		 * 
		 * updateCarRequest.setBrandId(brand.getId());
		 * updateCarRequest.setColorId(color.getId());
		 * updateCarRequest.setDailyPrice(carToUpdate.getDailyPrice());
		 * updateCarRequest.setDescription(carToUpdate.getDescription());
		 */
		Car carToUpdate = modelMapperService.forRequest().map(updateCarRequest, Car.class);

		this.carRepository.save(carToUpdate);

		return new SuccessResult("CAR.UPDATED");

	}

	@Override
	public DataResult<List<ListCarResponse>> getAll() {
		List<Car> cars = this.carRepository.findAll();
		List<ListCarResponse> response = cars.stream()
				.map(car -> this.modelMapperService.forResponse().map(car, ListCarResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListCarResponse>>(response, "CARS.GETTED");

		// new SuccessDataResult<List<ListCarResponse>>(this.carRepository.findAll());
	}

	@Override
	public DataResult<CarResponse> getById(int id) {
		Car car = carRepository.findById(id);
		CarResponse response = this.modelMapperService.forResponse().map(car, CarResponse.class);
		return new SuccessDataResult<CarResponse>(response, "CAR.GETTED");		
		// SuccessDataResult<CarResponse>(this.carRepository.findById(carResponse.getId()));
	}

	private boolean checkBrandCount(int id) {
		List<Car> cars = carRepository.getByBrandId(id);
		if (cars.size() < 5) {
			return false;
		} else {

			return true;
		}
	}

}
