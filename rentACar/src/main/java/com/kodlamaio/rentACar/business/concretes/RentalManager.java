package com.kodlamaio.rentACar.business.concretes;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.CarService;
import com.kodlamaio.rentACar.business.abstracts.CityService;
import com.kodlamaio.rentACar.business.abstracts.CorporateCustomerService;
import com.kodlamaio.rentACar.business.abstracts.IndividualCustomerService;
import com.kodlamaio.rentACar.business.abstracts.InvoiceService;
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
import com.kodlamaio.rentACar.dataAccess.abstracts.CorporateCustomerRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.IndividualCustomerRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.RentalRepository;
import com.kodlamaio.rentACar.entities.concretes.Car;
import com.kodlamaio.rentACar.entities.concretes.IndividualCustomer;
import com.kodlamaio.rentACar.entities.concretes.Rental;

@Service
public class RentalManager implements RentalService {
    //Repositoryler değil Serviceler üzerinden ilerlemen gerekiyor
	// @Lazy anatosyonu sonsuz döngü olmasın diye 
	
	
	RentalRepository rentalRepository;		
	CarService carService;
	CityService cityService;
	IndividualCustomerService individualCustomerService;
	CorporateCustomerService corporateCustomerService;
	ModelMapperService modelMapperService;
	FindexCheckService findexCheckService;


	@Autowired
	public RentalManager(RentalRepository rentalRepository, CarService carService, CityService cityService,
			IndividualCustomerService individualCustomerService, CorporateCustomerService corporateCustomerService,
			ModelMapperService modelMapperService, FindexCheckService findexCheckService) {
		super();
		this.rentalRepository = rentalRepository;
		this.carService = carService;
		this.cityService = cityService;
		this.individualCustomerService = individualCustomerService;
		this.corporateCustomerService = corporateCustomerService;
		this.modelMapperService = modelMapperService;
		this.findexCheckService = findexCheckService;
	}





	/*----------------------------------------ADD-------------------------------------------------*/

	@Override
	public Result addIndividualCustomer(CreateRentalRequest createRentalRequest) {
		Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);

		Car car = this.carService.findByCarId(createRentalRequest.getCarId());		
//		City pickUpCityId = this.cityRepository.findById(createRentalRequest.getPickUpCityId());
//		City returnCityId = this.cityRepository.findById(createRentalRequest.getReturnedCityId());
		IndividualCustomer individualCustomer = this.individualCustomerService
				.findByIndividualCustomerId(createRentalRequest.getCustomerId());

		LocalDate pickupDate = (createRentalRequest.getPickupDate());
		LocalDate returnDate = (createRentalRequest.getReturnDate());
		int range = (int) ChronoUnit.DAYS.between(pickupDate, returnDate);
		
		

		rental.setTotalDate(range);
		rental.setTotalPrice(range * car.getDailyPrice());
//		rental.setPickUpCityId(pickUpCityId);
//		rental.setReturnedCityId(returnCityId);
//		car.setCity(returnCityId);
		checkIfCarState(createRentalRequest.getCarId());
		checkIfCarExistsById(createRentalRequest.getCarId()); // aynı araba kiralanmış mı
		checkDateToRentACar(pickupDate, returnDate);
		checkFindexMinValue(car.getCarScore(), individualCustomer.getIdentityNumber()); // müşterinin findex puanına
																						// bakıp arabanın puanı ile
																			            // karşılaştırdık

