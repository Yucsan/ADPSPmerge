package com.ejemplos.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ejemplos.modelo.Sendero;
import com.ejemplos.modelo.SenderoRepositorio;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class SenderoService {
	
	@Value("${rutaapi}")
	String basePath;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private SenderoRepositorio senderoRepositorio;
	
	public List<Sendero>obtenerSenderos(){
		Sendero[] response = restTemplate.getForObject(basePath+"/sendero", Sendero[].class);
		System.out.println("Respuesta de la API: " + Arrays.toString(response)); // Para ver qué llega
		return Arrays.asList(response);
	}
	
	// -----------------------------------------------Obtener Senderos con dificultad media    ----------------
	public List<Sendero>obtenerSenderosNivelMedio(){
		Sendero[] response = restTemplate.getForObject(basePath+"/sendero", Sendero[].class);
		
		List<Sendero> senderos = Arrays.asList(response);
		List<Sendero> nivelMedio = new ArrayList<Sendero>();
		for( Sendero s: senderos) {
			if(s.getDificultad().equals("media")) {
				nivelMedio.add(s);
			}
		}
		System.out.println("Senderos Nivel Medio: " + nivelMedio); // Para ver qué llega
		return nivelMedio;
	}
	
	
	// --- ---------------------------------------------- metodos API WEB  GUARDA / BORRA  ejercicio2.sql 
	public void guardarSenderosNivelMedio() {
	    List<Sendero> senderosMedios = obtenerSenderosNivelMedio();
	    senderoRepositorio.saveAll(senderosMedios);
	    System.out.println("Senderos guardados en MySQL correctamente.");
	}
	
	
	public void borrarTodosLosSenderosMedios() {
		senderoRepositorio.deleteAll();
		System.out.println("Todos los senderos de nivel medio han sido eliminados.");
	}
	
	
	// ---  metodos de la API REST  -- sin implementar
	
	public void borrarSendero(String id) {
		restTemplate.delete(basePath+"/sendero/"+id);
	}
	
	public Sendero obtenerSendero(String id) {
		return restTemplate.getForObject(basePath+"/sendero/"+id, Sendero.class);
	}
	
	public void crearSendero(Sendero sendero) {
		restTemplate.postForObject(basePath+"/sendero", sendero, Sendero.class);
	}
	
	public void actualizaSendero(String id, Sendero sendero) {
		restTemplate.put(basePath+"/sendero/"+id, sendero);
	}
	
	
	
}
