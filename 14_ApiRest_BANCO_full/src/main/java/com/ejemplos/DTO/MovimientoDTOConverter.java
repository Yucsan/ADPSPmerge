package com.ejemplos.DTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ejemplos.modelo.Cliente;
import com.ejemplos.modelo.Cuenta;
import com.ejemplos.modelo.Movimiento;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MovimientoDTOConverter {

	@Autowired
	private ModelMapper modelMapper;
	
	public MovimientoDTO convertirADTO(Movimiento movi) {
		return modelMapper.map(movi,  MovimientoDTO.class);
	}
	
	public Movimiento convertirAMovimiento(CreateMovimientoDTO createMovimientoDTO) {
		return modelMapper.map(createMovimientoDTO, Movimiento.class);
	}
	
	
}
