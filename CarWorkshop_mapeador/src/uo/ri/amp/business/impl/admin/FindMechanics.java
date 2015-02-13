package uo.ri.amp.business.impl.admin;

import uo.ri.amp.persistence.MecanicoFinder;
import uo.ri.business.impl.admin.Command;
import uo.ri.model.exception.BusinessException;

public class FindMechanics implements Command
{

	@Override
	public Object execute() throws BusinessException 
	{
		return MecanicoFinder.find();
	}

}
