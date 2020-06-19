package br.com.zup.recruiting.ramonfacchinzip.business.service.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.zup.recruiting.ramonfacchinzip.business.service.AddressService;
import br.com.zup.recruiting.ramonfacchinzip.error.AddressConflictException;
import br.com.zup.recruiting.ramonfacchinzip.error.AddressNotFoundException;
import br.com.zup.recruiting.ramonfacchinzip.error.DatabaseValidationException;
import br.com.zup.recruiting.ramonfacchinzip.model.Address;

@SpringBootTest
public class AddressServiceTests {
	
	@Autowired
	AddressService service;
	
	@Test
	void testSimple() throws Exception {
		Address address = Address.builder()
				.address("Rua Presidente Gama Rosa")
				.zip("88036-260")
				.city("Florianópolis")
				.state("SC")
				.district("Trindade")
				.build();
		
		// test: create
		address = service.save(address);
		assertNotNull(address.getUuid());
		
		// test: update
		String uuid = address.getUuid();
		address.setCity("FLORIANOPOLIS");
		service.save(address);
		
		// test: retrieve
		address = service.findOne(uuid);
		assertEquals("FLORIANOPOLIS", address.getCity());
		
		// test: delete
		address = service.delete(address);
		assertThrows(AddressNotFoundException.class, () -> {
			service.findOne(uuid);
		});
	}
	
	@Test
	void testConstraintViolations() throws Exception {
		Address address = Address.builder()
				.address("Rua Presidente Gama Rosa")
				.zip("88036260")
				.city("Florianópolis")
				.state("SC")
				.district("Trindade")
				.build();
		
		// test: invalid zip code
		assertThrows(DatabaseValidationException.class, () -> {
			service.save(address);
		});
		address.setZip("88036-260");
		
		// test: null address
		address.setAddress(null);
		assertThrows(DatabaseValidationException.class, () -> {
			service.save(address);
		});
		address.setAddress("Rua Presidente Gama Rosa");
		
		// test: null city
		address.setCity(null);
		assertThrows(DatabaseValidationException.class, () -> {
			service.save(address);
		});
		address.setCity("Florianópolis");
		
		// test: null state
		address.setState(null);
		assertThrows(DatabaseValidationException.class, () -> {
			service.save(address);
		});
		address.setState("SC");
		
	}
	
	@Test
	void testDuplicateAddressInsertion() throws Exception {
		Address address = Address.builder()
				.address("Rua Lauro Linhares")
				.zip("88036-000")
				.city("Florianópolis")
				.state("SC")
				.district("Trindade")
				.build();
		
		// test: create
		address = service.save(address);
		assertNotNull(address.getUuid());
		
		Address address2 = Address.builder()
				.address("Rua Lauro Linhares")
				.zip("88036-000")
				.city("Florianópolis")
				.state("SC")
				.district("Trindade")
				.build();
		
		// test: non-unique zip code
		assertThrows(AddressConflictException.class, () -> {
			service.save(address2);
		});
	}

}
