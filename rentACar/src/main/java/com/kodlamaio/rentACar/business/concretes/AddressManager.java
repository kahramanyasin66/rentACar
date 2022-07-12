package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.business.abstracts.AddressService;
import com.kodlamaio.rentACar.business.abstracts.CorporateCustomerService;
import com.kodlamaio.rentACar.business.abstracts.IndividualCustomerService;
import com.kodlamaio.rentACar.business.requests.addresses.CreateAddressRequest;
import com.kodlamaio.rentACar.business.requests.addresses.DeleteAddressRequest;
import com.kodlamaio.rentACar.business.requests.addresses.UpdateAddressRequest;
import com.kodlamaio.rentACar.business.responses.addresses.AddressResponse;
import com.kodlamaio.rentACar.business.responses.addresses.ListAddressResponse;
import com.kodlamaio.rentACar.core.utilities.exceptions.BusinessException;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.AddressRepository;
import com.kodlamaio.rentACar.dataAccess.abstracts.CustomerRepository;
import com.kodlamaio.rentACar.entities.concretes.Address;

@Service
public class AddressManager implements AddressService {
	private ModelMapperService modelMapperService;
	private AddressRepository addressRepository;
	private CustomerRepository customerRepository;
	private IndividualCustomerService individualCustomerService;
	private CorporateCustomerService corporateCustomerService;

	public AddressManager(ModelMapperService modelMapperService, AddressRepository addressRepository,
			CustomerRepository customerRepository, IndividualCustomerService individualCustomerService,
			CorporateCustomerService corporateCustomerService) {

		this.modelMapperService = modelMapperService;
		this.addressRepository = addressRepository;
		this.customerRepository = customerRepository;
		this.individualCustomerService = individualCustomerService;
		this.corporateCustomerService = corporateCustomerService;
	}

	@Override
	public Result delete(DeleteAddressRequest deleteAddressRequest) {
		Address address = this.modelMapperService.forRequest().map(deleteAddressRequest, Address.class);
		this.addressRepository.delete(address);

		return new SuccessResult("ADDRESS.DELETED");
	}

	@Override
	public DataResult<List<ListAddressResponse>> getAll() {
		List<Address> addresses = this.addressRepository.findAll();
		List<ListAddressResponse> response = addresses.stream()
				.map(address -> this.modelMapperService.forResponse().map(address, ListAddressResponse.class))
				.collect(Collectors.toList());

		return new SuccessDataResult<List<ListAddressResponse>>(response, "ADDRESS.LISTED");
	}

	@Override
	public DataResult<AddressResponse> getById(int id) {
		Address address = this.addressRepository.findById(id);

		AddressResponse response = this.modelMapperService.forResponse().map(address, AddressResponse.class);

		return new SuccessDataResult<AddressResponse>(response, "ADDRESS.GETTED");
	}

	@Override
	public Result addForIndividualCustomer(CreateAddressRequest createAddressRequest) {
		// checkIfIndividualCustomerIdExists(createAddressRequest.getCustomerId());

		Address address = this.modelMapperService.forRequest().map(createAddressRequest, Address.class);
		address.setInvoiceAddress(createAddressRequest.getRealAddress());
		this.addressRepository.save(address);
		return new SuccessResult("ADDRESS.ADDED.FOR.INDIVIDUAL.CUSTOMER");
	}

	@Override
	public Result addForCorporateCustomer(CreateAddressRequest createAddressRequest) {
		// checkIfCorporateCustomerIdExists(createAddressRequest.getCustomerId());

		Address address = this.modelMapperService.forRequest().map(createAddressRequest, Address.class);
		address.setInvoiceAddress(createAddressRequest.getRealAddress());
		this.addressRepository.save(address);
		return new SuccessResult("ADDRESS.ADDED.FOR.CORPORATE.CUSTOMER");
	}

	@Override
	public Result addInvoiceAddressIfDifferentForIndividualCustomer(CreateAddressRequest createAddressRequest) {
		// checkIfIndividualCustomerIdExists(createAddressRequest.getCustomerId());

		Address address = this.modelMapperService.forRequest().map(createAddressRequest, Address.class);
		this.addressRepository.save(address);
		return new SuccessResult("INVOICE.ADDRESS.ADDED.FOR.INDIVIDUAL.CUSTOMER");
	}

	@Override
	public Result addInvoiceAddressIfDifferentForCorporateCustomer(CreateAddressRequest createAddressRequest) {
		// checkIfCorporateCustomerIdExists(createAddressRequest.getCustomerId());

		Address address = this.modelMapperService.forRequest().map(createAddressRequest, Address.class);
		this.addressRepository.save(address);
		return new SuccessResult("INVOICE.ADDRESS.ADDED.FOR.CORPORATE.CUSTOMER");
	}

	@Override
	public Result updateForIndividualCustomer(UpdateAddressRequest updateAddressRequest) {
		checkIfAddressExistsById(updateAddressRequest.getId());
		// checkIfIndividualCustomerIdExists(updateAddressRequest.getCustomerId());

		Address address = this.modelMapperService.forRequest().map(updateAddressRequest, Address.class);
		this.addressRepository.save(address);
		return new SuccessResult("ADDRESS.UPDATED.FOR.INDIVIDUAL.CUSTOMER");
	}

	@Override
	public Result updateForCorporateCustomer(UpdateAddressRequest updateAddressRequest) {
		checkIfAddressExistsById(updateAddressRequest.getId());
		// checkIfCorporateCustomerIdExists(updateAddressRequest.getCustomerId());

		Address address = this.modelMapperService.forRequest().map(updateAddressRequest, Address.class);
		this.addressRepository.save(address);
		return new SuccessResult("ADDRESS.UPDATED.FOR.CORPORATE.CUSTOMER");
	}

	@Override
	public Result updateIfBothAreSameForIndividualCustomer(UpdateAddressRequest updateAddressRequest) {
		checkIfAddressExistsById(updateAddressRequest.getId());
		// checkIfCorporateCustomerIdExists(updateAddressRequest.getCustomerId());

		Address address = this.modelMapperService.forRequest().map(updateAddressRequest, Address.class);
		address.setInvoiceAddress(updateAddressRequest.getRealAddress());
		this.addressRepository.save(address);
		return new SuccessResult("ADDRESS.UPDATED.FOR.INDIVIDUAL.CUSTOMER");
	}

	@Override
	public Result updateIfBothAreSameForCorporateCustomer(UpdateAddressRequest updateAddressRequest) {
		checkIfAddressExistsById(updateAddressRequest.getId());
		// checkIfCorporateCustomerIdExists(updateAddressRequest.getCustomerId());

		Address address = this.modelMapperService.forRequest().map(updateAddressRequest, Address.class);
		address.setInvoiceAddress(updateAddressRequest.getRealAddress());
		this.addressRepository.save(address);
		return new SuccessResult("ADDRESS.UPDATED.FOR.CORPORATE.CUSTOMER");
	}

	private void checkIfAddressExistsById(int id) {
		Address address = this.addressRepository.findById(id);
		if (address == null) {
			throw new BusinessException("THERE.IS.NOT.ADDRESS");
		}
	}

	@Override
	public Address findByAddressId(int id) { // AddressService Kullanan böyle bir adres var mı bilecek.
		checkIfAddressExistsById(id);
		Address address = this.addressRepository.findById(id);
		return address;
	}

}
