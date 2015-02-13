package uo.ri.ui.admin.action;

import uo.ri.busyness.AdminService;
import uo.ri.busyness.impl.AdminServiceImpl;
import uo.ri.common.BusinessException;
import alb.util.console.Console;
import alb.util.menu.Action;

public class DeleteMechanicAction implements Action 
{

	@Override
	public void execute() throws BusinessException {
		Long idMecanico = Console.readLong("Id de mecánico");
		
		AdminService as = new AdminServiceImpl();
		as.deleteMechanic(idMecanico);
		
		Console.println("Se ha eliminado el mecánico");
	}

}
