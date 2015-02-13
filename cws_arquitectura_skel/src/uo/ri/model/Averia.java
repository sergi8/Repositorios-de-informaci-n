package uo.ri.model;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import uo.ri.model.types.AveriaStatus;
import alb.util.money.Money;

@Entity
@Table(name = "TAVERIAS")
public class Averia 
{
	@Id
	@GeneratedValue
	private Long id;
	private String descripcion;
	@Temporal(TemporalType.DATE)private Date fecha;
	private Money importe;
	@Enumerated(EnumType.STRING)
	private AveriaStatus status;
	
	@ManyToOne private Mecanico mecanico;
	@ManyToOne private Factura factura;
	@ManyToOne private Vehiculo vehiculo;

	@OneToMany(mappedBy = "averia") private Set<Intervencion> intervenciones = new HashSet<Intervencion>();

	public Averia(String descripcion, Date fecha, Vehiculo vehiculo) 
	{
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.vehiculo = vehiculo;
		status = AveriaStatus.ABIERTA;
		calcularImporte();
	}
	public Averia(){}

	public Money getImporte() 
	{
		return importe;
	}

	public void setImporte(Money d) 
	{
		this.importe = d;
	}

	public AveriaStatus getStatus() 
	{
		return status;
	}

	public void setStatus(AveriaStatus status) 
	{
		this.status = status;
	}
	
	protected void _setMecanico(Mecanico mecanico)
	{
		this.mecanico = mecanico;
	}

	public Mecanico getMecanico() 
	{
		return mecanico;
	}

	public Factura getFactura() 
	{
		return factura;
	}

	protected void _setFactura(Factura factura) 
	{
		this.factura = factura;
	}

	public long getId() 
	{
		return id;
	}

	public String getDescripcion() 
	{
		return descripcion;
	}

	public Date getFecha() 
	{
		return (Date) fecha.clone();
	}

	public Vehiculo getVehiculo() 
	{
		return vehiculo;
	}
	
//	protected void _setVehiculo(Vehiculo vehiculo)
//	{
//		this.vehiculo = vehiculo;
//	}
	
	
	
	Set<Intervencion> _getIntervenciones() 
	{
		return intervenciones;
	}

	public Set<Intervencion> getIntervenciones() 
	{
		return Collections.unmodifiableSet(intervenciones);
	}
	
	public void addIntervencion(Intervencion i)
	{
		intervenciones.add(i);
		calcularImporte();
	}
	
	public void calcularImporte() 
	{
		Double p = 0.0;
		for (Intervencion i : getIntervenciones())
			p += i.getImporte();
		setImporte(Money.euros(p));
		
	}

	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descripcion == null) ? 0 : descripcion.hashCode());
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
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
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
	
	@Override
	public String toString() 
	{
		return "Averia [id=" + id + ", descripcion=" + descripcion + ", fecha="
				+ fecha + ", importe=" + importe + ", status=" + status
				+ ", mecanico=" + mecanico + ", factura=" + factura
				+ ", vehiculo=" + vehiculo + ", intervenciones="
				+ intervenciones + "]";
	}
	
}
