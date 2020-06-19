package br.com.zup.recruiting.ramonfacchinzip.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InternalServerErrorException extends ResponseStatusException {

	public InternalServerErrorException(HttpStatus status, String reason, Throwable cause) {
		super(status, reason, cause);
	}

	public InternalServerErrorException(HttpStatus status, String reason) {
		super(status, reason);
	}

	public InternalServerErrorException(HttpStatus status) {
		super(status);
	}

	private static final long serialVersionUID = 7122033957407932005L;

}
