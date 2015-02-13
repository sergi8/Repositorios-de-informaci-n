package uo.ri.amp.business.impl.admin;

import uo.ri.amp.persistence.ContratoFinder;
import uo.ri.business.impl.admin.Command;
import uo.ri.model.exception.BusinessException;

public class FindContratoByID implements Command
{
	private Long id;
	public FindContratoByID(Long id) 
	{
		this.id = id;
	}

	@Override
	public Object execute() throws BusinessException 
	{
		return ContratoFinder.findById(id);
	}
	

}
