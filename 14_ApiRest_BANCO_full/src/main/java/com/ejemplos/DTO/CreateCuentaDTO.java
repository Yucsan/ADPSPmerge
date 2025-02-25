package com.ejemplos.DTO;




import java.math.BigDecimal;

import com.ejemplos.modelo.MovimientoPK;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateCuentaDTO {
		
	private Integer codCuenta;
	private BigDecimal interes;
	private BigDecimal saldo;
	private String clienteCodCliente;
	private Integer sucursalCodSucursal;
	private MovimientoPK movimientoMovimientoPK;
	
}
