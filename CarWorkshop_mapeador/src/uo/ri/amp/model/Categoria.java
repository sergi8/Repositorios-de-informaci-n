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
@Table(name = "TCATEGORIAS")
public class Categoria 
{
	@Id 
	@GeneratedValue
	private Long id;
	private String nombre;
	private Double porcent_Productividad;
	private double porcent_Trienio;
	
	@OneToMany(mappedBy = "categoria") private Set<Contrato> contratos = new HashSet<Contrato>();
	
	public Categoria(String nombre) 
	{
		this.nombre=nombre;
	}
	public Categoria() {}
	
	public Double getPorcentProductividad() 
	{
		return porcent_Productividad;
	}
	
	public void setPorcentProductividad(Double porcentProductividad) 
	{
		this.porcent_Productividad = porcentProductividad;
	}
	
	public double getPorcentTrienio() 
	{
		return porcent_Trienio;
	}
	
	public void setPorcentTrienio(double porcentTrienio) 
	{
		this.porcent_Trienio = porcentTrienio;
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
		Categoria other = (Categoria) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nombre=" + nombre
				+ ", porcentProductividad=" + porcent_Productividad
				+ ", porcentTrienio=" + porcent_Trienio + ", contratos="
				+ contratos + "]";
	} 
	
	

}
