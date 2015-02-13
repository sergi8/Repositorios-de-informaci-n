package uo.ri;

import java.util.HashSet;
import java.util.Set;

public class Intervencion 
{
	private int minutos;
	private Averia averia;
	private Mecanico mecanico;
	
	private Set<Sustitucion> sustituciones = new HashSet<Sustitucion>();
	
	public Intervencion(Mecanico mecanico, Averia averia) 
	{
		
	}
	void _setAveria(Averia averia)
	{
		this.averia = averia;
	}
	public void setAveria(Averia averia) {
		this.averia = averia;
	}

	public int getMinutos() {
		return minutos;
	}

	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}

	public Averia getAveria() 
	{
		return averia;
	}
	
	void _setMecanico(Mecanico mecanico)
	{
		this.mecanico = mecanico;
	}
	
	public void setMecanico(Mecanico mecanico)
	{
		this.mecanico = mecanico;
	}
	
	public Mecanico getMecanico() 
	{
		return mecanico;
	}
	

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + minutos;
		return result;
	}

	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Intervencion other = (Intervencion) obj;
		if (minutos != other.minutos)
			return false;
		return true;
	}
	
	public void addSustitucion(Sustitucion s)
	{
		s._setIntervencion(this);
		sustituciones.add(s);
	}
	public void removeSustitucion(Sustitucion s)
	{
		sustituciones.remove(s);
		s._setIntervencion(null);
	} 
	public Set<Sustitucion> getSustituciones()
	{
		return new HashSet<>(sustituciones);
		//return Collections.unmodifiableSet(vehiculos);
	}

}
