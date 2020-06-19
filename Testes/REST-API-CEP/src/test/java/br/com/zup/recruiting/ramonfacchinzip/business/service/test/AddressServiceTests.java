package br.com.zup.recruiting.ramonfacchinzip.business.service.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import br.com.zup.recruiting.ramonfacchinzip.business.service.AddressService;
import br.com.zup.recruiting.ramonfacchinzip.model.Address;

@SpringBootTest
public class AddressServiceTests {
	
	@Autowired
	AddressService service;
	
	@Test
	void testSimple() throws Exception {
		Address address = Address.builder()
				.address("Rua Presidente Gama Rosa")
				.zip("88036260")
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
		assertThrows(ResponseStatusException.class, () -> {
			service.findOne(uuid);
		});
	}
	
	@Test
	void testInvalidZipCode() throws Exception {
		Address address = Address.builder()
				.address("Rua Presidente Gama Rosa")
				.zip("88036260abc")
				.city("Florianópolis")
				.state("SC")
				.district("Trindade")
				.build();
		
		// test: invalid zip code
		assertThrows(ResponseStatusException.class, () -> {
			service.save(address);
		});
		
		// test: null address
		address.setZip("88036260");
		address.setAddress(null);
		assertThrows(ResponseStatusException.class, () -> {
			service.save(address);
		});
		
	}

}
