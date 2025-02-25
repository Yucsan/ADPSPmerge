package com.ejemplos.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.ejemplos.modelo.Cliente;
import com.ejemplos.service.ClienteService;

@Controller
public class ClienteController {
	

	@Autowired
	private ClienteService clienteService;

	@GetMapping("/index")
	public String inicio(Model model) {
		model.addAttribute("titulo","Prueba del index");
		return "index";
	}
	
	//Muestra TODOS
	@GetMapping("/listado")
	public String listar(Model model) {
		model.addAttribute("titulo","Listado de Clientes Banco");	
		
		try {
			model.addAttribute("clientes", clienteService.obtenerClientes() );	
		}catch(Exception e1) {
			model.addAttribute("clientes", new ArrayList<Cliente>());
		}
		
		return "listar";
	}
	
	
	// Para insertar Datos
		//fase 1 mostrar formulario
		@GetMapping(value = "/form")
		public String crear(Model model) {
			Cliente cliente = new Cliente(); // objeto mismo nombre que la clase
			
			//paso datos a la vista
			model.addAttribute("cliente", cliente);
			model.addAttribute("titulo", "Formulario de Cliente");
			return "formsincss";
		}
		
		/*  2da fase el usuario envia los datos cuando se hace sumit del formulario  
		 se necesita metodo que trate estos datos la url es igual al Post guardar porcesa el formulario y recibe datos*/
		@PostMapping("/form")
		public String guardar(Cliente cliente) {
			clienteService.crearCliente(cliente);
			return "redirect:/listado";
		}
		
		@GetMapping("/form/{id}")
		public String editar(@PathVariable String id, Model model) {
			
			Cliente cliente = null;
			if(id != null) {
				cliente = clienteService.obtenerCliente(id);
			}else {
				return "redirect:/listado ";
			}
			model.addAttribute("cliente", cliente);
			model.addAttribute("titulo", "Editar Cliente");
			return "formsincss";
			
		}
		
	
		// MUESTRA NIVEL MEDIO

		@GetMapping("/apellidos")
		public String apellidos(Model model) {
			model.addAttribute("titulo","Listado de apellidos");	
			try {
				model.addAttribute("clientes", clienteService.obtenerClientesApellido() );	
			}catch(Exception e1) {
				model.addAttribute("clientes", new ArrayList<Cliente>());
			}
			return "listar";
		}
		
		/*
		// GUARDA EN LA BASE DE DATOS

		@GetMapping("/guardarMedios")
		public String guardarSenderosMedios(Model model) {
		    try {
		        senderoService.guardarSenderosNivelMedio();
		        model.addAttribute("mensaje", "Senderos guardados en MySQL exitosamente.");
		        model.addAttribute("senderos", senderoService.obtenerSenderosNivelMedio()  );
		    } catch (Exception e) {
		        model.addAttribute("mensaje", "Error al guardar senderos: " + e.getMessage());
		    }
		    return "listar"; // Redirige a la vista
		}
		
		
		// GUARDA EN LA BASE DE DATOS
		@GetMapping("/borrarSenderos")
		public String borrarSenderosMedios(Model model) {
			try {
				senderoService.borrarTodosLosSenderosMedios();
				model.addAttribute("mensaje", "Senderos BORRADOS exitosamente.");
			} catch (Exception e) {
				model.addAttribute("mensaje", "Error al BORRAR senderos: " + e.getMessage());
			}
			return "listar"; // Redirige a la vista
		}
		*/
		
	

}
