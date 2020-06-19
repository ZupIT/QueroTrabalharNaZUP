package br.com.zup.recruiting.ramonfacchinzip.business.service;

import br.com.zup.recruiting.ramonfacchinzip.model.Address;

public interface AddressService {
	
	Address findOne(String uuid);
	
	Address findByZipCode(String zipCode);
	
	Address save(Address address);
	
	Address delete(Address address);

}
