package com.ejemplos.modelo;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the SUCURSAL database table.
 * 
 */
@Entity
@Table(name="SUCURSAL")
@NamedQuery(name="Sucursal.findAll", query="SELECT s FROM Sucursal s")
public class Sucursal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="COD_SUCURSAL")
	private Integer codSucursal;

	@Column(name="CAPITAL_ANIO_ANTERIOR")
	private BigDecimal capitalAnioAnterior;

	@Column(name="DIRECCION")
	private String direccion;

	//bi-directional many-to-one association to Cuenta
	@OneToMany(mappedBy="sucursal")
	private List<Cuenta> cuentas;

	public Sucursal() {
	}

	public int getCodSucursal() {
		return this.codSucursal;
	}

	public void setCodSucursal(int codSucursal) {
		this.codSucursal = codSucursal;
	}

	public BigDecimal getCapitalAnioAnterior() {
		return this.capitalAnioAnterior;
	}

	public void setCapitalAnioAnterior(BigDecimal capitalAnioAnterior) {
		this.capitalAnioAnterior = capitalAnioAnterior;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public List<Cuenta> getCuentas() {
		return this.cuentas;
	}

	public void setCuentas(List<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	public Cuenta addCuenta(Cuenta cuenta) {
		getCuentas().add(cuenta);
		cuenta.setSucursal(this);

		return cuenta;
	}

	public Cuenta removeCuenta(Cuenta cuenta) {
		getCuentas().remove(cuenta);
		cuenta.setSucursal(null);

		return cuenta;
	}

}