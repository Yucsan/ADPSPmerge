package com.ejemplos.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateSenderoDTO {
	
	private String id;
	private String nombre;
	private Integer distancia;
	private String dificultad;
	private String municipioIdmuni;

}
