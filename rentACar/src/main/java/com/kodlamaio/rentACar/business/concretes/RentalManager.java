package com.kodlamaio.rentACar.business.concretes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.RentalService;
import com.kodlamaio.rentACar.business.requests.rentals.CreateRentalRequest;
import com.kodlamaio.rentACar.business.requests.rentals.DeleteRentalRequest;
import com.kodlamaio.rentACar.business.requests.rentals.UpdateRentalRequest;
import com.kodlamaio.rentACar.business.responses.rentals.ListRentalResponse;
import com.kodlamaio.rentACar.business.responses.rentals.RentalResponse;
import com.kodlamaio.rentACar.core.utilities.adapters.abstracts.FindexCheckService;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.CarRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.CityRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.RentalRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.UserRepository;
import com.kodlamaio.rentACar.entities.concretes.Car;
import com.kodlamaio.rentACar.entities.concretes.City;
import com.kodlamaio.rentACar.entities.concretes.Rental;
import com.kodlamaio.rentACar.entities.concretes.User;

@Service
public class RentalManager implements RentalService {

	RentalRepository rentalRepository;
	CarRepository carRepository;
	CityRepository cityRepository;
	UserRepository userRepository;
	ModelMapperService modelMapperService;
	FindexCheckService findexCheckService;

	@Autowired
	public RentalManager(RentalRepository rentalRepository, CarRepository carRepository,
			ModelMapperService modelMapperService, CityRepository cityRepository, UserRepository userRepository,
			FindexCheckService findexCheckService) {

		this.rentalRepository = rentalRepository;
		this.carRepository = carRepository;
		this.modelMapperService = modelMapperService;
		this.cityRepository = cityRepository;
		this.userRepository = userRepository;
		this.findexCheckService = findexCheckService;
	}

	@Override
	public Result add(CreateRentalRequest createRentalRequest) {

		Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);
		Car car = this.carRepository.findById(createRentalRequest.getCarId());
		City pickUpCityId = this.cityRepository.findById(createRentalRequest.getPickUpCityId());
		City returnCityId = this.cityRepository.findById(createRentalRequest.getReturnedCityId());
		User userId = this.userRepository.findById(createRentalRequest.getUserId());

		LocalDate pickupDate = (createRentalRequest.getPickupDate());
		LocalDate returnDate = (createRentalRequest.getReturnDate());
		int range = (int) ChronoUnit.DAYS.between(pickupDate, returnDate);

		rental.setTotalDate(range);
		rental.setTotalPrice(range * car.getDailyPrice());
		rental.setPickUpCityId(pickUpCityId);
		rental.setReturnedCityId(returnCityId);
		car.setCarState(3);

		if (!(rental.getPickUpCityId().equals(rental.getReturnedCityId()))) {  // farklı bir şehir için 
			rental.setTotalPrice(rental.getTotalPrice() + 750);
		}
		car.setCity(returnCityId);

		checkFindexMinValue(car.getCarScore(), userId.getTcNo());		//findex puanı için 	

		this.rentalRepository.save(rental);

		return new SuccessResult("RENTAL.ADDED");

	}

	private void checkFindexMinValue(int carScore, String tcNo) {

		if (findexCheckService.CheckFindexScore(tcNo) < carScore) {

			throw new BusinessException("RENTAL.NOT.ADDED.FINDEXPOINT.INSUFFICIENT");
		}
		
	}

	@Override
	public Result update(UpdateRentalRequest updateRentalRequest) {

		Rental rentalToUpdate = this.modelMapperService.forRequest().map(updateRentalRequest, Rental.class);
		Car car = this.carRepository.findById(updateRentalRequest.getCarId());

		LocalDate pickupDate = (updateRentalRequest.getPickupDate());
		LocalDate returnDate = (updateRentalRequest.getReturnDate());
		LocalDate isToday = LocalDate.now();
		int range = (int) ChronoUnit.DAYS.between(pickupDate, returnDate);
		rentalToUpdate.setTotalDate(range);
		rentalToUpdate.setTotalPrice(range * car.getDailyPrice());

		if (isToday.equals(rentalToUpdate.getReturnDate())) {
			car.setCarState(1);
		}
		if ((rentalToUpdate.getPickUpCityId().equals(rentalToUpdate.getReturnedCityId()))) {
			rentalToUpdate.setTotalPrice(rentalToUpdate.getTotalPrice() - 750);
		}

		this.rentalRepository.save(rentalToUpdate);

		return new SuccessResult("RENTAL.UPDATED");
	}

	@Override
	public Result delete(DeleteRentalRequest deleteRentalRequest) {
		Rental rental = new Rental();
		rental.setId(deleteRentalRequest.getId());
		this.rentalRepository.delete(rental);

		return new SuccessDataResult<Rental>("RENTAL.DELETED" + rental.getId());
	}

	@Override
	public DataResult<List<ListRentalResponse>> getall() {
		List<Rental> rentals = this.rentalRepository.findAll();

		List<ListRentalResponse> response = rentals.stream()
				.map(rental -> this.modelMapperService.forResponse().map(rental, ListRentalResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListRentalResponse>>(response, "RENTALS.GETTED");
		// new SuccessDataResult<List<Rental>>(rentalRepository.findAll());
	}

	@Override
	public DataResult<RentalResponse> getById(int id) {

		Rental rental = this.rentalRepository.findById(id);

		RentalResponse response = this.modelMapperService.forResponse().map(rental, RentalResponse.class);

		return new SuccessDataResult<RentalResponse>(response, "RENTAL.GETTED");
		// new
		// SuccessDataResult<Rental>(rentalRepository.findById(rentalResponse.getId()));
	}

}