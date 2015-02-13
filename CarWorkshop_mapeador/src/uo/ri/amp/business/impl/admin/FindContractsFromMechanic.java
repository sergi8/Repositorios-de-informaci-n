package uo.ri.amp.business.impl.admin;

import uo.ri.amp.persistence.ContratoFinder;
import uo.ri.business.impl.admin.Command;
import uo.ri.model.exception.BusinessException;

public class FindContractsFromMechanic implements Command
{
	private long idMecanico;
	
	public FindContractsFromMechanic(long idMecanico) 
	{
		this.idMecanico = idMecanico;
	}
	@Override
	public Object execute() throws BusinessException 
	{
		return ContratoFinder.findAllFromMechanic(idMecanico);
	}

}
