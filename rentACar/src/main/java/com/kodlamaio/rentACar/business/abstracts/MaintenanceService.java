package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.maintenances.CreateMaintenanceRequest;
import com.kodlamaio.rentACar.business.requests.maintenances.DeleteMaintenanceRequest;
import com.kodlamaio.rentACar.business.requests.maintenances.UpdateMaintenanceRequest;
import com.kodlamaio.rentACar.business.responses.maintenances.ListMaintenanceResponse;
import com.kodlamaio.rentACar.business.responses.maintenances.MaintenanceResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface MaintenanceService  {
	Result add(CreateMaintenanceRequest createMaintenanceRequest );

	Result delete(DeleteMaintenanceRequest deleteMaintenanceRequest);

	Result update(UpdateMaintenanceRequest updateMaintenanceRequest);

    DataResult<List<ListMaintenanceResponse>> getAll();
	
	DataResult<MaintenanceResponse> getById(int id);
}