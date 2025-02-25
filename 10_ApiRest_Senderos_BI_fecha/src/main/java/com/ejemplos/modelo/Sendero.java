package com.ejemplos.modelo;

import java.util.Date;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@JsonIgnoreProperties({"municipio"})
public class Sendero {
	
	@Id 
	@Column(name = "cod_sendero")
	private String id;
	private String nombre;
	private Integer distancia;
	private String dificultad;
	
	@Temporal( TemporalType.TIMESTAMP)
	private Date fecha;
	
	@ManyToOne
	@JoinColumn(name = "cod_municipio")
	private Municipio municipio;
	
}
