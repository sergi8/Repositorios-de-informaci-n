package uo.ri.amp.business.impl.admin;

import uo.ri.amp.persistence.CategoriaFinder;
import uo.ri.business.impl.admin.Command;
import uo.ri.model.exception.BusinessException;

public class FindCategoriaById implements Command
{
	private Long id;
	
	public FindCategoriaById(Long id) 
	{
		this.id=id;
	}

	@Override
	public Object execute() throws BusinessException 
	{
		return CategoriaFinder.findByNombre(id);
	}
	
}
