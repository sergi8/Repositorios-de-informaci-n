package uo.ri.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.*;

import uo.ri.model.types.IntervencionKey;

@Entity
@IdClass(IntervencionKey.class)
@Table(name = "TINTERVENCIONES")
public class Intervencion 
{
	
	private Integer minutos;
	
	@Id @ManyToOne private Averia averia;
	@Id @ManyToOne private Mecanico mecanico;
	
	@OneToMany(mappedBy = "intervencion") private Set<Sustitucion> sustituciones = new HashSet<Sustitucion>();
	
	public Intervencion(Mecanico mecanico, Averia averia) 
	{
		this.averia = averia;
		this.mecanico = mecanico;
		mecanico.addIntervencion(averia, this);
	}
	public Intervencion(){}
	
	public void unlink() 
	{
		mecanico._getIntervenciones().remove(this);
		averia.removeIntervencion(this);
		
		mecanico = null;
		averia = null;
	}
	
	public int getMinutos() 
	{
		return minutos;
	}

	public void setMinutos(int minutos) 
	{
		this.minutos = minutos;
	}

	public Mecanico getMecanico() 
	{
		return mecanico;
	}

	public Averia getAveria() 
	{
		return averia;
	}

	Set<Sustitucion> _getSustituciones() 
	{
		return sustituciones;
	}
	
	public Set<Sustitucion> getSustituciones() 
	{
		return Collections.unmodifiableSet(sustituciones);
	}
	
	private double getImporteSustituciones() 
	{
		double total = 0;
		Iterator<Sustitucion> iterador = sustituciones.iterator();
		while(iterador.hasNext())
			total += iterador.next().getImporte();
		
		return total;
	}
	
	private double getImporteManoDeObra() 
	{
		return getMinutos() * (getAveria().getVehiculo().getTipo().getPrecioHora()/60);
	}
	
	public double getImporte() 
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
	public String toString() {
		return "Intervencion [minutos=" + minutos + ", mecanico=" + mecanico
				+ ", averia=" + averia + ", sustituciones=" + sustituciones
				+ "]";
	}
	

}
