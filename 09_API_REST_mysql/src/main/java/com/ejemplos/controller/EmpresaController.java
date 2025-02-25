package com.ejemplos.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ejemplos.DTO.CreateEmpresaDTO;
import com.ejemplos.DTO.EmpresaDTO;
import com.ejemplos.DTO.EmpresaDTOConverter;
import com.ejemplos.modelo.Empresa;
import com.ejemplos.modelo.EmpresaRepositorio;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor // lombok crea el contructor
public class EmpresaController {
	
	@Autowired 
	private final EmpresaRepositorio empresaRepositorio;  // con autowired ya no 
	
	@Autowired
	private EmpresaDTOConverter empresaDTOConverter;
	
	
	@GetMapping("/empresa")  
	public ResponseEntity<?> obtenerTodos(){
		List<Empresa>result = empresaRepositorio.findAll();
		
		if(result.isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			List<EmpresaDTO> dtoList = result.stream()
			.map(empresaDTOConverter::convertirADTO)
			.collect(Collectors.toList());
			return ResponseEntity.ok(dtoList);
		}
	}

	@GetMapping("/empresa/{id}")
	public ResponseEntity<?> obtenerUno(@PathVariable Long id) {
		Empresa result = empresaRepositorio.findById(id).orElse(null); 
		
		//not found es el 404
		if(result==null)
			return ResponseEntity.notFound().build();
		else {
			return ResponseEntity.ok(empresaDTOConverter.convertirADTO(result));
		}		
	}
	
	@PostMapping("/empresa")
	public  ResponseEntity<?> nuevoProducto(@RequestBody CreateEmpresaDTO nuevo) {
		
		// Imprimir los valores del DTO para verificar
	    System.out.println("Proyecto ID: " + nuevo.getProyectoIdproye());
	    System.out.println("Programador ID: " + nuevo.getProgramadorIdpro());
	    System.out.println("Titulo Empresa: " + nuevo.getTitulo());
	    System.out.println("cif Empresa: " + nuevo.getCif());
		
		Empresa saved = empresaDTOConverter.convertirAEmpre(nuevo);
		return  ResponseEntity.status(HttpStatus.CREATED).body( empresaRepositorio.save(saved)); //201 Created
	}
	
	/*
	 * ejemplo agregar
	{
	    "titulo": "La buena Vida es Programar",
		"cif": "cif:252669",
		"proyectoIdproye": 1,
		"programadorIdpro": 2
	}
	
	--------------------------------------------------------------------------------------------------------------------------
	*/
	
	
	@PutMapping("/empresa/{id}")
	public ResponseEntity<?>  editaProducto(@RequestBody CreateEmpresaDTO editar, @PathVariable Long id ) {
		if(empresaRepositorio.existsById(id)) {
			
			Empresa n = empresaDTOConverter.convertirAEmpre(editar);			
			n.setId(id);
			
			if(editar.getProyectoIdproye() == null)
				n.setProyecto(empresaRepositorio.findById(id).get().getProyecto());
		
			if(editar.getProgramadorIdpro() == null)
				n.setProgramador(empresaRepositorio.findById(id).get().getProgramador());
			
			if(editar.getTitulo() == null)
				n.setTitulo(empresaRepositorio.findById(id).get().getTitulo());
			
			if(editar.getCif() == null) //getPrecio()==0.0)
				n.setCif(empresaRepositorio.findById(id).get().getCif());
			
			return ResponseEntity.ok(empresaRepositorio.save(n)); //ok
		}else {
			return ResponseEntity.notFound().build(); //404 no lo encuentro
		}
	
	}
	/*
	
	{
	    "titulo": "La buena Vida es Programar",
		"cif": "cif:252669",
		"proyectoIdproye": 1,
		"programadorIdpro": 2
	}
	*/
	
	//borrado
	
	@DeleteMapping("/empresa/{id}") 
	public ResponseEntity<?> borrarProducto2(@PathVariable Long id ) {
		if(empresaRepositorio.existsById(id)) {
			empresaRepositorio.deleteById(id);
			return ResponseEntity.noContent().build(); //204
		}else {
			return ResponseEntity.notFound().build();
		}
	}

	
}
