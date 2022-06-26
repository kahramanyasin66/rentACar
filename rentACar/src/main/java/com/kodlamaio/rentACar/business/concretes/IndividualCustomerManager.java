package com.kodlamaio.rentACar.business.concretes;

import java.rmi.RemoteException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.IndividualCustomerService;
import com.kodlamaio.rentACar.business.requests.individualCustomers.CreateIndividualCustomerRequest;
import com.kodlamaio.rentACar.business.requests.individualCustomers.DeleteIndividualCustomerRequest;
import com.kodlamaio.rentACar.business.requests.individualCustomers.UpdateIndividualCustomerRequest;
import com.kodlamaio.rentACar.business.responses.individualCustomers.IndividualCustomerResponse;
import com.kodlamaio.rentACar.business.responses.individualCustomers.ListIndividualCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.adapters.abstracts.PersonCheckService;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.IndividualCustomerRepository;
import com.kodlamaio.rentACar.entities.concretes.IndividualCustomer;

@Service
public class IndividualCustomerManager implements IndividualCustomerService {
	private IndividualCustomerRepository individualCustomerRepository;
	private ModelMapperService modelMapperService;
	PersonCheckService personCheckService;


	@Autowired
	public IndividualCustomerManager(IndividualCustomerRepository individualCustomerRepository,
			ModelMapperService modelMapperService,PersonCheckService personCheckService) {
		this.individualCustomerRepository = individualCustomerRepository;
		this.modelMapperService = modelMapperService;
		this.personCheckService = personCheckService;
	}

	@Override
	public Result add(CreateIndividualCustomerRequest createIndividualCustomerRequest)
			throws NumberFormatException, RemoteException {
		
		IndividualCustomer individualCustomer = this.modelMapperService.forRequest()
				.map(createIndividualCustomerRequest, IndividualCustomer.class);
		checkUserExistsByNationality(individualCustomer);		
		this.individualCustomerRepository.save(individualCustomer);		

		return new SuccessResult("INDIVIDUAL.CUSTOMER.ADDED");
	}

	@Override
	public Result update(UpdateIndividualCustomerRequest updateIndividualCustomerRequest) {
		IndividualCustomer updateToIndividualCustomer = this.modelMapperService.forRequest()				
				.map(updateIndividualCustomerRequest, IndividualCustomer.class);		
		
		this.individualCustomerRepository.save(updateToIndividualCustomer);		

		return new SuccessResult("INDIVIDUAL.CUSTOMER.UPDATED");
	}

	@Override
	public Result delete(DeleteIndividualCustomerRequest deleteIndividualCustomerRequest) {
		IndividualCustomer deleteToIndividualCustomer = this.modelMapperService.forRequest()
				.map(deleteIndividualCustomerRequest, IndividualCustomer.class);
		this.individualCustomerRepository.delete(deleteToIndividualCustomer);

		return new SuccessResult("INDIVIDUAL.CUSTOMER.DELETED");
	}

	@Override
	public DataResult<List<ListIndividualCustomerResponse>> getAll() {
		List<IndividualCustomer> individualCustomers = this.individualCustomerRepository.findAll();
		
		List<ListIndividualCustomerResponse> response = individualCustomers.stream()				
				.map(individualCustomer -> this.modelMapperService.forRequest()						
						.map(individualCustomers,ListIndividualCustomerResponse.class))				
						.collect(Collectors.toList());

		return new SuccessDataResult<List<ListIndividualCustomerResponse>>(response+"INDIVIDUAL.CUSTOMERS.LISTED");
	}

	@Override
	public DataResult<IndividualCustomerResponse> getById(int id) {
		
		IndividualCustomer individualCustomer = this.individualCustomerRepository.findById(id);
		IndividualCustomerResponse response = this.modelMapperService.forResponse()
				.map(individualCustomer, IndividualCustomerResponse.class);				

		return new SuccessDataResult<IndividualCustomerResponse>(response+"INDIVIDUAL.CUSTOMER.GETTED");
	}
	
	private void checkUserExistsByNationality(IndividualCustomer individualCustomer)
			throws NumberFormatException, RemoteException {
         //TC Kimlik Numarasının Doğruluğunu Kontrol Ediyoruz
		if (!personCheckService.checkPerson(individualCustomer)) {

			throw new BusinessException("USER.WASN'T.ADDED");
		}
	}

}
