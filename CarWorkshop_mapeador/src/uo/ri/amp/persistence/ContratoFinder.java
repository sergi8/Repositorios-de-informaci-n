package uo.ri.amp.persistence;

import java.util.List;

import uo.ri.amp.model.Contrato;
import uo.ri.persistence.util.Jpa;

public class ContratoFinder 
{
	public static Contrato findById(Long id) 
	{
		return Jpa.getManager().find(Contrato.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public static List<Contrato> findAll() 
	{
		return Jpa.getManager().createNamedQuery("Contrato.findAll").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public static List<Contrato> findAllFromMechanic(Long idMecánico) 
	{
		return Jpa.getManager().createNamedQuery("Contrato.findAllFromMechanic").setParameter(1, idMecánico).getResultList();
	}
	
	
}
