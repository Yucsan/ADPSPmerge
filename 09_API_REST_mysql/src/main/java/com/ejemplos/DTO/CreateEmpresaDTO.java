package com.ejemplos.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateEmpresaDTO {
	
	private String titulo;
	private String cif;
	private Long proyectoIdproye;
	private Long programadorIdpro;

}
