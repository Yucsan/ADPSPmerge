package com.ejemplos.DTO;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ejemplos.modelo.Sendero;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SenderoDTOConverter {

	@Autowired
	private ModelMapper modelMapper;
	
	public SenderoDTO convertirADTO(Sendero sendero) {
		return modelMapper.map(sendero,  SenderoDTO.class);
	}
	
	public Sendero convertirASendero(CreateSenderoDTO createsenderoDTO) {
		return modelMapper.map(createsenderoDTO, Sendero.class);
	}
	
	
}
