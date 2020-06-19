package br.com.zup.recruiting.ramonfacchinzip.business.service.impl;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.server.ResponseStatusException;

import br.com.zup.recruiting.ramonfacchinzip.business.repository.AddressRepository;
import br.com.zup.recruiting.ramonfacchinzip.business.service.AddressService;
import br.com.zup.recruiting.ramonfacchinzip.model.Address;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressRepository repository;
	
	@Override
	public Address findOne(String uuid) {
		return repository.findById(uuid)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No address found with given UUID."));
	}

	@Override
	public Address findByZipCode(String zipCode) {
		return repository.findOne(Example.of(Address.builder().zip(zipCode).build()))
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No address found with given ZIP Code."));
	}

	@Override
	public Address save(Address address) {
		try {
			return repository.save(address);
		} catch (TransactionSystemException e) {
			if (e.contains(ConstraintViolationException.class)) {
				log.error("Business rule violated while persisting address: {}", e.getRootCause().getMessage());
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getRootCause().getMessage());
			}
			log.error("Unexpected error while persisting address: {}", e.getRootCause().getMessage());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMostSpecificCause().getMessage());
		}
	}

	@Override
	public Address delete(Address address) {
		address = findOne(address.getUuid());
		repository.delete(address);
		return address;
	}

}
