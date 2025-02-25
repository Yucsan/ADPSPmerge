package com.ejemplos.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SenderoDTO {
	
	private String id;
	private String nombre;
	private Integer distancia;
	private String dificultad;
	private String municipioNombre; // atencion a esto
	//nombre entidad+nombreDelAtributo
	
}
