package com.kodlamaio.rentACar.business.concretes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.RentalService;
import com.kodlamaio.rentACar.business.requests.rentals.CreateRentalRequest;
import com.kodlamaio.rentACar.business.requests.rentals.DeleteRentalRequest;
import com.kodlamaio.rentACar.business.requests.rentals.UpdateRentalRequest;
import com.kodlamaio.rentACar.business.responses.rentals.ListRentalResponse;
import com.kodlamaio.rentACar.business.responses.rentals.RentalResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.CarRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.RentalRepository;
import com.kodlamaio.rentACar.entities.concretes.Car;
import com.kodlamaio.rentACar.entities.concretes.Rental;

@Service
public class RentalManager implements RentalService {

	RentalRepository rentalRepository;
	CarRepository carRepository;
	ModelMapperService modelMapperService;

	public RentalManager(RentalRepository rentalRepository,CarRepository carRepository,ModelMapperService modelMapperService) {

		this.rentalRepository = rentalRepository;
		this.carRepository = carRepository;
		this.modelMapperService = modelMapperService ;
	}

	@Override
	public Result add(CreateRentalRequest createRentalRequest) {
		Rental rental = new Rental();

		Car car = carRepository.findById(createRentalRequest.getCarId());	

		rental.setPickupDate(createRentalRequest.getPickupDate());
		rental.setReturnDate(createRentalRequest.getReturnDate());
		
		rental.setCar(car);		
		LocalDate pickupDate = (createRentalRequest.getPickupDate());
		LocalDate returnDate = (createRentalRequest.getReturnDate());
		Long range = ChronoUnit.DAYS.between(pickupDate, returnDate);
		
		rental.setTotalDate(range);
		rental.setTotalPrice(range*car.getDailyPrice());
		

		this.rentalRepository.save(rental);

		return new SuccessResult("RENT.ADDED");
	}

	@Override
	public Result update(UpdateRentalRequest updateRentalRequest) {

		Rental rentalToUpdate = rentalRepository.findById(updateRentalRequest.getId());
		Car car = new Car();
		car.setId(updateRentalRequest.getCarId());

		rentalToUpdate.setPickupDate(updateRentalRequest.getPickupDate());
		rentalToUpdate.setReturnDate(updateRentalRequest.getPickupDate());
		rentalToUpdate.setTotalDate(updateRentalRequest.getTotalDate());
		rentalToUpdate.setTotalPrice(updateRentalRequest.getTotalDate() * car.getDailyPrice());

		this.rentalRepository.save(rentalToUpdate);

		return new SuccessResult("RENT.UPDATED");
	}

	@Override
	public Result delete(DeleteRentalRequest deleteRentalRequest) {
		Rental rental = new Rental();
		rental.setId(deleteRentalRequest.getId());
		this.rentalRepository.delete(rental);

		return new SuccessDataResult<Rental>("RENTAL.DELETED" + rental);
	}

	@Override
	public DataResult<List<ListRentalResponse>> getall() {

		return null ;
		//new SuccessDataResult<List<Rental>>(rentalRepository.findAll());
	}

	@Override
	public DataResult<RentalResponse> getById(int id) {

		return null ;
		//new SuccessDataResult<Rental>(rentalRepository.findById(rentalResponse.getId()));
	}

}