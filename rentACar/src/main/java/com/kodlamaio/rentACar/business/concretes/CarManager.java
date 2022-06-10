package com.kodlamaio.rentACar.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.CarService;
import com.kodlamaio.rentACar.business.requests.cars.CreateCarRequest;
import com.kodlamaio.rentACar.business.requests.cars.DeleteCarRequest;
import com.kodlamaio.rentACar.business.requests.cars.UpdateCarRequest;
import com.kodlamaio.rentACar.business.responses.cars.CarResponse;
import com.kodlamaio.rentACar.business.responses.cars.ListCarResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.ErrorResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.CarRepository;
import com.kodlamaio.rentACar.entities.concretes.Brand;
import com.kodlamaio.rentACar.entities.concretes.Car;
import com.kodlamaio.rentACar.entities.concretes.Color;

@Service
public class CarManager implements CarService {
	CarRepository carRepository;

	@Autowired
	public CarManager(CarRepository carRepository) {

		this.carRepository = carRepository;
	}

	@Override
	public Result add(CreateCarRequest createCarRequest) {
		if (!checkBrandCount(createCarRequest.getBrandId())) {

			Car car = new Car();
			car.setDescription(createCarRequest.getDescription()); // cretaCarRequest'in açıklması geliyor
			car.setDailyPrice(createCarRequest.getDailyPrice());

			/* Burası Önemli !!! */

			Brand brand = new Brand();
			brand.setId(createCarRequest.getBrandId());
			car.setBrand(brand);

			Color color = new Color();
			color.setId(createCarRequest.getColorId());
			car.setColor(color);
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
		Car carToUpdate = carRepository.findById(updateCarRequest.getId());

		Brand brand = new Brand();
		brand.setId(updateCarRequest.getBrandId());

		Color color = new Color();
		color.setId(updateCarRequest.getColorId());

		updateCarRequest.setBrandId(brand.getId());
		updateCarRequest.setColorId(color.getId());
		updateCarRequest.setDailyPrice(carToUpdate.getDailyPrice());
		updateCarRequest.setDescription(carToUpdate.getDescription());

		this.carRepository.save(carToUpdate);

		return new SuccessResult("CAR.UPDATED");

	}

	@Override
	public DataResult<List<ListCarResponse>> getAll() {

		return 	null ;
		//new SuccessDataResult<List<ListCarResponse>>(this.carRepository.findAll());
	}

	@Override
	public DataResult<CarResponse> getById(CarResponse carResponse) {

		return null ;
		//new SuccessDataResult<CarResponse>(this.carRepository.findById(carResponse.getId()));
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
