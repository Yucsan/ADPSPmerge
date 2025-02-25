package com.ejemplos.modelo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@jakarta.persistence.Entity
public class Municipio {

	@Id
	private String cod_municipio;
	
	private String nombre_municipio;
	
	@OneToMany(mappedBy = "municipio")
	@JsonManagedReference
	private List<Sendero> senderos;
}