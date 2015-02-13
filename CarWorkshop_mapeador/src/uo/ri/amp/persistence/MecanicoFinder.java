package uo.ri.amp.persistence;

import java.util.List;

import uo.ri.model.Mecanico;
import uo.ri.persistence.util.Jpa;

public class MecanicoFinder {

	public static Mecanico findById(Long id) 
	{
		return Jpa.getManager().find(Mecanico.class, id);
	}

	@SuppressWarnings("unchecked")
	public static List<Mecanico> findAll() 
	{
		return Jpa.getManager().createNamedQuery("Mecanico.findAll").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public static List<Mecanico> find() 
	{
		return Jpa.getManager().createNamedQuery("Mecanico.find").getResultList();
	}

}
