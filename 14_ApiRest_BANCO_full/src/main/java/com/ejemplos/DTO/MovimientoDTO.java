package com.ejemplos.DTO;


import java.math.BigDecimal;
import java.sql.Timestamp;

import com.ejemplos.modelo.MovimientoPK;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MovimientoDTO {
	

	private MovimientoPK id;
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Timestamp fechaHora;
	private BigDecimal importe;
	private Integer cuentaCodCuenta;
	private String tipoMovimientoCodtipoMovimiento;
	

	
}
