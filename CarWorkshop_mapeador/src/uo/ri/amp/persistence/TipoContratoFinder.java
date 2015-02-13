package uo.ri.amp.persistence;

import java.util.List;

import uo.ri.amp.model.TipoContrato;
import uo.ri.persistence.util.Jpa;

public class TipoContratoFinder 
{
	
	public static TipoContrato findByNombre(Long id) 
	{
		return Jpa.getManager().find(TipoContrato.class, id);
	}
	
	@SuppressWarnings("unchecked")
	public static List<TipoContrato> findAll() 
	{
		return Jpa.getManager().createNamedQuery("TipoContrato.findAll").getResultList();
	}
	
	
}
