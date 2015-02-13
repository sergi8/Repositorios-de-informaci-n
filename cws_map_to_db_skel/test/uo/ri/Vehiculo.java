package uo.ri;

import java.util.HashSet;
import java.util.Set;

public class Vehiculo 
{
	private String matricula;
	private String marca;
	private String modelo;
	private TipoVehiculo tV;
	private Cliente cliente;
	private Set<Averia> averias = new HashSet<>();
	
	
	public Vehiculo(String matricula,String marca,String modelo) 
	{
		super();
		this.matricula = matricula;
	}


	void _setTipoVehículo(TipoVehiculo Tv)
	{
		this.tV = tV;
	}
	void _setCliente(Cliente cliente)
	{
		this.cliente = cliente;
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


	public String getMatricula() {
		return matricula;
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
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((matricula == null) ? 0 : matricula.hashCode());
		return result;
	}
	
	public void addAvería(Averia a)
	{
		a._setVehículo(this);
		averias.add(a);
	}
	public void removeAveria(Averia a)
	{
		averias.remove(a);
		a._setVehículo(null);
	} 
	public Set<Averia> getAverias()
	{
		return new HashSet<>(averias);
		//return Collections.unmodifiableSet(vehiculos);
	}

	
}
