package uo.ri.model;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import uo.ri.model.types.AveriaStatus;

@Entity
public class Averia {

	@Id
	@GeneratedValue
	private Long id;

	public Long getId() {
		return id;
	}

	public Mecanico getMecanico() {
		return mecanico;
	}

	private String descripcion;
	private Date fecha;
	private Double importe;
	private AveriaStatus status;
	
	@ManyToOne private Vehiculo vehiculo;
	@ManyToOne private Factura factura;
	@ManyToOne private Mecanico mecanico;
	
	public Factura getFactura() {
		return factura;
	}


	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	@OneToMany(mappedBy = "averia") private Set<Intervencion> intervenciones = new HashSet<>();


	void _setVehiculo(Vehiculo v){
		this.vehiculo = v;
	}
	
	
	void _setFactura(Factura f){
		this.factura = f;
	}
	
	void _setMecanico(Mecanico m) {
		this.mecanico = m;
		
	}
	
	public Averia(Vehiculo vehiculo) {
		this.fecha = new Date();
		vehiculo.addAveria(this);
		this.status = AveriaStatus.ABIERTA;
	}
	
	
	public Averia(Date fecha, Vehiculo vehiculo) {
		this(vehiculo);
		this.fecha = fecha;
		this.status = AveriaStatus.ABIERTA;
	}

	public Averia(Vehiculo vehiculo, String descripcion) {
		this(vehiculo);
		this.descripcion = descripcion;
		this.status = AveriaStatus.ABIERTA;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public AveriaStatus getStatus() {
		return status;
	}

	public void setStatus(AveriaStatus status) {
		this.status = status;
	}

	public Date getFecha() {
		return fecha;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	

	 Set<Intervencion> _getIntervenciones() {
		return intervenciones;
	}
	 
	 public Set<Intervencion> getIntervenciones() {
			return Collections.unmodifiableSet(intervenciones);
		}

	public void calcularImporte() {
		Double p = 0.0;
		for (Intervencion i : getIntervenciones()) {
			p += i.getImporte();
		}
		setImporte(p);
		
	}


	
	
	
	
	
}
