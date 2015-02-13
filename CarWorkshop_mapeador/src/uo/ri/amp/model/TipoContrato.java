package uo.ri.amp.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TTiposContrato")
public class TipoContrato 
{
	@Id 
	@GeneratedValue
	private Long id;
	
	private String nombre;
	private int Dias_Indemnizacion;
	
	@OneToMany(mappedBy = "tipoContrato") private Set<Contrato> contratos = new HashSet<Contrato>();
	
	public TipoContrato(String nombre)
	{
		this.nombre=nombre;
	}
	public TipoContrato() {}
	
	public int getDiasIndemnizacion() {
		return Dias_Indemnizacion;
	}
	public void setDiasIndemnizacion(int diasIndemnizacion) 
	{
		Dias_Indemnizacion = diasIndemnizacion;
	}
	public Long getId() 
	{
		return id;
	}
	public String getNombre() 
	{
		return nombre;
	}
	public Set<Contrato> getContratos() 
	{
		return Collections.unmodifiableSet(contratos);
	}
	
	Set<Contrato> _getContratos()
	{
		return contratos;
	}
	public void addContrato(Contrato c)
	{
		//no se hace c.setMecanico(this) porque ya se enlazó en el constructor de Contrato
		contratos.add(c);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		TipoContrato other = (TipoContrato) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "TipoContrato [id=" + id + ", nombre=" + nombre
				+ ", DiasIndemnizacion=" + Dias_Indemnizacion + ", contratos="
				+ contratos + "]";
	}
	
	
	
	
}
