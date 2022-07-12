package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.CorporateCustomerService;
import com.kodlamaio.rentACar.business.requests.corporateCustomers.CreateCorporateCustomerRequest;
import com.kodlamaio.rentACar.business.requests.corporateCustomers.DeleteCorporateCustomerRequest;
import com.kodlamaio.rentACar.business.requests.corporateCustomers.UpdateCorporateCustomerRequest;
import com.kodlamaio.rentACar.business.responses.corporateCustomers.CorporateCustomerResponse;
import com.kodlamaio.rentACar.business.responses.corporateCustomers.ListCorporeteCustomerResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.CorporateCustomerRepository;
import com.kodlamaio.rentACar.entities.concretes.CorporateCustomer;

@Service
public class CorporateCustomerManager implements CorporateCustomerService {
	ModelMapperService modelMapperService;
	CorporateCustomerRepository corporateCustomerRepository;

	@Autowired
	public CorporateCustomerManager(ModelMapperService modelMapperService,
			CorporateCustomerRepository corporateCustomerRepository) {

		this.modelMapperService = modelMapperService;
		this.corporateCustomerRepository = corporateCustomerRepository;
	}

	@Override
	public Result add(CreateCorporateCustomerRequest corporateCustomerRequest) {
		CorporateCustomer corporateCustomer = this.modelMapperService.forRequest().map(corporateCustomerRequest,
				CorporateCustomer.class);

		this.corporateCustomerRepository.save(corporateCustomer);

		return new SuccessResult("CORPORATE.CUSTOMER.ADDED");
	}

	@Override
	public Result update(UpdateCorporateCustomerRequest updateCorporateCustomerRequest) {
		checkIfCorporateCustomerExistsById(updateCorporateCustomerRequest.getId());
		CorporateCustomer updateToCorporateCustomer = this.modelMapperService.forRequest()
				.map(updateCorporateCustomerRequest, CorporateCustomer.class);
		this.corporateCustomerRepository.save(updateToCorporateCustomer);
		return new SuccessResult("CORPORATE.CUSTOMER.UPDATED");
	}

	@Override
	public Result delete(DeleteCorporateCustomerRequest deleteCorporateCustomerRequest) {
		checkIfCorporateCustomerExistsById(deleteCorporateCustomerRequest.getId());
		CorporateCustomer deleteToCorporateCustomer = this.modelMapperService.forRequest()
				.map(deleteCorporateCustomerRequest, CorporateCustomer.class);
		this.corporateCustomerRepository.delete(deleteToCorporateCustomer);

		return new SuccessResult("CORPORATE.CUSTOMER.DELETED");
	}

	@Override
	public DataResult<List<ListCorporeteCustomerResponse>> getAll() {
		List<CorporateCustomer> corporateCustomers = this.corporateCustomerRepository.findAll();

		List<ListCorporeteCustomerResponse> response = corporateCustomers.stream()
				.map(corporateCustomer -> this.modelMapperService.forResponse().map(corporateCustomer,
						ListCorporeteCustomerResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListCorporeteCustomerResponse>>(response, "CORPORATE.CUSTOMERS.LISTED");
	}

	@Override
	public DataResult<CorporateCustomerResponse> getById(int id) {
		checkIfCorporateCustomerExistsById(id);
		CorporateCustomer corporateCustomerId = this.corporateCustomerRepository.findById(id);
		CorporateCustomerResponse response = this.modelMapperService.forResponse().map(corporateCustomerId,
				CorporateCustomerResponse.class);

		return new SuccessDataResult<CorporateCustomerResponse>(response, "CORPORATE.CUSTOMER.GETTED");
	}

	private void checkIfCorporateCustomerExistsById(int id) {
		CorporateCustomer corporateCustomer = this.corporateCustomerRepository.findById(id);
		if (corporateCustomer == null) {
			throw new BusinessException("CORPORATE.CUSTOMER.EXISTS");
		}
	}

	@Override
	public CorporateCustomer findByCorporateCustomerId(int id) {
		checkIfCorporateCustomerExistsById(id);
		CorporateCustomer corporateCustomer = this.corporateCustomerRepository.findById(id);
		return corporateCustomer;
	}

}
