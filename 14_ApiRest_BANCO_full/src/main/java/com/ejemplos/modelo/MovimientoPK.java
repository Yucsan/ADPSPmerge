package com.ejemplos.modelo;

import java.io.Serializable;
import jakarta.persistence.*;


@Embeddable
public class MovimientoPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="COD_CUENTA", insertable=false, updatable=false)
	private int codCuenta;

	@Column(name="MES")
	private int mes;

	@Column(name="NUM_MOV_MES")
	private int numMovMes;

	public MovimientoPK() {
	}
	public int getCodCuenta() {
		return this.codCuenta;
	}
	public void setCodCuenta(int codCuenta) {
		this.codCuenta = codCuenta;
	}
	public int getMes() {
		return this.mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
	}
	public int getNumMovMes() {
		return this.numMovMes;
	}
	public void setNumMovMes(int numMovMes) {
		this.numMovMes = numMovMes;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MovimientoPK)) {
			return false;
		}
		MovimientoPK castOther = (MovimientoPK)other;
		return 
			(this.codCuenta == castOther.codCuenta)
			&& (this.mes == castOther.mes)
			&& (this.numMovMes == castOther.numMovMes);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.codCuenta;
		hash = hash * prime + this.mes;
		hash = hash * prime + this.numMovMes;
		
		return hash;
	}

	
	
}