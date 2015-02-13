package uo.ri.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import uo.ri.model.key.SustitucionKey;

@Entity
@IdClass(SustitucionKey.class)
@Table(name = "TSUSTITUCIONES")
public class Sustitucion 
{
	private Integer cantidad;
	
	@Id @ManyToOne private Repuesto repuesto;
	@Id @ManyToOne private Intervencion intervencion;
	
	public Sustitucion(Intervencion intervencion, Repuesto repuesto) 
	{
		this.intervencion=intervencion;
		this.repuesto = repuesto;
		
		intervencion._getSustituciones().add(this);
		repuesto._getSustituciones().add(this);
	}
	public Sustitucion() {}
	
	public void unLink()
	{
		intervencion._getSustituciones().remove(this);
		repuesto._getSustituciones().remove(this);
		
		this.intervencion=null;
		this.repuesto = null;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Repuesto getRepuesto() {
		return repuesto;
	}

	public Intervencion getIntervencion() 
	{
		return intervencion;
	}
	
	public Double getImporte() 
	{
		return repuesto.getPrecio() * getCantidad();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((intervencion == null) ? 0 : intervencion.hashCode());
		result = prime * result
				+ ((repuesto == null) ? 0 : repuesto.hashCode());
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
		Sustitucion other = (Sustitucion) obj;
		if (intervencion == null) {
			if (other.intervencion != null)
				return false;
		} else if (!intervencion.equals(other.intervencion))
			return false;
		if (repuesto == null) {
			if (other.repuesto != null)
				return false;
		} else if (!repuesto.equals(other.repuesto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sustitucion [cantidad=" + cantidad + ", repuesto=" + repuesto
				+ ", intervencion=" + intervencion + "]";
	}

	
}

