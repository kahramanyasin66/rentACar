package com.kodlamaio.rentACar.business.concretes;

import java.util.Comparator;
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
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.CarRepository;
import com.kodlamaio.rentACar.entities.concretes.Brand;
import com.kodlamaio.rentACar.entities.concretes.Car;
import com.kodlamaio.rentACar.entities.concretes.Color;

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

		checkBrandCount(createCarRequest.getBrandId());
		checkIfCarExistsByPlate(createCarRequest.getPlate());
		// Marka brandManager'da check ediliyor.
		// Renk colorManager'da check ediliyor.

		// Car car = this.modelMapperService.forRequest().map(createCarRequest,
		// Car.class);
		Car car = carMappingBuilder(createCarRequest);
		car.setCarState(1);
		this.carRepository.save(car);
		return new SuccessResult("CAR.ADDED");

	}

	@Override
	public Result delete(DeleteCarRequest deleteCarRequest) {
		checkIfCarExistsById(deleteCarRequest.getId());
		Car deleteToCar = this.modelMapperService.forRequest().map(deleteCarRequest, Car.class);
		this.carRepository.delete(deleteToCar);
		return new SuccessResult("CAR.DELETED");
	}

	@Override
	public Result update(UpdateCarRequest updateCarRequest) {
		checkIfCarExistsById(updateCarRequest.getId());
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

		return new SuccessDataResult<List<ListCarResponse>>(response, "CARS.LISTED");

	}

	@Override
	public DataResult<CarResponse> getById(int id) {
		checkIfCarExistsById(id);
		Car car = carRepository.findById(id);
		CarResponse response = this.modelMapperService.forResponse().map(car, CarResponse.class);
		return new SuccessDataResult<CarResponse>(response, "CAR.GETTED");

	}

	private Car carMappingBuilder(CreateCarRequest createCarRequest) {
		return Car.builder().dailyPrice(createCarRequest.getDailyPrice()).kilometer(createCarRequest.getKilometer())
				.plate(createCarRequest.getPlate()).carScore(createCarRequest.getCarScore())
				.brand(brandMappingBuilder(createCarRequest)).color(colorMappingBuilder(createCarRequest))

				.build();
	}

	private Brand brandMappingBuilder(CreateCarRequest createCarRequest) {
		return Brand.builder().id(createCarRequest.getBrandId()).build();

	}

	private Color colorMappingBuilder(CreateCarRequest createCarRequest) {
		return Color.builder().id(createCarRequest.getColorId()).build();
	}

	private void checkBrandCount(int id) {
		List<Car> cars = carRepository.getByBrandId(id);
		if (cars.size() > 5) {
			throw new BusinessException("EXCEED.THE.NUMBER.OF.BRAND");
		}
	}

	private void checkIfCarExistsByPlate(String plate) { // aynı isimde marka varsa hata döndür
		Car car = this.carRepository.findByPlate(plate);
		if (car != null) {
			throw new BusinessException("CAR.PLATE.EXISTS");
		}
	}

	private void checkIfCarExistsById(int id) {
		Car brand = this.carRepository.findById(id);
		if (brand == null) {
			throw new BusinessException("CAR.EXISTS");
		}
	}

	@Override
	public Car findByCarId(int id) {// CarService Kullanan böyle bir araba var mı bilecek.
		checkIfCarExistsById(id);
		Car car = this.carRepository.findById(id);
		return car;
	}

	/*--------------------------------- CAR SINIFINA ÖZEL İŞ KURALLARI ------------------------------------------*/
	@Override // Findeks puanına göre arabaları listeleme

	public DataResult<List<ListCarResponse>> getAllSortedByFindeksNumber() {
		List<Car> cars = this.carRepository.findAll();
		List<ListCarResponse> response = cars.stream()
				.map(car -> this.modelMapperService.forResponse().map(car, ListCarResponse.class))
				.sorted(Comparator.comparing(ListCarResponse::getCarScore).reversed()).collect(Collectors.toList());
		return new SuccessDataResult<List<ListCarResponse>>(response, "CARS.LISTED");
	}

	@Override // Findeks puanına ve kilometresine göre sıralayıp getirir.
	public DataResult<List<ListCarResponse>> getAllSortedByFindeksNumberAndKilometer() {
		List<Car> cars = this.carRepository.findAll();
		List<ListCarResponse> response = cars.stream()
				.map(car -> this.modelMapperService.forResponse().map(car, ListCarResponse.class))
				.sorted(Comparator.comparing(ListCarResponse::getCarScore).thenComparing(ListCarResponse::getKilometer)
						.reversed())
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListCarResponse>>(response, "CARS.LISTED");
	}

	@Override // Araç durumlarına göre listeleme
	public DataResult<List<ListCarResponse>> getByState(int state) {
		List<Car> cars = this.carRepository.findAll();
		List<ListCarResponse> response = cars.stream()
				.map(car -> this.modelMapperService.forResponse().map(car, ListCarResponse.class))
				.filter(car -> car.getCarState() == state).collect(Collectors.toList());

		return new SuccessDataResult<List<ListCarResponse>>(response, "CARS.LISTED");
	}

	@Override // Şehirlere göre araçları listeleme
	public DataResult<List<ListCarResponse>> getByCityName(String cityName) {
		List<Car> cars = this.carRepository.findAll();
		List<ListCarResponse> response =cars.stream()
				.map(car -> this.modelMapperService.forResponse().map(car,ListCarResponse.class))
				.filter(car -> cityName.equals(car.getCityName()))
						.collect(Collectors.toList());				 

		return new SuccessDataResult<List<ListCarResponse>>(response ,"CARS.LISTED");
	}

	@Override // Marka ve Renge Göre Araçları Listeleme
	public DataResult<List<ListCarResponse>> getByBrandAndColor(String brandName, String colorName) {
		List<Car> cars = this.carRepository.findAll();
		List<ListCarResponse> response = cars.stream().map(car-> this.modelMapperService.forResponse().map(car, ListCarResponse.class))
				.filter(car -> brandName.equals(car.getBrandName()) && colorName.equals(car.getColorName()))
						.collect(Collectors.toList());
		return new SuccessDataResult<List<ListCarResponse>>(response,"CARS.LISTED"); 
	}

	@Override // Günlük fiyat sınırlandırması ile listeleme
	public DataResult<List<ListCarResponse>> getDailyPriceGreaterThan(double dailyPrice) {
	  List<Car> cars =  this.carRepository.findAll();
	  List<ListCarResponse> response  = cars.stream()
			  .map(car -> this.modelMapperService.forResponse().map(car, ListCarResponse.class))
			  .filter(car -> car.getDailyPrice() > dailyPrice )
			  .collect(Collectors.toList());
		return new SuccessDataResult<List<ListCarResponse>>(response,"CARS.LISTED");
	}

}
