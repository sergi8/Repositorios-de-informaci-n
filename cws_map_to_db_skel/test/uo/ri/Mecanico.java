package uo.ri;

import java.util.HashSet;
import java.util.Set;

public class Mecanico 
{
	private String dni;
	private String nombre;
	private String apellidos;
	private Set<Intervencion> intervenciones = new HashSet<>();
	
	public Mecanico(String dni ,String nombre, String apellido) 
	{
		this.nombre=nombre;
		this.apellidos=apellido;
	}

	
	
	
	public String getNombre() {
		return nombre;
	}




	public void setNombre(String nombre) {
		this.nombre = nombre;
	}




	public String getApellidos() {
		return apellidos;
	}




	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}




	public String getDni() {
		return dni;
	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
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
		Mecanico other = (Mecanico) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}



	public void addIntervencion(Intervencion inter)
	{
		inter._setMecanico(this);
		intervenciones.add(inter);
	}
	public void removeIntervencion(Intervencion i)
	{
		intervenciones.remove(i);
		i._setMecanico(null);
	} 

	public Set<Intervencion> getIntervenciones()
	{
		return new HashSet<>(intervenciones);
	}
}
