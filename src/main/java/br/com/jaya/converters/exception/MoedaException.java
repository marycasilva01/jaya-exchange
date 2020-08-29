package br.com.jaya.converters.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class MoedaException extends ResponseStatusException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MoedaException(HttpStatus statusCode, String statusText) {
		super(statusCode, statusText);
	}

}
