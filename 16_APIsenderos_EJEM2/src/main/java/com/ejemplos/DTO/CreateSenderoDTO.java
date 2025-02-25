package com.ejemplos.DTO;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class CreateSenderoDTO {
	
	private String cod_sendero;
	private String nombre;
	private String dificultad;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate fecha;
	private String municipioCod_municipio;
	
}