package uo.ri.model;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.*;

import uo.ri.model.types.AveriaStatus;

@Entity
@Table(name = "TAVERIAS")
public class Averia 
{
	
	@Id 
	@GeneratedValue 
	private Long id;
	
	private String descripcion;
	@Temporal(TemporalType.DATE) private Date fecha;
	private Double importe;
	@Enumerated(EnumType.STRING) private AveriaStatus status;
	
	@ManyToOne private Vehiculo vehiculo;
	@ManyToOne private Factura factura;
	@ManyToOne private Mecanico mecanico;
	@OneToMany(mappedBy = "averia") private Set<Intervencion> intervenciones = new HashSet<Intervencion>();

	public Averia(String descripcion, Date fecha, Vehiculo vehiculo) 
	{
		this.descripcion = descripcion;
		this.fecha = fecha;
		this.vehiculo = vehiculo;
		setStatus(AveriaStatus.ABIERTA);
		calcularImporte();
	}
	public Averia() {}
	

	public Long getId() 
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
	
	public Double getImporte() 
	{
		return importe;
	}

	public void setImporte(Double importe) 
	{
		this.importe = importe;
	}
	
	public Vehiculo getVehiculo() 
	{
		return vehiculo;
	}

	public AveriaStatus getStatus() 
	{
		return status;
	}

	public void setStatus(AveriaStatus status) 
	{
		this.status = status;
	}
	
	public Factura getFactura() 
	{
		return factura;
	}
	
	void setFactura(Factura f) 
	{
		factura = f;
	}
	
	public Mecanico getMecanico() 
	{
		return mecanico;
	}
	
	void _setMecanico(Mecanico m) 
	{
		mecanico = m;
	}
	
	public void addIntervencion(Intervencion i) 
	{
		intervenciones.add(i);
		importe += i.getImporte();
	}
	
	public void removeIntervencion(Intervencion i) 
	{
		intervenciones.remove(i);
		importe -= i.getImporte();
	}
	
	Set<Intervencion> _getIntervenciones() 
	{
		return intervenciones;
	}
	
	public Set<Intervencion> getIntervenciones() 
	{
		return Collections.unmodifiableSet(intervenciones);
	}

	public void calcularImporte() 
	{
		double total = 0;
		Iterator<Intervencion> iterador = intervenciones.iterator();
		while(iterador.hasNext())
			total += iterador.next().getImporte();
		
		setImporte(total);
	}
	

	@Override
	public int hashCode() {
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
	public String toString() {
		return "Averia [id=" + id + ", descripcion=" + descripcion + ", fecha="
				+ fecha + ", importe=" + importe + ", status=" + status
				+ ", vehiculo=" + vehiculo + ", factura=" + factura
				+ ", mecanico=" + mecanico + ", intervenciones="
				+ intervenciones + "]";
	}
	
}
