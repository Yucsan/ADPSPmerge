package com.ejemplos.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ejemplos.DTO.SenderoDTO;
import com.ejemplos.DTO.SenderoDTOConverter;
import com.ejemplos.excepciones.ApiError;
import com.ejemplos.excepciones.SenderoNotFoundException;
import com.ejemplos.modelo.Sendero;
import com.ejemplos.modelo.SenderoRepositorio;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.ejemplos.DTO.CreateSenderoDTO;


import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor  
public class SenderoController {
	@Autowired
	private   SenderoRepositorio senderoRepositorio; 
	
	@Autowired
	private SenderoDTOConverter senderoDTOConverter;
	
	/********************************************************************************************/
	
	@GetMapping("/sendero")
	public ResponseEntity<?> obtenerTodos() {
		List<Sendero> result = senderoRepositorio.findAll();

		if (result.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			List<SenderoDTO> dtoList = result.stream().map(senderoDTOConverter::convertirADto)
					.collect(Collectors.toList());
			return ResponseEntity.ok(dtoList);
		}
	}

	/********************************************************************************************/
	@GetMapping("/sendero/{id}")
	public ResponseEntity<?> obtenerUno(@PathVariable String id) {
			Sendero result = senderoRepositorio.findById(id).orElse(null);
			if (result == null)
				throw new SenderoNotFoundException(id);
			return ResponseEntity.ok(result);
	}

	/********************************************************************************************/
	
	@PostMapping("/sendero")
	public ResponseEntity<?> nuevoSendero(@RequestBody CreateSenderoDTO nuevo) {
		Sendero n= senderoDTOConverter.convertirASend(nuevo);
		return ResponseEntity.status(HttpStatus.CREATED).body(senderoRepositorio.save(n));
	}
	
	/********************************************************************************************/
	
	@PutMapping("/sendero/{id}")
	public ResponseEntity<?> editarSendero(@RequestBody CreateSenderoDTO editar, @PathVariable String id) {
	
		if (!senderoRepositorio.existsById(id)) throw new SenderoNotFoundException(id);
	
		Sendero n= senderoDTOConverter.convertirASend(editar);
		n.setCod_sendero(id);
		//si en el objeto editar no mandamos ciertos campos, debemos mantener lo que el objeto tiene
		//guardado en la BD
		if (editar.getMunicipioCod_municipio()==null)
			n.setMunicipio(senderoRepositorio.findById(id).get().getMunicipio());
		if (editar.getNombre()==null)
			n.setNombre(senderoRepositorio.findById(id).get().getNombre());
		if (editar.getDificultad()==null)
			n.setDificultad(senderoRepositorio.findById(id).get().getDificultad());
		if (editar.getFecha()==null)
			n.setFecha(senderoRepositorio.findById(id).get().getFecha());
		return ResponseEntity.ok(senderoRepositorio.save(n));
	}
	
	/********************************************************************************************/
	
	@DeleteMapping("/sendero/{id}")
	public ResponseEntity<?> borrarSendero(@PathVariable String id) {
		Sendero result = senderoRepositorio.findById(id).orElse(null);
		if (result == null)
			 throw new SenderoNotFoundException(id);
		
		senderoRepositorio.delete(result);
		return ResponseEntity.noContent().build();
	}
	
	/********************************************************************************************/

	//cuando se produzca un error de este tipo ejecuta este método
		@ExceptionHandler(SenderoNotFoundException.class)
		public ResponseEntity<ApiError> handleProductoNoEncontrado(SenderoNotFoundException ex) {
			ApiError apiError = new ApiError();
			apiError.setEstado(HttpStatus.NOT_FOUND);
			apiError.setFecha(LocalDateTime.now());
			apiError.setMensaje(ex.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
		}
		
		@ExceptionHandler(JsonProcessingException.class)
		public ResponseEntity<ApiError> handleJsonMappingException(JsonProcessingException ex) {
			ApiError apiError = new ApiError();
			apiError.setEstado(HttpStatus.BAD_REQUEST);
			apiError.setFecha(LocalDateTime.now());
			apiError.setMensaje(ex.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiError);
		}		
}