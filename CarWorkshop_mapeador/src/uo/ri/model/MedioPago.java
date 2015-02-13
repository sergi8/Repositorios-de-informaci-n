package uo.ri.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@Inheritance
@Table(name = "TMEDIOSPAGO")
public abstract class MedioPago {
	
	@Id @GeneratedValue private Long id;
	
	private Double acumulado;
	
	@OneToMany(mappedBy = "medioPago") private Set<Cargo> cargos = new HashSet<Cargo>();
	@ManyToOne private Cliente cliente;
	
	public MedioPago(Cliente cliente) {
		this.cliente = cliente;
		cliente.addMedioPago(this);
		acumulado = 0D;
	}
	
	public MedioPago() {
		
	}

	public Double getAcumulado() {
		return acumulado;
	}

	public Cliente getCliente() {
		return cliente;
	}
	
	void addCargo(Cargo c) {
		cargos.add(c);
		acumulado += c.getImporte();
	}

	void removeCargo(Cargo c) {
		cargos.remove(c);
		acumulado -= c.getImporte();
	}
	
	public Set<Cargo> getCargos() {
		return Collections.unmodifiableSet(cargos);
	}
	
	public void unlink() {
		cliente.borrarMedioPago(this);
		cliente = null;
	}
	
	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
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
		MedioPago other = (MedioPago) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MedioPago [acumulado=" + acumulado
				+ ", cliente=" + cliente + "]";
	}

	public String toFormatedString() {
		// TODO Auto-generated method stub
		return toString();
	}

}
