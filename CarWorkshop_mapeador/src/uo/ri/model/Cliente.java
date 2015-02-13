package uo.ri.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import uo.ri.model.types.Address;

@Entity //Usar siempre los de javax.persistence
@Table(name = "TCLIENTES")
public class Cliente {
	
	@Id @GeneratedValue private Long id;
	
	private String dni;
	private String nombre;
	private String apellidos;
	private Address direccion;
	
	@OneToMany(mappedBy="cliente") private Set<Vehiculo> vehiculos = new HashSet<Vehiculo>();
	@OneToMany(mappedBy = "cliente") private Set<MedioPago> mediosPago = new HashSet<MedioPago>();
	
	public Cliente(String dni) {
		this.dni = dni;
	}
	
	public Cliente(String nombre, String apellidos, String dni) {
		this(dni);
		setNombre(nombre);
		setApellidos(apellidos);
	}

	public Cliente() {
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

	public Address getDireccion() {
		return direccion;
	}

	public void setDireccion(Address direccion) {
		this.direccion = direccion;
	}

	public String getDni() {
		return dni;
	}
	
	public void addVehiculo(Vehiculo v) {
		v.setCliente(this);
		vehiculos.add(v);
	}
	
	public void removeVehiculo(Vehiculo v) {
		vehiculos.remove(v);
		v.setCliente(null);
	}
	
	public Set<Vehiculo> getVehiculos() {
		return Collections.unmodifiableSet(vehiculos);
	}
	
	void addMedioPago(MedioPago m) {
		mediosPago.add(m);
	}
	
	void borrarMedioPago(MedioPago m) {
		mediosPago.remove(m);
	}
	
	public void removeMedioPago(MedioPago m) {
		m.unlink();
	}
	
	public Set<MedioPago> getMediosPago() {
		return Collections.unmodifiableSet(mediosPago);
	}
	
	public Long getId() {
		return id;
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

	@Override
	public String toString() {
		return "Cliente [dni=" + dni + ", nombre=" + nombre + ", apellidos="
				+ apellidos + ", direccion=" + direccion + "]";
	}
	
	

}
