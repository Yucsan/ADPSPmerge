package com.ejemplos.modelo;

import jakarta.persistence.Column;
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
	
	@Id 
	@Column(name = "cod_sendero")
	private String id;
	private String nombre;
	private Integer distancia;
	private String dificultad;
	
	@ManyToOne
	@JoinColumn(name = "cod_municipio")
	private Municipio municipio;
	
}
