package com.ejemplos.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class Proyecto {

	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idproye")
	private Long idproye;
	private String nombre;
	private String tecnologia;
	private String pais;
}
