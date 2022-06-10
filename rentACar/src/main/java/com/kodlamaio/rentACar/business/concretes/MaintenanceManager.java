package com.kodlamaio.rentACar.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.MaintenanceService;
import com.kodlamaio.rentACar.business.requests.maintenances.CreateMaintenanceRequest;
import com.kodlamaio.rentACar.business.requests.maintenances.DeleteMaintenanceRequest;
import com.kodlamaio.rentACar.business.requests.maintenances.UpdateMaintenanceRequest;
import com.kodlamaio.rentACar.business.responses.maintenances.ListMaintenanceResponse;
import com.kodlamaio.rentACar.business.responses.maintenances.MaintenanceResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.MaintenanceRepository;
import com.kodlamaio.rentACar.entities.concretes.Car;
import com.kodlamaio.rentACar.entities.concretes.Maintenance;

@Service
public class MaintenanceManager implements MaintenanceService {

	MaintenanceRepository maintenanceRepository;

	@Autowired
	public MaintenanceManager(MaintenanceRepository maintenanceRepository) {

		this.maintenanceRepository = maintenanceRepository;
	}

	@Override
	public Result add(CreateMaintenanceRequest createMaintenanceRequest) {

		Maintenance maintenance = new Maintenance();
		maintenance.setDateSent(createMaintenanceRequest.getDateSent());
		maintenance.setDateReturned(createMaintenanceRequest.getDateReturned());
		maintenance.setDescription(createMaintenanceRequest.getDescription());
		Car car = new Car();
		car.setId(createMaintenanceRequest.getCarId());
		car.setCarState(2);
		maintenance.setCar(car);		

		this.maintenanceRepository.save(maintenance);

		return new SuccessResult("BAKIM.KAYDEDİLDİ");
	}

	@Override
	public Result delete(DeleteMaintenanceRequest deleteMaintenanceRequest) {

		int carId = deleteMaintenanceRequest.getId();
		this.maintenanceRepository.deleteById(carId);
		return new SuccessResult("CAR.DELETED");
	}

	@Override
	public Result update(UpdateMaintenanceRequest updateMaintenanceRequest) {
		Maintenance maintenanceToUpdate = maintenanceRepository.findById(updateMaintenanceRequest.getId());

		Car car = new Car();
		car.setId(updateMaintenanceRequest.getCarId());

		maintenanceToUpdate.setDateReturned(updateMaintenanceRequest.getDateReturned());
		maintenanceToUpdate.setDateSent(updateMaintenanceRequest.getDateSent());
		maintenanceToUpdate.setDescription(updateMaintenanceRequest.getDescription());
		maintenanceToUpdate.setCar(car);
		this.maintenanceRepository.save(maintenanceToUpdate);

		return new SuccessResult("MAINTENANCE.UPDATED");
	}

	@Override
	public DataResult<List<ListMaintenanceResponse>> getAll() {

		return null ;
		//new SuccessDataResult<List<MaintenanceResponse>>(maintenanceRepository.findAll());
	}

	@Override
	public DataResult<MaintenanceResponse> getById(int id ) {

		return null ;
		//new SuccessDataResult<MaintenanceResponse>(this.maintenanceRepository.findById(maintenanceResponse.getId()));
	}

}