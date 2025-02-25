package com.ejemplos.DTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ejemplos.modelo.Cliente;
import com.ejemplos.modelo.Cuenta;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CuentaDTOConverter {

	@Autowired
	private ModelMapper modelMapper;
	
	public CuentaDTO convertirADTO(Cuenta cuenta) {
		return modelMapper.map(cuenta,  CuentaDTO.class);
	}
	
	public Cuenta convertirASendero(CreateCuentaDTO createCuentaDTO) {
		return modelMapper.map(createCuentaDTO, Cuenta.class);
	}
	
	
}
