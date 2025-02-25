package com.ejemplos.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
@Entity
public class Empresa {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idempre")
	private Long id;
	private String titulo;
	private String cif;
	
	@ManyToOne
	@JoinColumn(name = "idproye_FK")
	private Proyecto proyecto;
	
	@ManyToOne
	@JoinColumn(name = "idpro_FK")
	private Programador programador;
	
}
