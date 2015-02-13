package uo.ri;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Cliente 
{
	 @Id @GeneratedValue private Long id;
	private String dni;
	private String nombre;
	private String apellidos;
	private String direccion;
	
	@Transient private Set<Vehiculo> vehiculos = new HashSet<>();
	@Transient private Set<MedioPago> medios = new HashSet<>();
	
	public Cliente(String dni, String nombre, String apellido) 
	{
		
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
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
		Cliente other = (Cliente) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}
	
	public void addVehiculo(Vehiculo v)
	{
		v._setCliente(this);
		vehiculos.add(v);
	}
	public void removeVehiculo(Vehiculo v)
	{
		vehiculos.remove(v);
		v._setCliente(null);
	} 
	public Set<Vehiculo> getVehiculos()
	{
		return new HashSet<>(vehiculos);
		//return Collections.unmodifiableSet(vehiculos);
	}
	

}
