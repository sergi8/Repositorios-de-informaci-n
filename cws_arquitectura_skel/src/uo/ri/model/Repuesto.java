package uo.ri.model;

import java.util.Collections;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TREPUESTOS")
public class Repuesto 
{
	@Id
	@GeneratedValue
	private long id;
	
	private String codigo;
	private String descripcion;
	private Double precio;
	@OneToMany(mappedBy = "repuesto") private Set<Sustitucion> sustituciones;
	
	/**
	 * constructor con el valor que defíne la identidad(solo lectura no hay set)
	 * @param codigo
	 */
	public Repuesto(String codigo) 
	{
		this.codigo = codigo;
	}
	/**
	 * constructór sin parámetros para el mapeador
	 */
	public Repuesto(){}
	
	public Long getId()
	{
		return id;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Double getPrecio() {
		return precio;
	}
	
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	/**
	 * getter con proteccion de paquete para el modelo de dominio
	 * @return
	 */
	Set<Sustitucion> _getSustituciones() 
	{
		return sustituciones;
	}
	/**
	 * setter público
	 * @return
	 */
	public Set<Sustitucion> getSustituciones()
	{
		return Collections.unmodifiableSet(sustituciones);
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
	
	@Override
	public String toString() {
		return "Repuesto [id=" + id + ", codigo=" + codigo + ", descripcion="
				+ descripcion + ", precio=" + precio + ", sustituciones="
				+ sustituciones + "]";
	}
	
	

}
