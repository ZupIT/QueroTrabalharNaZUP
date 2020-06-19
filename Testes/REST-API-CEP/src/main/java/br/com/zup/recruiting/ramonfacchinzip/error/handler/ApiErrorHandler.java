package br.com.zup.recruiting.ramonfacchinzip.error.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.zup.recruiting.ramonfacchinzip.error.AddressConflictException;
import br.com.zup.recruiting.ramonfacchinzip.error.AddressNotFoundException;
import br.com.zup.recruiting.ramonfacchinzip.error.DatabaseValidationException;
import br.com.zup.recruiting.ramonfacchinzip.model.dto.ApiResponse;
import br.com.zup.recruiting.ramonfacchinzip.util.Constants;

@ControllerAdvice
public class ApiErrorHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler({AddressConflictException.class, DatabaseValidationException.class, AddressNotFoundException.class})
	protected ResponseEntity<Object> handleAddressErrors(RuntimeException ex, WebRequest request) {
		ResponseStatusException responseStatusException = (ResponseStatusException) ex;
		ApiResponse apiResponse = ApiResponse.builder()
			.status(Constants.ResponseStatus.FAILURE)
			.message(ex instanceof DatabaseValidationException ? "Endereço inválido." : "O endereço informado já foi cadastrado.")
			.build();
		if (ex instanceof DatabaseValidationException) {
			apiResponse.setMessage("Endereço inválido.");
		} 
		if (ex instanceof AddressConflictException) {
			apiResponse.setMessage("O endereço informado já foi cadastrado.");
		}
		if (ex instanceof AddressNotFoundException) {
			apiResponse.setMessage("O CEP informado não foi encontrado.");
		}
		return super.handleExceptionInternal(ex, apiResponse, new HttpHeaders(), responseStatusException.getStatus(), request);
	}

}
