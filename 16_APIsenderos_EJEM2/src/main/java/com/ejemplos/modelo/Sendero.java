package com.ejemplos.modelo;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class Sendero {

	//Generated value da fallo porque el id es tipo string.
	@Id
	private String cod_sendero;
	
	private String nombre;
	
	private int distancia;
	
	private String dificultad;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate fecha;
	
	@ManyToOne
	@JoinColumn(name="cod_municipio")
    @JsonBackReference
	private Municipio municipio;
}