package com.ejemplos.controller;

import java.util.List;

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

import com.ejemplos.modelo.Usuario;
import com.ejemplos.modelo.UsuarioRepositorio;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor // lombok crea el contructor
@RequestMapping("/apirestUsuario")
public class UsuarioController {
	
	private final UsuarioRepositorio usuarioRepositorio;
	
	
//----------------------------    http://localhost:8090/apirestUsuario/usuario  ------------
	
	@GetMapping("/usuario") 
	public ResponseEntity<?> obtenerTodos(){
		List<Usuario>result = usuarioRepositorio.findAll();
		
		if(result.isEmpty()) {
			return ResponseEntity.notFound().build();
		}else {
			return ResponseEntity.ok(result);
		}
	}
	
//  -----------------------------  http://localhost:8090/apirestUsuario/usuario/1 ---------------	
	@GetMapping("/usuario/{id}")
	public ResponseEntity<?> obtenerUno(@PathVariable Long id) {
		Usuario result = usuarioRepositorio.findById(id).orElse(null); 
		
		//not found es el 404
		if(result==null)
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(result); // 200	
	}
	
   // -----------------------------  http://localhost:8090/apirestUsuario/saludo/1 ---------------	
	
	@GetMapping("/saludo/{id}")
	public ResponseEntity<?> saludo(@PathVariable Long id) {
		Usuario result = usuarioRepositorio.findById(id).orElse(null); 
		String saludo = "Hola "+result.getNombre();
		
		//not found es el 404
		if(result==null)
			return ResponseEntity.notFound().build();
		else
			return ResponseEntity.ok(saludo); // 200	
	}
	
	
	//en Postman para probarlo url: http://localhost:8080/producto
	//-----------------------------------------------------------------------------------------------------------------
	
	// 1 Insertamos nuevo producto @param nuevo   2 @return producto insertado
	
	@PostMapping("/usuario")
	public  ResponseEntity<?> nuevoUsuario(@RequestBody Usuario nuevo) {
		Usuario saved = usuarioRepositorio.save(nuevo);
		return  ResponseEntity.status(HttpStatus.CREATED).body(saved); //201 Created
	}
	
	// ACTUALIZA usuario | @param editar | @param id | @return  
	
	@PutMapping("/usuario/{id}")
	public ResponseEntity<?>  editaUsuario(@RequestBody Usuario editar, @PathVariable Long id ) {
		if(usuarioRepositorio.existsById(id)) {
			editar.setId(id);
			return ResponseEntity.ok(usuarioRepositorio.save(editar)); //ok
		}else {
			return ResponseEntity.notFound().build(); //404 no lo encuentro
		}
	
	}
	
	//para probarlo en Postman http://localhost:8080/producto/1452 --------------------------------------------------------------
	//Borrar
		
	@DeleteMapping("/usuario/{id}") // da error porque tiene la misma URL
	public ResponseEntity<?> borrarUsuario2(@PathVariable Long id ) {
		if(usuarioRepositorio.existsById(id)) {
			usuarioRepositorio.deleteById(id);
			return ResponseEntity.noContent().build(); //204
		}else {
			return ResponseEntity.notFound().build();
		}
	}

	

}
