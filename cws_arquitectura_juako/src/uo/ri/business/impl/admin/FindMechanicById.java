package uo.ri.business.impl.admin;

import uo.ri.business.impl.Command;
import uo.ri.model.exception.BusinessException;
import uo.ri.persistence.MecanicoFinder;
import uo.ri.persistence.util.Jpa;

public class FindMechanicById implements Command{

	private Long id;

	public FindMechanicById(Long id) {
		this.id = id;
	}

	public Object execute() throws BusinessException {
		return MecanicoFinder.findById(id);
		//return Jpa.getManager().find(Mecanico.class, id);
	}

}
