package uo.ri.model;

import javax.persistence.*;

import uo.ri.model.types.CargoKey;

@Entity
@IdClass(CargoKey.class)
@Table(name = "TCARGOS")
public class Cargo {
	
	private Double importe;
	
	@Id @ManyToOne private MedioPago medioPago;
	@Id @ManyToOne private Factura factura;
	
	public Cargo(MedioPago medioPago, Factura factura) {
		this(factura, medioPago, 0D);
		
	}
	
	public Cargo(Factura factura, MedioPago medioPago, Double importe) {
		this.medioPago = medioPago;
		this.factura = factura;
		setImporte(importe);
		
		medioPago.addCargo(this);
		factura.addCargo(this);
	}

	public Cargo() {
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public MedioPago getMedioPago() {
		return medioPago;
	}

	public Factura getFactura() {
		return factura;
	}
	
	public void unlink() {
		factura.removeCargo(this);
		medioPago.removeCargo(this);
		
		factura = null;
		medioPago = null;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((factura == null) ? 0 : factura.hashCode());
		result = prime * result
				+ ((medioPago == null) ? 0 : medioPago.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cargo other = (Cargo) obj;
		if (factura == null) {
			if (other.factura != null)
				return false;
		} else if (!factura.equals(other.factura))
			return false;
		if (medioPago == null) {
			if (other.medioPago != null)
				return false;
		} else if (!medioPago.equals(other.medioPago))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cargo [importe=" + importe + ", medioPago=" + medioPago
				+ ", factura=" + factura + "]";
	}
	
	

}
