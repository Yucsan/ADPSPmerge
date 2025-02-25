package com.ejemplos.excepciones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//al lanzar la excepción será un código 404 el que reciba el cliente 
//y no el 500 como sería en un principio
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CuentaNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public CuentaNotFoundException(Integer id) {
		super("No se puede encontrar la cuenta con la ID: " + id);
	}
	
	
}
