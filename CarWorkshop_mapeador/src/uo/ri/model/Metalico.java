package uo.ri.model;

import javax.persistence.Entity;

@Entity
public class Metalico extends MedioPago{
	
	public Metalico(Cliente cliente) {
		super(cliente);
	}
	
	public Metalico() {
		super();
	}

}
