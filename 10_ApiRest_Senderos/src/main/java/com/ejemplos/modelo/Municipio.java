package com.ejemplos.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class Municipio {

	
	@Id
	@Column(name = "cod_municipio")
	private String idmuni;
	@Column(name = "nombre_municipio")
	private String nombre;
	
	
}
