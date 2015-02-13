package uo.ri.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "TREPUESTOS")
public class Repuesto {
	
	@Id @GeneratedValue private Long id;
	
	private String codigo;
	private String descripcion;
	private Double precio;
	
	@OneToMany(mappedBy = "repuesto") private Set<Sustitucion> sustituciones = new HashSet<Sustitucion>();
	
	public Repuesto(String codigo) {
		this.codigo = codigo;
	}
	
	public Repuesto(String codigo, String descripcion, Double precio) {
		this(codigo);
		setDescripcion(descripcion);
		setPrecio(precio);
	}
	
	public Repuesto() {
		
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public String getCodigo() {
		return codigo;
	}
	
	Set<Sustitucion> _getSustituciones() {
		return sustituciones;
	}
	
	public Set<Sustitucion> getSustituciones() {
		return Collections.unmodifiableSet(sustituciones);
	}
	
	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Repuesto other = (Repuesto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Repuesto [codigo=" + codigo + ", descripcion=" + descripcion
				+ ", precio=" + precio + "]";
	}
	
	

}
