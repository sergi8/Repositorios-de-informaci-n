package uo.ri.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "TVEHICULOS")
public class Vehiculo {
	
	@Id @GeneratedValue private Long id;
	
	private String matricula;
	private String marca;
	private String modelo;
	@Column(name = "NUM_AVERIAS") private int numAverias;
	
	
	@ManyToOne private Cliente cliente;
	@ManyToOne private TipoVehiculo tipo;
	@OneToMany(mappedBy = "vehiculo") private Set<Averia> averias = new HashSet<Averia>();
	
	public Vehiculo(String matricula) 
	{
		this.matricula = matricula;
	}
	
	public Vehiculo(String matricula, String modelo, String marca) 
	{
		this(matricula);
		setModelo(modelo);
		setMarca(marca);
	}

	public Vehiculo() {
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getNumAverias() {
		return numAverias;
	}

	public String getMatricula() {
		return matricula;
	}

	void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	void setTipo(TipoVehiculo t) 
	{
		tipo = t;
	}
	
	public TipoVehiculo getTipo() 
	{
		return tipo;
	}
	
	public void addAveria(Averia a) 
	{
		averias.add(a);
		numAverias++;
	}
	
	public void removeAveria(Averia a) 
	{
		averias.remove(a);
		numAverias--;
	}
	
	public Set<Averia> getAverias() {
		return Collections.unmodifiableSet(averias);
	}
	
	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((matricula == null) ? 0 : matricula.hashCode());
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
		Vehiculo other = (Vehiculo) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vehiculo [matricula=" + matricula + ", marca=" + marca
				+ ", modelo=" + modelo + ", numAverias=" + numAverias + "]";
	}
	
	
	
	
}
