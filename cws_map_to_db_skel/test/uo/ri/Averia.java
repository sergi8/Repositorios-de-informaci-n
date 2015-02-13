package uo.ri;
import java.util.HashSet;
import java.util.Set;

import javax.xml.crypto.Data;

public class Averia 
{
	private String descripcion;
	private Data fecha;
	private double importe;
	private char status;
	private Vehiculo vehiculo = null;
	private Set<Intervencion> intervenciones = new HashSet<>();
	private Factura factura;
	
	public Averia(Vehiculo vehiculo, String descripcion) 
	{
		this.fecha = fecha;
		vehiculo.addAvería(this);
		this.vehiculo = vehiculo;
		factura = null;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}
	public void _setFactura(Factura factura) {
		this.factura = factura;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Data getFecha() {
		return fecha;
	}

	public void setFecha(Data fecha) {
		this.fecha = fecha;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	void _setVehículo(Vehiculo vehiculo) 
	{
		this.vehiculo = vehiculo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result
				+ ((vehiculo == null) ? 0 : vehiculo.hashCode());
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
		Averia other = (Averia) obj;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (vehiculo == null) {
			if (other.vehiculo != null)
				return false;
		} else if (!vehiculo.equals(other.vehiculo))
			return false;
		return true;
	}
	
	
	
	public void addIntervencion(Intervencion i)
	{
		i._setAveria(this);
		intervenciones.add(i);
	}
	public void removeIntervencion(Intervencion i)
	{
		intervenciones.remove(i);
		i._setAveria(null);
	} 
	public Set<Intervencion> getIntervenciones()
	{
		return new HashSet<>(intervenciones);
		//return Collections.unmodifiableSet(vehiculos);
	}
	

}
