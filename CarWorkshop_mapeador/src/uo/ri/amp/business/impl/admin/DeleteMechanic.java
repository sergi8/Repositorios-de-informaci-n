package uo.ri.amp.business.impl.admin;

import javax.persistence.EntityManager;

import uo.ri.business.impl.admin.Command;
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
		// Un mecanico no se podr√° borrar si ya ha hecho intervenciones o 
		// tiene averias asignadas
		
		EntityManager em = Jpa.getManager();
		
		Mecanico m = em.find(Mecanico.class, idMecanico);
		assertIsNotNull(m);
		assertCanBeDeleted(m);
		
		em.remove(m);

		return null;
	}

	private void assertIsNotNull(Mecanico m) throws BusinessException 
	{
		if (m == null)
			throw new BusinessException("No existe mec·nico con id = "+idMecanico);
	}
	
	private void assertCanBeDeleted(Mecanico m) throws BusinessException 
	{
		if(!m.getAverias().isEmpty() || !m.getIntervenciones().isEmpty())
			throw new BusinessException("El mec·nico no puede ser borrado.");
	}

}
