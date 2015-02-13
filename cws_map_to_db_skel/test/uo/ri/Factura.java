package uo.ri;

import java.util.HashSet;
import java.util.Set;

import uo.ri.model.types.FacturaStatus;

public class Factura 
{
	private Long numerto;
	private String fecha;
	private  double iva;
	private double importe;
	private Set<Averia> averias = new HashSet<>();
	private FacturaStatus status;
	
	public Factura() 
	{
		
	}
	
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public double getIva() {
		return iva;
	}
	public void setIva(double iva) {
		this.iva = iva;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	public FacturaStatus getStatus() {
		return status;
	}
	public void setStatus(FacturaStatus status) {
		this.status = status;
	}
	public Long getNumerto() {
		return numerto;
	}
	
	public void addAveria(Averia a)
	{
		a.setFactura(this);
		averias.add(a);
	}

}
