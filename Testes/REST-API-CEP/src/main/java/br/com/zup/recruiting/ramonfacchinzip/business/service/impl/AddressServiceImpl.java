package br.com.zup.recruiting.ramonfacchinzip.business.service.impl;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;

import br.com.zup.recruiting.ramonfacchinzip.business.repository.AddressRepository;
import br.com.zup.recruiting.ramonfacchinzip.business.service.AddressService;
import br.com.zup.recruiting.ramonfacchinzip.error.AddressConflictException;
import br.com.zup.recruiting.ramonfacchinzip.error.AddressNotFoundException;
import br.com.zup.recruiting.ramonfacchinzip.error.DatabaseValidationException;
import br.com.zup.recruiting.ramonfacchinzip.error.InternalServerErrorException;
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
				.orElseThrow(() -> new AddressNotFoundException(HttpStatus.NOT_FOUND, "No address found with given UUID."));
	}

	@Override
	public Address findByZipCode(String zipCode) {
		return repository.findOne(Example.of(Address.builder().zip(zipCode).build()))
				.orElseThrow(() -> new AddressNotFoundException(HttpStatus.NOT_FOUND, "No address found with given ZIP Code."));
	}

	@Override
	public Address save(Address address) {
		try {
			return repository.save(address);
		} catch (TransactionSystemException e) {
			if (e.contains(ConstraintViolationException.class)) {
				log.error("Business rule violated while persisting address: {} - BAD_REQUEST", e.getRootCause().getMessage());
				throw new DatabaseValidationException(HttpStatus.BAD_REQUEST, e.getRootCause().getMessage());
			}
			log.error("Unexpected error while persisting address: {} - INTERNAL_SERVER_ERROR", e.getRootCause().getMessage());
			throw new InternalServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMostSpecificCause().getMessage());
		} catch (DataIntegrityViolationException e) {
			if (e.contains(org.hibernate.exception.ConstraintViolationException.class)) {
				log.error("Business rule violated while persisting address: {} - CONFLICT", e.getRootCause().getMessage());
				throw new AddressConflictException(HttpStatus.CONFLICT, e.getRootCause().getMessage());
			}
			log.error("Unexpected error while persisting address: {} - INTERNAL_SERVER_ERROR", e.getRootCause().getMessage());
			throw new InternalServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMostSpecificCause().getMessage());
		}
	}

	@Override
	public Address delete(Address address) {
		address = findOne(address.getUuid());
		repository.delete(address);
		return address;
	}

}
