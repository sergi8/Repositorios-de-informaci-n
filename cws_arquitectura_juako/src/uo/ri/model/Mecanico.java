package uo.ri.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import uo.ri.model.types.AveriaStatus;

@Entity
public class Mecanico {

	
	@Id
	@GeneratedValue
	private Long id;

	public Long getId() {
		return id;
	}
	
	
	private String dni;
	private String nombre;
	private String apellidos;

	@OneToMany(mappedBy = "mecanico") private Set<Intervencion> intervenciones = new HashSet<>();
	@Transient private Set<Averia> averias = new HashSet<>();

	public Mecanico(String dni, String nombre, String apellidos) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	public Mecanico() {
	}

	public String getDni() {
		return dni;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	@Override
	public String toString() {
		return "Mecanico [nombre=" + nombre + ", apellidos=" + apellidos
				+ "] - DNI : " + dni;
	}

	@Override
	/**
	 * equals y hashCode sobre la identidad de la entidad.
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		return result;
	}

	@Override
	/**
	 * equals y hashCode sobre la identidad de la entidad.
	 */
	public boolean equals(Object obj) {
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

	Set<Intervencion> _getIntervenciones() {
		return intervenciones;
	}

	public Set<Intervencion> getIntervenciones() {
		return Collections.unmodifiableSet(intervenciones);
	}

	public void addAveria(Averia a) {
		a._setMecanico(this);
		averias.add(a);
	}

	public void removeAveria(Averia a) {
		averias.remove(a);
		a._setMecanico(null);
		a.setStatus(AveriaStatus.TERMINADA);

	}

	public Set<Averia> getAsignadas() {
		return Collections.unmodifiableSet(averias);
	}

}
