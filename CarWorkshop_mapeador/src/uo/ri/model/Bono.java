package uo.ri.model;

import javax.persistence.Entity;

@Entity
public class Bono extends MedioPago{
	
	private Double disponible;
	private String codigo;
	
	public Bono(Cliente cliente, String codigo) {
		super(cliente);
		this.codigo = codigo;
	}
	
	public Bono() {
		super();
	}

	public Double getDisponible() {
		return disponible;
	}

	public void setDisponible(Double disponible) {
		this.disponible = disponible;
	}

	public String getCodigo() {
		return codigo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bono other = (Bono) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bono [disponible=" + disponible + ", codigo=" + codigo + "]";
	}
	
	

}
