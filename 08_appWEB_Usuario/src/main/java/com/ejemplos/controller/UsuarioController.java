package com.ejemplos.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import com.ejemplos.modelo.Usuario;
import com.ejemplos.service.UsuarioService;

@Controller
public class UsuarioController {
	

	@Autowired
	private UsuarioService usuarioService;
	//******************************************
	
	@GetMapping("/index")
	public String inicio(Model model) {
		model.addAttribute("titulo","Prueba del index");
		return "index";
	}
	
	//mostrar
	@GetMapping("/listado")
	public String listar(Model model) {
		model.addAttribute("titulo","Listado de usuarios");	
		
		try {
			model.addAttribute("usuarios", usuarioService.obtenerUsuarios() );	
		}catch(Exception e1) {
			model.addAttribute("usuarios", new ArrayList<Usuario>());
		}
		
		return "listar";
	}
	
	//mostrar
	@GetMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id")Long id) {
		
		if(id>0) {
			usuarioService.borrarUsuario(id);
		}
		return "redirect:/listado";
	}
	
	// Para insertar Datos
	//fase 1 mostrar formulario
	@GetMapping(value = "/form")
	public String crear(Model model) {
		Usuario usuario = new Usuario(); // objeto mismo nombre que la clase
		
		//paso datos a la vista
		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", "Formulario de usuario");
		return "formsincss";
	}
	
	/*  2da fase el usuario envia los datos cuando se hace sumit del formulario  
	 se necesita metodo que trate estos datos la url es igual al Post guardar porcesa el formulario y recibe datos*/
	@PostMapping("/form")
	public String guardar(Usuario usuario) {
		usuarioService.crearUsuario(usuario);
		return "redirect:/listado";
	}
	
	@GetMapping("/form/{id}")
	public String editar(@PathVariable Long id, Model model) {
		
		Usuario usuario = null;
		if(id>0) {
			usuario = usuarioService.obtenerusuario(id);
		}else {
			return "redirect:/listado ";
		}
		model.addAttribute("usuario", usuario);
		model.addAttribute("titulo", "Editar Usuario");
		return "formsincss";
		
	}
	
	
	
	
	
	
	
}
