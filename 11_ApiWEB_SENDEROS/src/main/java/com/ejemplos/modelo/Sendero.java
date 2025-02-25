package com.ejemplos.modelo;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "senderosmedios")
public class Sendero {
	
	@Id
	@Column(name = "ID", length = 20)
	private String id;
	
	@Column(name = "NOMBRE", length = 100)
	private String nombre;
	
	@Column(name = "DISTANCIA")
	private Integer distancia;
	
	@Column(name = "DIFICULTAD", length = 30)
	private String dificultad;
	
	@Column(name = "MUNICIPIO", length = 200)
	private String municipioNombre;
		
}
