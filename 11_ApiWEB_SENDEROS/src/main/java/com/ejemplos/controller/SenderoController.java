package com.ejemplos.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.ejemplos.modelo.Sendero;
import com.ejemplos.service.SenderoService;

@Controller
public class SenderoController {
	

	@Autowired
	private SenderoService senderoService;

	
	//Muestra TODOS
	@GetMapping("/listado")
	public String listar(Model model) {
		model.addAttribute("titulo","Listado de senderos");	
		
		try {
			model.addAttribute("senderos", senderoService.obtenerSenderos() );	
		}catch(Exception e1) {
			model.addAttribute("senderos", new ArrayList<Sendero>());
		}
		
		return "listar";
	}
	
		// MUESTRA NIVEL MEDIO
		@GetMapping("/dificultadMedia")
		public String dificultadMedia(Model model) {
			model.addAttribute("titulo","Listado de senderos Medios");	
			try {
				model.addAttribute("senderos", senderoService.obtenerSenderosNivelMedio()  );	
			}catch(Exception e1) {
				model.addAttribute("senderos", new ArrayList<Sendero>());
			}
			return "listar";
		}
		
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
		
		
	

}