		car.setCarState(3);
		this.rentalRepository.save(rental);

//		invoiceService.add(rental);
		return new SuccessResult("RENTAL.ADDED");

	}
	
	
	
	@Override
	public Result addCorporateCustomer(CreateRentalRequest createRentalRequest) {
		
		Rental rental = this.modelMapperService.forRequest().map(createRentalRequest, Rental.class);

		Car car = this.carService.findByCarId(createRentalRequest.getCarId());		
//		City pickUpCityId = this.cityRepository.findById(createRentalRequest.getPickUpCityId());
//		City returnCityId = this.cityRepository.findById(createRentalRequest.getReturnedCityId());
	
		LocalDate pickupDate = (createRentalRequest.getPickupDate());
		LocalDate returnDate = (createRentalRequest.getReturnDate());
		int range = (int) ChronoUnit.DAYS.between(pickupDate, returnDate);

		rental.setTotalDate(range);
		rental.setTotalPrice(range * car.getDailyPrice());
//		rental.setPickUpCityId(pickUpCityId);
//		rental.setReturnedCityId(returnCityId);
//		car.setCity(returnCityId);
		
		
		checkIfCarState(createRentalRequest.getCarId());
		checkIfCarExistsById(createRentalRequest.getCarId()); // aynı araba kiralanmış mı
		checkDateToRentACar(pickupDate, returnDate);																				// bakıp arabanın puanı ile
																			            // karşılaştırdık

		car.setCarState(3);
		this.rentalRepository.save(rental);

		return new SuccessResult("RENTAL.ADDED");
		
		
	}

	/*------------------------------- RENTAL ADD METHODS --------------------------------*/

	private void checkIfCarState(int id) {                               // Araç bakımda veya Kiradaysa kiralanamaz
		Car car = this.carService.findByCarId(id);
		if (car.getCarState() == 2 || car.getCarState() == 3) {
			throw new BusinessException("CAR.IS.NOT.AVAIBLE");
		}
	}

	private void checkDateToRentACar(LocalDate pickupDate, LocalDate returnDate) { // Tarihleri Yanlış Giremesin
		if (!pickupDate.isBefore(returnDate) || pickupDate.isBefore(LocalDate.now())) {
			throw new BusinessException("PICKUPDATE.AND.RETURNDATE.ERROR");
		}
	}

	private void checkFindexMinValue(int carScore, String identityNumber) { // findex puanına göre araç verme

		if (findexCheckService.CheckFindexScore(identityNumber) < carScore) {

			throw new BusinessException("RENTAL.NOT.ADDED.FINDEXPOINT.INSUFFICIENT");
		}

	}

	private void checkIfCarExistsById(int id) { // kiralamada aynı araba varsa hata döndür
		Car currentCar = this.carService.findByCarId(id);
		if (currentCar != null) {
			throw new BusinessException("CAR.EXISTS");
		}
	}

	private double isDiffReturnCityFromPickUpCity(int pickUpCity, int returnCity) {
		if (pickUpCity != returnCity) {
			return 750.0;
		}
		return 0;
	}

	private double calculateTotalPrice(Rental rental, double dailyPrice) {
		double days = rental.getTotalDate();
		double totalDailyPrice = days * dailyPrice;
		double diffCityPrice = isDiffReturnCityFromPickUpCity(rental.getReturnedCityId().getId(),
				rental.getReturnedCityId().getId());
		double totalPrice = totalDailyPrice + diffCityPrice;

		return totalPrice;
	}

	/*----------------------------------------------UPDATE--------------------------------------------------------*/

	@Override
	public Result updateIndividualCustomer(UpdateRentalRequest updateRentalRequest) {
		checkIfRentalExistsById(updateRentalRequest.getId());
		checkIfCarState(updateRentalRequest.getCarId());
		checkDateToRentACar(updateRentalRequest.getPickupDate(), updateRentalRequest.getReturnDate());

		Rental rental = this.modelMapperService.forRequest().map(updateRentalRequest, Rental.class);

		int diffDate = (int) ChronoUnit.DAYS.between(rental.getPickupDate(), rental.getReturnDate());
		rental.setTotalDate(diffDate);

		Car car = this.carService.findByCarId(updateRentalRequest.getCarId());
		double totalPrice = calculateTotalPrice(rental, car.getDailyPrice());

		//rental.setPickUpCityId(car.getCity());
		rental.setTotalPrice(totalPrice);

		rentalRepository.save(rental);
		return new SuccessResult("RENTAL.UPDATED");
		
	}

	@Override
	public Result updateCorporateCustomer(UpdateRentalRequest updateRentalRequest) {
		checkIfRentalExistsById(updateRentalRequest.getId());
		checkIfCarState(updateRentalRequest.getCarId());
		checkDateToRentACar(updateRentalRequest.getPickupDate(), updateRentalRequest.getReturnDate());

		Rental rental = this.modelMapperService.forRequest().map(updateRentalRequest, Rental.class);

		int diffDate = (int) ChronoUnit.DAYS.between(rental.getPickupDate(), rental.getReturnDate());
		rental.setTotalDate(diffDate);

		Car car = this.carService.findByCarId(updateRentalRequest.getCarId());
		double totalPrice = calculateTotalPrice(rental, car.getDailyPrice());

		//rental.setPickUpCityId(car.getCity());
		rental.setTotalPrice(totalPrice);

		rentalRepository.save(rental);
		return new SuccessResult("RENTAL.UPDATED");
	}


	@Override
	public Result delete(DeleteRentalRequest deleteRentalRequest) {
		checkIfRentalExistsById(deleteRentalRequest.getId());
		Rental rental = this.modelMapperService.forRequest().map(deleteRentalRequest, Rental.class);
		this.rentalRepository.delete(rental);
		return new SuccessDataResult<Rental>("RENTAL.DELETED" + rental.getId());
	}

	@Override
	public DataResult<List<ListRentalResponse>> getall() {
		List<Rental> rentals = this.rentalRepository.findAll();

		List<ListRentalResponse> response = rentals.stream()
				.map(rental -> this.modelMapperService.forResponse().map(rental, ListRentalResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListRentalResponse>>(response, "RENTALS.LISTED");

	}

	@Override
	public DataResult<RentalResponse> getById(int id) {

		Rental rental = this.rentalRepository.findById(id);

		RentalResponse response = this.modelMapperService.forResponse().map(rental, RentalResponse.class);
		return new SuccessDataResult<RentalResponse>(response, "RENTAL.GETTED");
	}

	@Override
	public DataResult<List<ListRentalResponse>> getAll(Integer pageNo, Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
		
		List<Rental> rentals = this.rentalRepository.findAll(pageable).getContent();		
		List<ListRentalResponse> response = rentals.stream()
				.map(user -> this.modelMapperService.forResponse().map(user, ListRentalResponse.class))
				.collect(Collectors.toList());		
		
		return new SuccessDataResult<List<ListRentalResponse>>(response+"RENTAL.PAGED");
	}
	
    private void checkIfRentalExistsById(int id) {
    	Rental rental = this.rentalRepository.findById(id);
    	if(rental == null ) {
    		throw new BusinessException("RENTAL.EXIST");
    	}
    	
    }

	@Override
	public Rental findByRentalId(int id) {
		checkIfRentalExistsById(id);
		Rental rental =this.rentalRepository.findById(id);
		return rental;
	}

	

}