package com.ejemplos.controllers;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ejemplos.model.Usuario;

@Controller
@RequestMapping("/app") // agrega carpetas a la url
public class IndexController1 {
	
	@GetMapping({ "/index" })
	public String index() {
		return ("index"); //recuerda esto llama a un html en templates index.html
	}

//  http://localhost:8080/app/index	 

	// pasar datos del controlador a la vista de la interfaz Model
	@GetMapping("/index2")
	public String index2(Model model) {
		model.addAttribute("titulo", "hola Spring:pasar datos al controlador" + " a la vista con la interfaz Model");
		return ("/index2");
	}

	// otra forma
	@RequestMapping("/perfil")
	public String perfil(Model model) {

		Usuario usuario = new Usuario();
		usuario.setNombre("Jose Luis");
		usuario.setApellido("Perez Del Rio");
		usuario.setEmail("rr@gmail.com");

		model.addAttribute("usuario", usuario); // la clase y la variable deben llamarse igual que la clase en minuscula
		model.addAttribute("titulo", "Perfil".concat(usuario.getNombre()));

		return "perfil";
	}

	// pasar lista
	@GetMapping("/listar1")
	public String listar1(Model model) {

		// creamos una lista de usuarios para probarlo
		List<Usuario> usuarios = new ArrayList<Usuario>();
		model.addAttribute("titulo", "Listado de usuarios");
		model.addAttribute("usuarios", usuarios);
		return "listar"; // se llama al html ATENTO
	}

	// pasar lista
	@GetMapping("/listar2")
	public String listar2(Model model) {

		// creamos una lista de usuarios para probarlo
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(new Usuario("Jose Luis", "Rus","rra@gmail.com"));
		usuarios.add(new Usuario("María Jose", "Mus","Maa@gmail.com"));
		usuarios.add(new Usuario("Korra", "Kor","korr@gmail.com"));
		
		model.addAttribute("titulo", "Listado de usuarios");
		model.addAttribute("usuarios", usuarios);
		return "listar"; // se llama al html ATENTO
	}

}
