package com.ejemplos.DTO;




import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateCuentaDTO {
		
	private Integer codCuenta;
	private BigDecimal interes;
	private BigDecimal saldo;
	private String clienteCodCliente;
}
