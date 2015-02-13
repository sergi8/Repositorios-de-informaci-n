package uo.ri.business.impl.admin;

import java.util.List;

import uo.ri.model.Mecanico;
import uo.ri.persistence.util.Jpa;

public class MecanicoFinder 
{
	
	public static Mecanico findById(long id) {
		return Jpa.getManager().find(Mecanico.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public static List<Mecanico> findAll() {
		return Jpa.getManager().createNamedQuery("Mecanico.findAll").getResultList();
	}

}
