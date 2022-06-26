package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.corporateCustomers.CreateCorporateCustomerRequest;
import com.kodlamaio.rentACar.business.requests.corporateCustomers.DeleteCorporateCustomerRequest;
import com.kodlamaio.rentACar.business.requests.corporateCustomers.UpdateCorporateCustomerRequest;
import com.kodlamaio.rentACar.business.responses.corporateCustomers.CorporateCustomerResponse;
import com.kodlamaio.rentACar.business.responses.corporateCustomers.ListCorporeteCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface CorporateCustomerService {
	Result add(CreateCorporateCustomerRequest corporateCustomerRequest);

	Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest);

	Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest);

	DataResult<List<ListCorporeteCustomerResponse>> getAll();

	DataResult<CorporateCustomerResponse> getById(int id);

}
