package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.addresses.CreateAddressRequest;
import com.kodlamaio.rentACar.business.requests.addresses.DeleteAddressRequest;
import com.kodlamaio.rentACar.business.requests.addresses.UpdateAddressRequest;
import com.kodlamaio.rentACar.business.responses.addresses.AddressResponse;
import com.kodlamaio.rentACar.business.responses.addresses.ListAddressResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;

public interface AddressService {
	Result add(CreateAddressRequest createAddressRequest);

	Result update(UpdateAddressRequest updateAddressRequest);

	Result delete(DeleteAddressRequest deleteAddressRequest);

	DataResult<List<ListAddressResponse>> getAll();

	DataResult<AddressResponse> getById(int id);
	
	DataResult<List<ListAddressResponse>> getAllInvoiceAddress(int userId, int addressType);
	
	DataResult<List<ListAddressResponse>> getAllRealAddress(int userId, int addressType);

}
