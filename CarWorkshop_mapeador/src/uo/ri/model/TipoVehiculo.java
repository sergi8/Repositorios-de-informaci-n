package uo.ri.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "TTIPOSVEHICULO")
public class TipoVehiculo{
	
	@Id @GeneratedValue private Long id;
	
	private String nombre;
	private Double precioHora;
	
	@OneToMany(mappedBy="tipo")  private Set<Vehiculo> vehiculos = new HashSet<Vehiculo>();
	
	public TipoVehiculo(String nombre) {
		this.nombre = nombre;
	}
	
	public TipoVehiculo(String nombre, Double precioHora) {
		this(nombre);
		setPrecioHora(precioHora);
	}

	public TipoVehiculo() {
	}

	public double getPrecioHora() {
		return precioHora;
	}

	public void setPrecioHora(Double precioHora) {
		this.precioHora = precioHora;
	}

	public String getNombre() 
	{
		return nombre;
	}
	
	public void addVehiculo(Vehiculo v) 
	{
		v.setTipo(this);
		vehiculos.add(v);
	}
	
	public void removeVehiculo(Vehiculo v) 
	{
		vehiculos.remove(v);
		v.setTipo(null);
	}
	
	public Set<Vehiculo> getVehiculos() {
		return Collections.unmodifiableSet(vehiculos);
	}
	
	public Long getId() {
		return id;
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
		TipoVehiculo other = (TipoVehiculo) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TipoVehiculo [nombre=" + nombre + ", precioHora=" + precioHora
				+ "]";
	}
	
	
	
	

}
