package com.kodlamaio.rentACar.business.abstracts;

import java.rmi.RemoteException;
import java.util.List;

import com.kodlamaio.rentACar.business.requests.individualCustomers.CreateIndividualCustomerRequest;
import com.kodlamaio.rentACar.business.requests.individualCustomers.DeleteIndividualCustomerRequest;
import com.kodlamaio.rentACar.business.requests.individualCustomers.UpdateIndividualCustomerRequest;
import com.kodlamaio.rentACar.business.responses.individualCustomers.IndividualCustomerResponse;
import com.kodlamaio.rentACar.business.responses.individualCustomers.ListIndividualCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface IndividualCustomerService {
	Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest) throws NumberFormatException, RemoteException;

	Result update(UpdateIndividualCustomerRequest updateIndividualCustomer);

	Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomer);

	DataResult<List<ListIndividualCustomerResponse>> getAll();

	DataResult<IndividualCustomerResponse> getById(int id);

}
