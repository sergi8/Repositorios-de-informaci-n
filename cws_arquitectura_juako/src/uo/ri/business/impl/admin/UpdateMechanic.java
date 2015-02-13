package uo.ri.business.impl.admin;


import uo.ri.model.Mecanico;
import uo.ri.model.exception.BusinessException;
import uo.ri.persistence.util.Jpa;

public class UpdateMechanic {

	private Mecanico mecanico;

	public UpdateMechanic(Mecanico mecanico) {
		this.mecanico = mecanico;
	}

	public Object execute() throws BusinessException {
		Jpa.getManager().merge(mecanico);
		return null;
	}

}
