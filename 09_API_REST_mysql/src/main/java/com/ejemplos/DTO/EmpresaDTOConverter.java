package com.ejemplos.DTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ejemplos.modelo.ProgramadorRepositorio;
import com.ejemplos.modelo.Proyecto;
import com.ejemplos.modelo.ProyectoRepositorio;
import com.ejemplos.modelo.Programador;
import com.ejemplos.modelo.Empresa;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmpresaDTOConverter {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private ProyectoRepositorio proyectoRepositorio;
	
	@Autowired
	private ProgramadorRepositorio programadorRepositorio;
	

	public EmpresaDTO convertirADTO(Empresa empresa) {
		return modelMapper.map(empresa,  EmpresaDTO.class);
	}
	
	public Empresa convertirAEmpre(CreateEmpresaDTO createEmpresaDto) {
		return modelMapper.map(createEmpresaDto, Empresa.class);
	}
	
	/*
	public Empresa convertirAEmpre(CreateEmpresaDTO createEmpresaDTO) {

	    Empresa empresa = modelMapper.map(createEmpresaDTO, Empresa.class);
	    
	    // Proceso para relacionar Progyecto a Empresa
	    Proyecto proyecto = proyectoRepositorio.findById(createEmpresaDTO.getProyectoIdproye()).orElse(null);
	    
	    if (proyecto != null)
	        empresa.setProyecto(proyecto);
	    else 
	        System.out.println("Proyecto no encontrado con id: " + createEmpresaDTO.getProyectoIdproye());
	    
	    // Proceso para relacionar Programador a Empresa
	    Programador programador = programadorRepositorio.findById(createEmpresaDTO.getProgramadorIdpro()).orElse(null);
	    
	    if (programador != null) 
	        empresa.setProgramador(programador);
	    else 
	        System.out.println("Programador no encontrado con id: " + createEmpresaDTO.getProgramadorIdpro());
	    
	    return empresa;
	}
*/
	
	
	/*
	public Empresa convertirAEmpre(CreateEmpresaDTO createEmpresaDTO) {
	    // Crear un nuevo empresa usando ModelMapper
	    Empresa empresa = modelMapper.map(createEmpresaDTO, Empresa.class);

	    // Cargar la categoría desde la base de datos
	    Proyecto proyecto = proyectoRepositorio.findById(createEmpresaDTO.getProyectoIdproye())
	            .orElseThrow( () -> new RuntimeException("Proyecto no encontrado con id: " + createEmpresaDTO.getProyectoIdproye()) );
	    empresa.setProyecto(proyecto); 

	    // Cargar el material desde la base de datos
	    Programador programador = programadorRepositorio.findById(createEmpresaDTO.getProgramadorIdpro())
	            .orElseThrow( () -> new RuntimeException("Programador no encontrado con id: " + createEmpresaDTO.getProgramadorIdpro()));
	    empresa.setProgramador(programador);
	    
	    return empresa;
	}
	*/
	
}
