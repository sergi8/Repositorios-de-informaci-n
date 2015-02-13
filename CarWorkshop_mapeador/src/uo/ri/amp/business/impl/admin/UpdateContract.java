package uo.ri.amp.business.impl.admin;

import uo.ri.amp.model.Contrato;
import uo.ri.business.impl.admin.Command;
import uo.ri.model.exception.BusinessException;
import uo.ri.persistence.util.Jpa;

public class UpdateContract implements Command
{
	private Contrato contrato;
	public UpdateContract(Contrato contrato) 
	{
		this.contrato=contrato;
	}

	@Override
	public Object execute() throws BusinessException 
	{
		Jpa.getManager().merge(contrato);
		return contrato;
	}

}
