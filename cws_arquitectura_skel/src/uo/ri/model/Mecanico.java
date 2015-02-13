package uo.ri.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import uo.ri.model.types.AveriaStatus;
@Entity
@Table(name = "TMECANICOS")
public class Mecanico 
{
	@Id
	@GeneratedValue
	private Long id;
	
	private String dni;
	private String nombre;
	private String apellidos;

	@OneToMany(mappedBy="mecanico")private Set<Averia> averias = new HashSet<Averia>();
	@OneToMany(mappedBy="mecanico")private Set<Intervencion> intervenciones = new HashSet<Intervencion>();
	
	public Mecanico(String dni, String nombre, String apellidos) 
	{
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}
	
	public Mecanico() {	}
	
	public Long getId()
	{
		return id;
	}
	
	public String getDni() 
	{
		return dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}

	public String getApellidos() 
	{
		return apellidos;
	}

	public void setApellidos(String apellidos) 
	{
		this.apellidos = apellidos;
	}
	
	Set<Intervencion> _getIntervenciones() 
	{
		return intervenciones;
	}

	public Set<Intervencion> getIntervenciones() 
	{
		return Collections.unmodifiableSet(intervenciones);
	}
	
	public void addAveria(Averia a) 
	{
		a._setMecanico(this);
		averias.add(a);
		a.setStatus(AveriaStatus.ASIGNADA);
	}

	public void removeAveria(Averia a) 
	{
		averias.remove(a);
		a._setMecanico(null);
		a.setStatus(AveriaStatus.TERMINADA);

	}
	Set<Averia> _getAverias()
	{
		return averias;
	}
	
	public Set<Averia> getAsignadas() 
	{
		return Collections.unmodifiableSet(averias);
	}
	
	//posiblemente esté mal seguramente en ved de minutos se le pase directamente la intervención
	public void intervenir(Averia a,Intervencion i)
	{
		a.addIntervencion(i);
		intervenciones.add(i);
	}

	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
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
		Mecanico other = (Mecanico) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Mecanico [id=" + id + ", dni=" + dni + ", nombre=" + nombre
				+ ", apellidos=" + apellidos + ", averias=" + averias
				+ ", intervenciones=" + intervenciones + "]";
	}
	

}
