package com.kodlamaio.rentACar.business.abstracts;

import java.util.List;

import com.kodlamaio.rentACar.business.requests.addresses.CreateAddressRequest;
import com.kodlamaio.rentACar.business.requests.addresses.DeleteAddressRequest;
import com.kodlamaio.rentACar.business.requests.addresses.UpdateAddressRequest;
import com.kodlamaio.rentACar.business.responses.addresses.AddressResponse;
import com.kodlamaio.rentACar.business.responses.addresses.ListAddressResponse;
import com.kodlamaio.rentACar.core.utilities.results.DataResult;
import com.kodlamaio.rentACar.core.utilities.results.Result;
import com.kodlamaio.rentACar.entities.concretes.Address;

public interface AddressService {
	Result addForIndividualCustomer(CreateAddressRequest createAddressRequest);//individual customer için adres ekleme 
	Result addForCorporateCustomer(CreateAddressRequest createAddressRequest); //corporate customer için adres ekleme 
	
	Result addInvoiceAddressIfDifferentForIndividualCustomer(CreateAddressRequest createAddressRequest); //individual customer için fatura adresi ekleme 
	Result addInvoiceAddressIfDifferentForCorporateCustomer(CreateAddressRequest createAddressRequest);  //corporate customer için fatura adresi ekleme
	
	
	Result updateForIndividualCustomer(UpdateAddressRequest updateAddressRequest); //individual customer için adres düzenleme  
	Result updateForCorporateCustomer(UpdateAddressRequest updateAddressRequest); //corporate customer için adres düzenleme
	
	Result updateIfBothAreSameForIndividualCustomer(UpdateAddressRequest updateAddressRequest);//individual customer için fatura adresi düzenleme
	Result updateIfBothAreSameForCorporateCustomer(UpdateAddressRequest updateAddressRequest);//corporate customer için fatura adresi düzenleme
	
	Result delete(DeleteAddressRequest deleteAddressRequest);
	
	
	DataResult<List<ListAddressResponse>> getAll();
	DataResult<AddressResponse> getById(int id);
	
	Address findByAddressId(int id);

}
