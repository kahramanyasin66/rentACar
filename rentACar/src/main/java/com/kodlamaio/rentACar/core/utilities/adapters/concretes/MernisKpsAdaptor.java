package com.kodlamaio.rentACar.core.utilities.adapters.concretes;

import java.rmi.RemoteException;

import com.kodlamaio.rentACar.core.utilities.adapters.abstracts.PersonCheckService;
import com.kodlamaio.rentACar.entities.concretes.User;

public class MernisKpsAdaptor implements PersonCheckService {

	@Override
	public boolean checkPerson(User user) throws NumberFormatException, RemoteException {
		
		return false;
	}

}
