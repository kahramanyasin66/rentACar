package com.kodlamaio.rentACar.business.concretes;

import java.util.List;
import java.util.stream.Collectors;

import com.kodlamaio.rentACar.business.abstracts.AddressService;
import com.kodlamaio.rentACar.business.requests.addresses.CreateAddressRequest;
import com.kodlamaio.rentACar.business.requests.addresses.DeleteAddressRequest;
import com.kodlamaio.rentACar.business.requests.addresses.UpdateAddressRequest;
import com.kodlamaio.rentACar.business.responses.addresses.AddressResponse;
import com.kodlamaio.rentACar.business.responses.addresses.ListAddressResponse;
import com.kodlamaio.rentACar.core.utilities.mapping.ModelMapperService;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.core.utilities.results.SuccessDataResult;
import com.kodlamaio.rentACar.core.utilities.results.SuccessResult;
import com.kodlamaio.rentACar.dataAccess.abstracts.AddressRepository;
import com.kodlamaio.rentACar.entities.concretes.Address;

public class AddressManager implements AddressService {
	ModelMapperService modelMapperService;
	AddressRepository addressRepository;

	@Override
	public Result add(CreateAddressRequest createAddressRequest) {
		Address address = this.modelMapperService.forRequest().map(createAddressRequest, Address.class);
		this.addressRepository.save(address);

		return new SuccessResult("ADDRESS.ADDED");
	}

	@Override
	public Result update(UpdateAddressRequest updateAddressRequest) {
		Address addressToUpdate = this.modelMapperService.forRequest().map(updateAddressRequest, Address.class);
		this.addressRepository.save(addressToUpdate);

		return new SuccessResult("ADDRESS.ADDED");

	}

	@Override
	public Result delete(DeleteAddressRequest deleteAddressRequest) {
		Address address = this.modelMapperService.forRequest().map(deleteAddressRequest, Address.class);
		this.addressRepository.save(address);

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

		return null;
	}

}
