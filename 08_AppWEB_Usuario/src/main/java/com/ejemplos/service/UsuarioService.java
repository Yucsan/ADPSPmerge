package com.ejemplos.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ejemplos.modelo.Usuario;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UsuarioService {
	@Value("${rutaapi}")
	String basePath;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public List<Usuario>obtenerUsuarios(){
		Usuario[] response = restTemplate.getForObject(basePath+"/usuario", Usuario[].class);
				return Arrays.asList(response);
	}
	
	public void borrarUsuario(Long id) {
		restTemplate.delete(basePath+"/usuario/"+id);
	}
	
	
	public Usuario obtenerusuario(Long id) {
		return restTemplate.getForObject(basePath+"/usuario/"+id, Usuario.class);
	}
	
	
	public void crearUsuario (Usuario usuario) {
		restTemplate.postForObject(basePath+"/usuario", usuario, Usuario.class);
	}
	
	public void actualizaUsuario(Long id, Usuario usuario) {
		restTemplate.put(basePath+"/usuario/"+id, usuario);
	}
	
	
	
}
