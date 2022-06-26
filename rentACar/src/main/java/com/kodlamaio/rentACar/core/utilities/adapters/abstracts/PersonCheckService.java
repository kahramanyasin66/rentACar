package com.kodlamaio.rentACar.core.utilities.adapters.abstracts;

import java.rmi.RemoteException;

import com.kodlamaio.rentACar.entities.concretes.IndividualCustomer;

public interface PersonCheckService {
	boolean checkPerson(IndividualCustomer individualCustomer) throws NumberFormatException, RemoteException;

}
