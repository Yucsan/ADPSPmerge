package com.ejemplos.modelo;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Data
@Entity
@Table(name = "senderosmedios")
public class Cliente {
	
	@Id
	@Column(name = "cod_cliente")
	private String codCliente;
	
	@Column(name = "apellidos")
	private String apellidos;
	
	@Column(name = "direccion")
	private String direccion;

	@Column(name = "nombre")
	private String nombre;

	
		
}
