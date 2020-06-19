package br.com.zup.recruiting.ramonfacchinzip.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class AddressNotFoundException extends ResponseStatusException {

	public AddressNotFoundException(HttpStatus status, String reason, Throwable cause) {
		super(status, reason, cause);
	}

	public AddressNotFoundException(HttpStatus status, String reason) {
		super(status, reason);
	}

	public AddressNotFoundException(HttpStatus status) {
		super(status);
	}

	private static final long serialVersionUID = 7122033957407932005L;

}
