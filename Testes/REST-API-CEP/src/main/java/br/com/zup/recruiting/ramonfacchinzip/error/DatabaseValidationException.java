package br.com.zup.recruiting.ramonfacchinzip.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DatabaseValidationException extends ResponseStatusException {

	public DatabaseValidationException(HttpStatus status, String reason, Throwable cause) {
		super(status, reason, cause);
	}

	public DatabaseValidationException(HttpStatus status, String reason) {
		super(status, reason);
	}

	public DatabaseValidationException(HttpStatus status) {
		super(status);
	}

	private static final long serialVersionUID = 7122033957407932005L;

}
