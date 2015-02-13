package uo.ri.model;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import alb.util.date.DateUtil;
import uo.ri.model.exception.BusinessException;
import uo.ri.model.types.AveriaStatus;
import uo.ri.model.types.FacturaStatus;

@Entity
@Table(name = "TFACTURAS")
public class Factura 
{
	
	@Id @GeneratedValue private Long id;
	
	private long numero;
	@Temporal(TemporalType.DATE) private Date fecha;
	@Column(name = "iva")private Double ivaPorcentaje;
	private Double importe;
	@Enumerated(EnumType.STRING) private FacturaStatus status;
	
	@OneToMany(mappedBy = "factura") private Set<Averia> averias = new HashSet<Averia>();
	@OneToMany(mappedBy = "factura") private Set<Cargo> cargos = new HashSet<Cargo>();
	
	public Factura(long numero) {
		this.numero = numero;
		setImporte(0D);
		setStatus(FacturaStatus.SIN_ABONAR);
		setFecha(DateUtil.fromString("1/8/2014"));
	}
	
	public Factura(long numero, Date fecha) {
		this(numero);
		setFecha(fecha);
	}
	
	public Factura(long numero, List<Averia> averias) throws BusinessException {
		this(numero);
		for(int i = 0; i < averias.size(); i++)
			addAveria(averias.get(i));
	}
	
	public Factura(long numero, Date fecha, List<Averia> averias) throws BusinessException {
		this(numero);
		setFecha(fecha);
		for(int i = 0; i < averias.size(); i++)
			addAveria(averias.get(i));
	}

	public Factura() {
		setImporte(0D);
		setStatus(FacturaStatus.SIN_ABONAR);
		
		Date now = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/yyyy");
        
		setFecha(DateUtil.fromString(formateador.format(now)));
	}

	public Date getFecha() 
	{
		return fecha;
	}

	private void setFecha(Date fecha) 
	{
		this.fecha = fecha;
	}

	public Double getIvaPorcentaje() {
		return ivaPorcentaje;
	}

	public void setIvaPorcentaje(Double ivaPorcentaje) {
		this.ivaPorcentaje = ivaPorcentaje;
	}

	public Double getImporte() {
		//return importe;
		return Math.round(importe * 100.0) / 100.0;
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

	public long getNumero() {
		return numero;
	}
	
	public void addAveria(Averia a) throws BusinessException 
	{
		if(a.getStatus() == null)
			throw new BusinessException();
		if(!a.getStatus().equals(AveriaStatus.TERMINADA))
			throw new BusinessException("La averia ha de estar terminada para facturarse");
		a.setFactura(this);
		a.setStatus(AveriaStatus.FACTURADA);
		averias.add(a);
	}
	
	public void removeAveria(Averia a) 
	{
		averias.remove(a);
		a.setFactura(null);
	}
	
	public Set<Averia> getAverias() 
	{
		return Collections.unmodifiableSet(averias);
	}
	
	void addCargo(Cargo c) {
		cargos.add(c);
	}
	
	void removeCargo(Cargo c) {
		cargos.remove(c);
	}
	
	public Set<Cargo> getCargos() {
		return Collections.unmodifiableSet(cargos);
	}
	
	public Long getId() {
		return id;
	}
	
	public void calcularImporte() {
		double total = 0;
		Iterator<Averia> iterador = averias.iterator();
		Averia pivote;
		while(iterador.hasNext()) {
			pivote = iterador.next();
			pivote.calcularImporte();
			total += pivote.getImporte();
		}
		setImporte(total * getIva());
	}
	
	public double getIva() {
		if(getFecha().before(DateUtil.fromString("1/7/2012")))
			return 1.18;
		else
			return 1.21;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (numero ^ (numero >>> 32));
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
		Factura other = (Factura) obj;
		if (numero != other.numero)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Factura [numero=" + numero + ", fecha=" + fecha
				+ ", ivaPorcentaje=" + ivaPorcentaje + ", importe=" + importe
				+ ", status=" + status + "]";
	}
	
	
}
