package uo.ri;

public class Sustitucion 
{
	private int cantidad;
	
	private Repuesto repuesto;
	private Intervencion intervencion;

	public Sustitucion(int cantidad) 
	{
		this.cantidad = cantidad;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cantidad;
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
		Sustitucion other = (Sustitucion) obj;
		if (cantidad != other.cantidad)
			return false;
		return true;
	}

	public Repuesto getRepuesto() {
		return repuesto;
	}

	public Intervencion getIntervencion() {
		return intervencion;
	}
	
	public void setRepuesto(Repuesto repuesto) 
	{
		this.repuesto = repuesto;
	}
	public void _setRepuesto(Repuesto repuesto) 
	{
		this.repuesto = repuesto;
	}

	public void setIntervencion(Intervencion intervencion) 
	{
		this.intervencion = intervencion;
	}
	public void _setIntervencion(Intervencion intervencion) 
	{
		this.intervencion = intervencion;
	}
	
	
	
	

}
