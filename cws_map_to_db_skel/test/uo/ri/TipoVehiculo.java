package uo.ri;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class TipoVehiculo 
{
	@Id @GeneratedValue private Long id;
	private String nombre;
	private double precioHora;
	private Set<Vehiculo> vehiculos = new HashSet<>();

	public TipoVehiculo(String nombre,double precioHora) 
	{
		super();
		this.nombre = nombre;
		this.precioHora = precioHora;
	}

	public String getNombre() {
		return nombre;
	}

	public double getPrecioHora() {
		return precioHora;
	}

	public void setPrecioHora(double precioHora) {
		this.precioHora = precioHora;
	}
	
	public void addVehiculo(Vehiculo v)
	{
		v._setTipoVehículo(this);
		vehiculos.add(v);
	}
	public void removeVehiculo(Vehiculo v)
	{
		vehiculos.remove(v);
		v._setTipoVehículo(null);
	} 
	public Set<Vehiculo> getVehiculos()
	{
		return new HashSet<>(vehiculos);
		//return Collections.unmodifiableSet(vehiculos);
	}

}
