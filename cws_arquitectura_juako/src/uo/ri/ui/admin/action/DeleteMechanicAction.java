package uo.ri.ui.admin.action;

import uo.ri.business.AdminService;
import uo.ri.conf.ServicesFactory;
import uo.ri.model.exception.BusinessException;
import alb.util.console.Console;
import alb.util.menu.Action;

public class DeleteMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {
		
		Long idMecanico = Console.readLong("Id de mecánico"); 
		
		AdminService as = ServicesFactory.getAdminService();
		as.deleteMechanic(idMecanico);
		
		Console.println("Se ha eliminado el mecánico");
	}

}
