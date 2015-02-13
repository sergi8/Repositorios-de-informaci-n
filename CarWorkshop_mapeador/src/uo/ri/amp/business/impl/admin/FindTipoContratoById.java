package uo.ri.amp.business.impl.admin;

import uo.ri.amp.persistence.TipoContratoFinder;
import uo.ri.business.impl.admin.Command;
import uo.ri.model.exception.BusinessException;

public class FindTipoContratoById implements Command
{
	private Long id;
	
	public FindTipoContratoById(Long id) 
	{
		this.id=id;
	}

	@Override
	public Object execute() throws BusinessException 
	{
		return TipoContratoFinder.findByNombre(id);
	}

}
