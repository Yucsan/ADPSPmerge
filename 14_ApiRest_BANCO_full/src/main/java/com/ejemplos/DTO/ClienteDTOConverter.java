package com.ejemplos.DTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ejemplos.modelo.Cliente;
import com.ejemplos.modelo.Cuenta;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ClienteDTOConverter {

	@Autowired
	private ModelMapper modelMapper;
	
	public ClienteDTO convertirADTO(Cliente cliente) {
		return modelMapper.map(cliente,  ClienteDTO.class);
	}
	
	public Cliente convertirACliente(CreateClienteDTO createClienteDTO) {
		return modelMapper.map(createClienteDTO, Cliente.class);
	}
	
	
}
