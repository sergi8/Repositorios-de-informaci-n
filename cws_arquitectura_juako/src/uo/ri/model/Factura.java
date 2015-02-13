package uo.ri.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import uo.ri.model.exception.BusinessException;
import uo.ri.model.types.AveriaStatus;
import uo.ri.model.types.FacturaStatus;


@Entity
public class Factura {

	@Id
	@GeneratedValue
	private Long id;

	public Long getId() {
		return id;
	}

	private Long numero;
	private Date fecha;
	private Double iva;
	private Double importe;
	@Transient private FacturaStatus status;
	
	@OneToMany(mappedBy = "factura") private List<Averia> averias = new ArrayList<Averia>();
	@OneToMany(mappedBy = "factura") private Set<Cargo> cargos = new HashSet<Cargo>();

	public Factura(Long numero) {
		this.numero = numero;
		this.fecha = new Date();
		setIva(0.21);
	}
	
	

	public Factura(long numero, Collection<Averia> averias) {
		this.numero = numero;
		for (Averia a : averias)
			this.averias.add(a);
		this.fecha = new Date();
		setIva(0.21);
		try {
			calcularImporteFactura();
		} catch (BusinessException e) {
			System.err.println("Averia no terminada o ya facturada.");
		}
	}



	@SuppressWarnings("deprecation")
	public Factura(long numero, Date fecha, List<Averia> averias) {
		this.numero = numero;
		this.averias = averias;
		this.fecha = fecha;
		if ((fecha.getYear() + 1900) > 2012) {
			if (fecha.getMonth() >= 6) {
				setIva(0.21);
			}
		} else
			setIva(0.18);
		try {
			calcularImporteFactura();
		} catch (BusinessException e) {
			System.err.println("Averia no terminada o ya facturada.");
		}
	}


	@SuppressWarnings("deprecation")
	public Factura(long numero, Date fecha) {
		this.numero = numero;
		this.fecha = fecha;
		if (fecha.getMonth()> 2012) {
			if (fecha.getMonth() >= 7) {
				setIva(0.21);
			}
		} else
			setIva(0.18);
		try {
			calcularImporteFactura();
		} catch (BusinessException e) {
			System.err.println("Averia no terminada o ya facturada.");
		}
	}
	
	public Factura() {}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Double getIva() {
		return iva;
	}

	public void setIva(Double iva) {
		this.iva = iva;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public FacturaStatus getStatus() {
		return status;
	}

	public void setStatus(FacturaStatus status) {
		this.status = status;
	}

	public Long getNumero() {
		return numero;
	}
	
	List<Averia> _getAverias(){
		return averias;
	}
	
	public List<Averia> getAverias() {
		 return Collections.unmodifiableList(averias);
		//return new HashSet<>(vehiculos);
	}
	
	
	public void addAveria(Averia a) {
		//órden importa
			a._setFactura(this);
			averias.add(a);
			
			try {
				calcularImporteFactura();
			} catch (BusinessException e) {
				System.err.println("Averia no terminada o ya facturada.");
			}
	}

	public void removeAveria(Averia a) {
		//órden importa
		averias.remove(a);
		a._setFactura(null);
		this.importe -= quitarImporteAveria(a);
		
	}
	
	private void calcularImporteFactura() throws BusinessException{
		Double p = 0.0;
		for (Averia a : _getAverias()) {
			if (a.getStatus().equals(AveriaStatus.ABIERTA) || a.getStatus().equals(AveriaStatus.ASIGNADA)) {
				throw new BusinessException(
						);
			}
			a.calcularImporte();
			p += a.getImporte();
			a.setStatus(AveriaStatus.FACTURADA);
		} 
		p += p * getIva();
		p *= 100;
		p = (double) Math.round(p);
		p /= 100;
		setImporte(p);
		setStatus(FacturaStatus.SIN_ABONAR);	
	}
	
	
	private Double quitarImporteAveria(Averia a) {
		Double p = 0.0;
		a.calcularImporte();
		p += a.getImporte();
		a.setStatus(AveriaStatus.ABIERTA);
		p += p * getIva();
		p *= 100;
		p = (double) Math.round(p);
		p /= 100;
		return p;

	}

	@Override
	public String toString() {
		return "Factura [numero=" + numero + ", fecha=" + fecha + ", iva="
				+ iva + ", importe=" + importe + ", status=" + status + "]";
	}

	@Override
	/**
	 * equals y hashCode sobre la identidad de la entidad.
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		return result;
	}

	@Override
	/**
	 * equals y hashCode sobre la identidad de la entidad.
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Factura other = (Factura) obj;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		return true;
	}



	public Set<Cargo> getCargos() {
		return Collections.unmodifiableSet(cargos);
	}



	public Set<Cargo> _getCargos() {
		return this.cargos;
	}

}
