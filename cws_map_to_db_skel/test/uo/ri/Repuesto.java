package uo.ri;

import java.util.HashSet;
import java.util.Set;

public class Repuesto 
{
	
	private String codigo;
	private String descripcion;
	private double precio;
	private Set<Sustitucion> sustituciones = new HashSet<Sustitucion>();
	
	public Repuesto(String codigo, String descripcion, double precio) 
	{
		this.codigo=codigo;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public String getCodigo() {
		return codigo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Repuesto other = (Repuesto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
	public void addSustitucion(Sustitucion s)
	{
		s._setRepuesto(this);
		sustituciones.add(s);
	}
	public void removeSustitucion(Sustitucion s)
	{
		sustituciones.remove(s);
		s._setRepuesto(null);
	} 
	public Set<Sustitucion> getSustituciones()
	{
		return new HashSet<>(sustituciones);
		//return Collections.unmodifiableSet(vehiculos);
	}

}
