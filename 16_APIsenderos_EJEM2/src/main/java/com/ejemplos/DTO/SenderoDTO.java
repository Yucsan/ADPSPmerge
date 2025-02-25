package com.ejemplos.DTO;

import java.time.LocalDate;

import com.ejemplos.modelo.Municipio;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SenderoDTO {
	
	private String cod_sendero;
	private String nombre;
	private String distancia;
	private String dificultad;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate fecha;
	private Municipio municipio;
	
}