package com.ejemplos.modelo;

import java.io.Serializable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@JsonIgnoreProperties({"cliente","sucursal","movimientos"})
public class Cuenta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cod_cuenta")
	private Integer codCuenta;

	private BigDecimal interes;
	private BigDecimal saldo;

	//bi-directional many-to-one association to Cliente
	@ManyToOne
	@JoinColumn(name="cod_cliente")
	private Cliente cliente;
	
	//bi-directional many-to-one association to Sucursal
	@ManyToOne
	@JoinColumn(name="COD_SUCURSAL")
	private Sucursal sucursal;

	//bi-directional many-to-one association to Movimiento
	@OneToMany(mappedBy="cuenta")
	private List<Movimiento> movimientos;

	
	public int getCodCuenta() {
		return this.codCuenta;
	}

	public void setCodCuenta(int codCuenta) {
		this.codCuenta = codCuenta;
	}

	public BigDecimal getInteres() {
		return this.interes;
	}

	public void setInteres(BigDecimal interes) {
		this.interes = interes;
	}

	public BigDecimal getSaldo() {
		return this.saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


}