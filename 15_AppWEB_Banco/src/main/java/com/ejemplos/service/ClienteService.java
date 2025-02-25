package com.ejemplos.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ejemplos.modelo.Cliente;
import com.ejemplos.modelo.ClienteRepositorio;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ClienteService {
	
	@Value("${rutaapi}")
	String basePath;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ClienteRepositorio clienteRepositorio;
	
	public List<Cliente>obtenerClientes(){
		Cliente[] response = restTemplate.getForObject(basePath+"/clientes", Cliente[].class);
		System.out.println("Respuesta de la API: " + Arrays.toString(response)); // Para ver qué llega
		return Arrays.asList(response);
	}
	
	public void borrarCliente(String id) {
		restTemplate.delete(basePath+"/cliente/"+id);
	}
	
	public Cliente obtenerCliente(String id) {
		return restTemplate.getForObject(basePath+"/cliente/"+id, Cliente.class);
	}

	public void actualizaCliente(String id, Cliente cliente) {
		restTemplate.put(basePath+"/cliente/"+id, cliente);
	}
	
	public void crearCliente (Cliente cliente) {
		restTemplate.postForObject(basePath+"/nuevocliente", cliente, Cliente.class);
	}
	
	
	// -----------------------------------------------Obtener Senderos con dificultad media    ----------------
	
	public List<Cliente>obtenerClientesApellido(){
		Cliente[] response = restTemplate.getForObject(basePath+"/verclientes", Cliente[].class);
		
		List<Cliente> clientes = Arrays.asList(response);
		List<Cliente> apellidos1 = new ArrayList<Cliente>();
		
		for( Cliente s: clientes) {
			String aux = s.getApellidos().substring(0,1).toLowerCase();
			if(aux.equals("c")) {
				apellidos1.add(s);
			}
		}
		System.out.println("Apellidos que empiezan con c: " + apellidos1); // Para ver qué llega
		return apellidos1;
	}
	
	
	// --- ---------------------------------------------- metodos API WEB  GUARDA / BORRA  ejercicio2.sql 
	/*
	public void guardarSenderosNivelMedio() {
	    List<Cliente> senderosMedios = obtenerSenderosNivelMedio();
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
	
	public Cliente obtenerSendero(String id) {
		return restTemplate.getForObject(basePath+"/sendero/"+id, Cliente.class);
	}
	
	public void crearSendero(Cliente sendero) {
		restTemplate.postForObject(basePath+"/sendero", sendero, Cliente.class);
	}
	
	public void actualizaSendero(String id, Cliente sendero) {
		restTemplate.put(basePath+"/sendero/"+id, sendero);
	}
	
	*/
	
}
