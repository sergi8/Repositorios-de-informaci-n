package uo.ri.business.impl;

import java.util.List;

import uo.ri.business.AdminService;
import uo.ri.business.impl.admin.AddMechanic;
import uo.ri.business.impl.admin.DeleteMechanic;
import uo.ri.business.impl.admin.FindAllMechanics;
import uo.ri.business.impl.admin.FindMechanicById;
import uo.ri.business.impl.admin.UpdateMechanic;
import uo.ri.model.Mecanico;
import uo.ri.model.exception.BusinessException;
import uo.ri.persistence.util.Jpa;

public class AdminServiceImpl implements AdminService {
	
	private CommandExecutor executor = new CommandExecutor();

	@Override
	public void newMechanic(final Mecanico mecanico) throws BusinessException {
		executor.execute(new Command() {

			@Override
			public Object execute() throws BusinessException {
				Jpa.getManager().persist(mecanico);		
				return null;
			}
			
		});
	}

	@Override
	public void updateMechanic(Mecanico mecanico) throws BusinessException {
		executor.execute(new UpdateMechanic( mecanico ));
	}

	@Override
	public void deleteMechanic(Long idMecanico) throws BusinessException {
		executor.execute(new DeleteMechanic(idMecanico));
	}

	@Override
	public List<Mecanico> findAllMechanics() throws BusinessException {
		executor.execute(return (List<Mecanico>) executor.execute(new FindAllMechanics());
	}

	@Override
	public Mecanico findMechanicById(Long id) throws BusinessException {
		return (Mecanico) executor.execute(new FindMechanicById(id));
	}

}
