package uo.ri.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import uo.ri.model.key.IntervencionKey;

@Entity
@IdClass(IntervencionKey.class)
@Table(name = "TINTERVENCIONES")
public class Intervencion 
{
	private Integer minutos;
	
	@Id @ManyToOne private Averia averia;
	@Id @ManyToOne private Mecanico mecanico;
	
	@OneToMany(mappedBy = "intervencion")private Set<Sustitucion> sustituciones = new HashSet<Sustitucion>();
	
	public Intervencion(Averia averia, Mecanico mecanico) 
	{
		this.averia = averia;
		this.mecanico = mecanico;
	}
	public Intervencion()
	{
		setMinutos(0);
	}
	
	public void unlink() 
	{
		mecanico._getIntervenciones().remove(this);
		averia._getIntervenciones().remove(this);
		
		mecanico = null;
		averia = null;
	}

	public Integer getMinutos() 
	{
		return minutos;
	}

	public void setMinutos(Integer minutos) 
	{
		this.minutos = minutos;
	}

	public Averia getAveria() 
	{
		return averia;
	}

	public Mecanico getMecanico() 
	{
		return mecanico;
	}

	Set<Sustitucion> _getSustituciones() 
	{
		return sustituciones;
	}
	
	public Set<Sustitucion> getSustituciones()
	{
		return Collections.unmodifiableSet(sustituciones);
	}
	
	public void addSustitucion(Repuesto r)
	{
		sustituciones.add(new Sustitucion(this, r));
	}
	
	private Double getImporteSustituciones() 
	{
		Double total = 0D;
		for(Sustitucion s : sustituciones)
			total += s.getImporte();
		
		return total;
	}
	
	private Double getImporteManoDeObra() 
	{
//		return getMinutos() * (getAveria().getVehiculo().getTipo()
//				.getPrecioHora()/60);
		return 0D;
	}
	
	public Double getImporte() 
	{
		return getImporteSustituciones() + getImporteManoDeObra();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((averia == null) ? 0 : averia.hashCode());
		result = prime * result
				+ ((mecanico == null) ? 0 : mecanico.hashCode());
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
		Intervencion other = (Intervencion) obj;
		if (averia == null) {
			if (other.averia != null)
				return false;
		} else if (!averia.equals(other.averia))
			return false;
		if (mecanico == null) {
			if (other.mecanico != null)
				return false;
		} else if (!mecanico.equals(other.mecanico))
			return false;
		return true;
	}

	@Override
	public String toString() 
	{
		return "Intervencion [minutos=" + minutos + ", averia=" + averia
				+ ", mecanico=" + mecanico + ", sustituciones=" + sustituciones
				+ "]";
	}
	
	
	
}

