package uo.ri.business.impl.admin;

import uo.ri.business.impl.Command;
import uo.ri.model.Mecanico;
import uo.ri.model.exception.BusinessException;
import uo.ri.persistence.util.Jpa;

public class DeleteMechanic implements Command
{

	private Long idMecanico;

	public DeleteMechanic(Long idMecanico) 
	{
		this.idMecanico = idMecanico;
	}

	public Object execute() throws BusinessException 
	{
		// Un mecanico no se podrÃ¡ borrar si ya ha hecho intervenciones o
				// tiene averias asignadas

				Mecanico m = Jpa.getManager().find(Mecanico.class, idMecanico);
				assertCanBeDeleted(m);
				assertIsNotNull(m);
				Jpa.getManager().remove(m);
				// El remove tiene que recibir un objeto persistent.
				return null;
	}
	private void assertCanBeDeleted(Mecanico m) throws BusinessException 
	{
		if (m.getAsignadas().size() > 0 || m.getIntervenciones().size() > 0)
			
			throw new BusinessException("No se puede borrar mecánico por dependencias.");
	}

	private void assertIsNotNull(Mecanico m) throws BusinessException 
	{
		if (m == null)
			throw new BusinessException("No hay mecánico con este ID.");
	}

}
