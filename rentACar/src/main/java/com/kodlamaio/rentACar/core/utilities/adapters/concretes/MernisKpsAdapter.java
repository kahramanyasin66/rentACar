package com.kodlamaio.rentACar.core.utilities.adapters.concretes;

import java.rmi.RemoteException;

import org.springframework.stereotype.Service;

import com.kodlamaio.rentACar.core.utilities.adapters.abstracts.PersonCheckService;
import com.kodlamaio.rentACar.entities.concretes.IndividualCustomer;

import tr.gov.nvi.tckimlik.WS.KPSPublicSoapProxy;

@Service
public class MernisKpsAdapter implements PersonCheckService {

	@Override
	public boolean checkPerson(IndividualCustomer individualCustomer) throws NumberFormatException, RemoteException {
		KPSPublicSoapProxy kpsPublicSoapProxy = new KPSPublicSoapProxy();

		boolean result = kpsPublicSoapProxy.TCKimlikNoDogrula(Long.parseLong(individualCustomer.getIdentityNumber()),

				individualCustomer.getFirstName().toUpperCase(), individualCustomer.getLastName().toUpperCase(),
				individualCustomer.getBirthDate().getYear());

		return result;
	}

}
