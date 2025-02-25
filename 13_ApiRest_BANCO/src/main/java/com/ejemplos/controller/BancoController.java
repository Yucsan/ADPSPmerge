package com.ejemplos.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplos.DTO.CreateCuentaDTO;
import com.ejemplos.DTO.CuentaDTO;
import com.ejemplos.DTO.CuentaDTOConverter;
import com.ejemplos.excepciones.ApiError;
import com.ejemplos.excepciones.CuentaNotFoundException;
import com.ejemplos.modelo.Cliente;
import com.ejemplos.modelo.ClienteRepositorio;
import com.ejemplos.modelo.Cuenta;
import com.ejemplos.modelo.CuentaRepositorio;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor // lombok crea el contructor
@RequestMapping("/apibanco")
public class BancoController {
	
	
	@Autowired
	private CuentaRepositorio cuentaRepositorio;
	
	@Autowired
	private CuentaDTOConverter cuentaDTOConverter;
	
	@Autowired
	private final ClienteRepositorio clienteRepositorio;
	
	@GetMapping("/clientes")  
	public ResponseEntity<?> obtenerClientes(){
		List<Cliente>result = clienteRepositorio.findAll();
			
		if(result.isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(result);
		}
	}


	@GetMapping("/cuentas")  
	public ResponseEntity<?> obtenerCuentas(){
		List<Cuenta>result = cuentaRepositorio.findAll();
			
		if(result.isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			List<CuentaDTO> dtoList = result.stream() // muestra lo que se desea mostrar
			.map(cuentaDTOConverter::convertirADTO)
			.collect(Collectors.toList());
			return ResponseEntity.ok(dtoList);
		}
	}
	
	@GetMapping("/cuenta/{id}")
	public ResponseEntity<?> obtenerUnaCuenta(@PathVariable Integer id) {
		Cuenta result = cuentaRepositorio.findById(id).orElse(null); 
		
		//not found es el 404
		if(result==null)
			throw new CuentaNotFoundException(id);
			
			
		return ResponseEntity.ok(cuentaDTOConverter.convertirADTO(result));		
	}
	
	
	@PostMapping("/cuenta")
	public  ResponseEntity<?> nuevosendero(@RequestBody CreateCuentaDTO nuevo) {
		
		// Imprimir los valores del DTO para verificar
	    System.out.println("ID: " + nuevo.getCodCuenta() );
	    System.out.println("nombre: " + nuevo.getInteres() );
	    System.out.println("distancia: " + nuevo.getSaldo() );
	    System.out.println("getMunicipioIdmuni: " + nuevo.getClienteCodCliente() );
				
		Cuenta saved = cuentaDTOConverter.convertirASendero(nuevo);
		return  ResponseEntity.status(HttpStatus.CREATED).body( cuentaRepositorio.save(saved)); //201 Created
	}
	
	
	//cuando se produzca un error de este tipo ejecuta este método
	@ExceptionHandler(CuentaNotFoundException.class)
	public ResponseEntity<ApiError> handleProductoNoEncontrado(CuentaNotFoundException ex) {
		ApiError apiError = new ApiError();
		apiError.setEstado(HttpStatus.NOT_FOUND);
		apiError.setFecha(LocalDateTime.now());
		apiError.setMensaje(ex.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
	}
	
	/*
	

	
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

	
	
	
	*/
	
	
	
	
	
	
	
	
	
	
	

}
