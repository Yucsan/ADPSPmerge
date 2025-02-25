package com.ejemplos.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class Usuario {
	
	@Id
	private long id;
	private String nombre;
	private String avatar;
	private String mail;

	
	

}
