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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplos.DTO.CreateSenderoDTO;
import com.ejemplos.DTO.SenderoDTO;
import com.ejemplos.DTO.SenderoDTOConverter;
import com.ejemplos.modelo.Sendero;
import com.ejemplos.modelo.SenderoRepositorio;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor // lombok crea el contructor
@RequestMapping("/apirestSendero")
public class SenderoController {
	
	@Autowired 
	private final SenderoRepositorio senderoRepositorio;  // con autowired ya no 
	
	@Autowired
	private SenderoDTOConverter senderoDTOConverter;
	
	@GetMapping("/sendero")  
	public ResponseEntity<?> obtenerTodos(){
		List<Sendero>result = senderoRepositorio.findAll();
			
		if(result.isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			List<SenderoDTO> dtoList = result.stream()
			.map(senderoDTOConverter::convertirADTO)
			.collect(Collectors.toList());
			return ResponseEntity.ok(dtoList);
		}
	}
		
	
	//http://localhost:8090/sendero/1 -----------------------------------------------------------------------------
	
	@GetMapping("/sendero/{id}")
	public ResponseEntity<?> obtenerUno(@PathVariable String id) {
		Sendero result = senderoRepositorio.findById(id).orElse(null); 
		
		//not found es el 404
		if(result==null)
			return ResponseEntity.notFound().build();
		else {
			return ResponseEntity.ok(senderoDTOConverter.convertirADTO(result));
		}
				
	}
	
	//en Postman para probarlo url: http://localhost:8090/sendero
	//-----------------------------------------------------------------------------------------------------------------
	
	// 1 Insertamos nuevo sendero @param nuevo   2 @return sendero insertado
	
	@PostMapping("/sendero")
	public  ResponseEntity<?> nuevosendero(@RequestBody CreateSenderoDTO nuevo) {
		
		// Imprimir los valores del DTO para verificar
	    System.out.println("ID: " + nuevo.getId() );
	    System.out.println("nombre: " + nuevo.getNombre() );
	    System.out.println("distancia: " + nuevo.getDistancia() );
	    System.out.println("dificultad: " + nuevo.getDificultad() );
	    System.out.println("getMunicipioIdmuni: " + nuevo.getMunicipioIdmuni() );
				
		Sendero saved = senderoDTOConverter.convertirASendero(nuevo);
		return  ResponseEntity.status(HttpStatus.CREATED).body( senderoRepositorio.save(saved)); //201 Created
	}
	
	
	// MODIFICA sendero | @param editar | @param id | @return  
	@PutMapping("/sendero/{id}")
	public ResponseEntity<?>  editasendero(@RequestBody CreateSenderoDTO editar, @PathVariable String id ) {
		 System.out.println("******* ID: " + editar.getId() );
		 
		if(senderoRepositorio.existsById(id)) {
			Sendero n = senderoDTOConverter.convertirASendero(editar);			
			n.setId(id);
			
			if(editar.getMunicipioIdmuni() == null )
				n.setMunicipio(senderoRepositorio.findById(id).get().getMunicipio());
				
			if(editar.getNombre() == null)
				n.setNombre(senderoRepositorio.findById(id).get().getNombre() );
			
			if(editar.getDistancia() == null)
				n.setDificultad(senderoRepositorio.findById(id).get().getDificultad());
			
			if(editar.getDificultad() == null)
				n.setDificultad(senderoRepositorio.findById(id).get().getDificultad());
			
			
			return ResponseEntity.ok(senderoRepositorio.save(n)); //ok
		}else {
			return ResponseEntity.notFound().build(); //404 no lo encuentro
		}
	
	}
	
	//para probarlo en Postman http://localhost:8090/sendero/1 --------------------------------------------------------------
	//Borrar
		
	@DeleteMapping("/sendero/{id}") // da error porque tiene la misma URL
	public ResponseEntity<?> borrarsendero2(@PathVariable String id ) {
		if(senderoRepositorio.existsById(id)) {
			senderoRepositorio.deleteById(id);
			return ResponseEntity.noContent().build(); //204
		}else {
			return ResponseEntity.notFound().build();
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
