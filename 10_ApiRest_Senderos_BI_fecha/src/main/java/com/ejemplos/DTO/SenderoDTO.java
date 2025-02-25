package com.ejemplos.DTO;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SenderoDTO {
	
	private String id;
	private String nombre;
	private Integer distancia;
	private String dificultad;
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "Europe/Paris")
	private Date fecha;
	private String municipioNombre; // atencion a esto
	//nombre entidad+nombreDelAtributo
	
}
