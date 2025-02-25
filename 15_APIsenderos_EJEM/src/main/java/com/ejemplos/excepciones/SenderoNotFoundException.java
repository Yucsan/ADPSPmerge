package com.ejemplos.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class SenderoNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public SenderoNotFoundException(String id) {
		super("No se puede encontrar el sendero con el código: " + id);
	}
}
