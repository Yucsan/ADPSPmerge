package com.ejemplos.modelo;

import java.io.Serializable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


/**
 * The persistent class for the MOVIMIENTO database table.
 * 
 */
@Entity
@Table(name="MOVIMIENTO")
@NamedQuery(name="Movimiento.findAll", query="SELECT m FROM Movimiento m")
@JsonIgnoreProperties({"cuenta","tipoMovimiento"})
public class Movimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private MovimientoPK id;
	
	@Temporal( TemporalType.TIMESTAMP)
	@Column(name="FECHA_HORA")
	private Timestamp fechaHora;

	@Column(name="IMPORTE")
	private BigDecimal importe;

	//bi-directional many-to-one association to Cuenta
	@ManyToOne
	@JoinColumn(name="COD_CUENTA")
	private Cuenta cuenta;

	//bi-directional many-to-one association to TipoMovimiento
	@ManyToOne
	@JoinColumn(name="COD_TIPO_MOVIMIENTO")
	private TipoMovimiento tipoMovimiento;

	public Movimiento() {
	}

	public MovimientoPK getId() {
		return this.id;
	}

	public void setId(MovimientoPK id) {
		this.id = id;
	}

	public Timestamp getFechaHora() {
		return this.fechaHora;
	}

	public void setFechaHora(Timestamp fechaHora) {
		this.fechaHora = fechaHora;
	}

	public BigDecimal getImporte() {
		return this.importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public Cuenta getCuenta() {
		return this.cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public TipoMovimiento getTipoMovimiento() {
		return this.tipoMovimiento;
	}

	public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

}