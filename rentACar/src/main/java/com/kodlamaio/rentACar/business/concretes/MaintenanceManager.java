package com.kodlamaio.rentACar.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.CarService;
import com.kodlamaio.rentACar.business.abstracts.MaintenanceService;
import com.kodlamaio.rentACar.business.requests.maintenances.CreateMaintenanceRequest;
import com.kodlamaio.rentACar.business.requests.maintenances.DeleteMaintenanceRequest;
import com.kodlamaio.rentACar.business.requests.maintenances.UpdateMaintenanceRequest;
import com.kodlamaio.rentACar.business.responses.maintenances.ListMaintenanceResponse;
import com.kodlamaio.rentACar.business.responses.maintenances.MaintenanceResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.MaintenanceRepository;
import com.kodlamaio.rentACar.entities.concretes.Car;
import com.kodlamaio.rentACar.entities.concretes.Maintenance;

@Service
public class MaintenanceManager implements MaintenanceService {

	MaintenanceRepository maintenanceRepository;
	CarService carService;
	ModelMapperService modelMapperService;

	@Autowired
	public MaintenanceManager(MaintenanceRepository maintenanceRepository, ModelMapperService modelMapperService,
			CarService carService) {

		this.maintenanceRepository = maintenanceRepository;
		this.modelMapperService = modelMapperService;
		this.carService = carService;
	}

	@Override
	public Result add(CreateMaintenanceRequest createMaintenanceRequest) {

		Maintenance maintenance = this.modelMapperService.forRequest().map(createMaintenanceRequest, Maintenance.class);
		Car car = this.carService.findByCarId(createMaintenanceRequest.getCarId());
		car.setCarState(2);
		this.maintenanceRepository.save(maintenance);

		return new SuccessResult("MAINTENANCE.SAVED");
	}

	@Override
	public Result delete(DeleteMaintenanceRequest deleteMaintenanceRequest) {
		checkIfMaintenanceExistsById(deleteMaintenanceRequest.getId());

		Maintenance deleteToMaintenance = this.modelMapperService.forRequest().map(deleteMaintenanceRequest,
				Maintenance.class);
		this.maintenanceRepository.delete(deleteToMaintenance);
		return new SuccessResult("MAINTENANCE.DELETED");

	}

	@Override
	public Result update(UpdateMaintenanceRequest updateMaintenanceRequest) {
		checkIfMaintenanceExistsById(updateMaintenanceRequest.getId());

		Maintenance maintenanceToUpdate = this.modelMapperService.forRequest().map(updateMaintenanceRequest,
				Maintenance.class);
		Car car = this.carService.findByCarId(updateMaintenanceRequest.getCarId());
		LocalDate itsToday = LocalDate.now();
		if (itsToday.equals(updateMaintenanceRequest.getDateReturned())) {
			car.setCarState(2);
		}
		this.maintenanceRepository.save(maintenanceToUpdate);

		return new SuccessResult("MAINTENANCE.UPDATED");
	}

	@Override
	public DataResult<List<ListMaintenanceResponse>> getAll() {
		List<Maintenance> maintenances = this.maintenanceRepository.findAll();
		List<ListMaintenanceResponse> response = maintenances.stream().map(
				maintenance -> this.modelMapperService.forResponse().map(maintenances, ListMaintenanceResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListMaintenanceResponse>>(response, "MAINTENANCES.GETTED");

	}

	@Override
	public DataResult<MaintenanceResponse> getById(int id) {
		checkIfMaintenanceExistsById(id);
		Maintenance maintenance = this.maintenanceRepository.findById(id);
		MaintenanceResponse response = this.modelMapperService.forResponse().map(maintenance,
				MaintenanceResponse.class);

		return new SuccessDataResult<MaintenanceResponse>(response, "MAINTENANCE.GETTED");

	}

	private void checkIfMaintenanceExistsById(int id) {
		Maintenance maintenance = this.maintenanceRepository.findById(id);
		if (maintenance == null) {
			throw new BusinessException("MAINTENANCE.EXISTS");
		}
	}

	@Override
	public Maintenance findByMaintenanceId(int id) {
		checkIfMaintenanceExistsById(id);
		Maintenance maintenance = this.maintenanceRepository.findById(id);
		return maintenance;
	}

}