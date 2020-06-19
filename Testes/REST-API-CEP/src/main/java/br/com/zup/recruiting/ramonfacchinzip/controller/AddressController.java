package br.com.zup.recruiting.ramonfacchinzip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zup.recruiting.ramonfacchinzip.aspect.annotation.apidoc.ExposeApiDocumentation;
import br.com.zup.recruiting.ramonfacchinzip.business.service.AddressService;
import br.com.zup.recruiting.ramonfacchinzip.model.Address;
import br.com.zup.recruiting.ramonfacchinzip.model.dto.ApiResponse;
import br.com.zup.recruiting.ramonfacchinzip.util.Constants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Endereços")
@ExposeApiDocumentation
@RestController
@RequestMapping("/rest-api")
public class AddressController {
	
	@Autowired
	AddressService service;

	@ApiOperation(value = "Cadastra um endereço.")
	@PostMapping(path = "/cadastro", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponse register(@RequestBody Address address) {
		service.save(address);
		return ApiResponse.builder().status(Constants.ResponseStatus.SUCCESS).message("O endereço foi cadastrado com sucesso.").build();
	}
	
	@ApiOperation(value = "Recupera um endereço a partir do CEP.")
	@GetMapping(path = "/busca/{zip}", produces = MediaType.APPLICATION_JSON_VALUE)
	public Address find(@PathVariable("zip") String zipCode) {
		if (zipCode == null || zipCode.trim().length() < 1) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Um CEP deve ser fornecido.");
		}
		boolean matchesUnformatted = zipCode.matches(Constants.ZIP_CODE_PATTERN_UNFORMATTED);
		boolean matchesFormatted = zipCode.matches(Constants.ZIP_CODE_PATTERN);
		if (matchesFormatted || matchesUnformatted) {
			if (matchesUnformatted) {
				// must format zip code
				zipCode = zipCode.substring(0, 5).concat("-").concat(zipCode.substring(5));
			}
			Address findByZipCode = service.findByZipCode(zipCode);
			findByZipCode.setStatus(Constants.ResponseStatus.SUCCESS);
			return findByZipCode;
		}
		throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CEP Inválido.");
	}

}
