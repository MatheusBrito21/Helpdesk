package com.matt.helpdesk.services.exceptions;
/**
 * Excecao para objeto nao encontrado
 * @author matheus.biserra
 *
 */
public class DataIntegrityViolationException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DataIntegrityViolationException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataIntegrityViolationException(String message) {
		super(message);
	}
	
	

}
