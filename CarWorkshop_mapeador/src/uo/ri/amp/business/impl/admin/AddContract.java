package uo.ri.amp.business.impl.admin;

import java.util.List;

import uo.ri.amp.model.Contrato;
import uo.ri.amp.model.types.ContratoStatus;
import uo.ri.amp.persistence.ContratoFinder;
import uo.ri.business.impl.admin.Command;
import uo.ri.model.exception.BusinessException;
import uo.ri.persistence.util.Jpa;

public class AddContract implements Command
{
	private Contrato contrato;
	
	public AddContract(Contrato contrato) 
	{
		this.contrato = contrato;
	}

	@Override
	public Object execute() throws BusinessException 
	{
		List<Contrato> contratosdelMecanico = ContratoFinder.findAllFromMechanic(contrato.getMecanico().getId());
		for(Contrato c : contratosdelMecanico)
			if(c.getStatus()== ContratoStatus.ACTIVO)
			{
				c.setStatus(ContratoStatus.EXTINTO);
				Jpa.getManager().merge(c);
			}
		
		Jpa.getManager().persist(contrato);
		return contrato;
	}

}
