package uo.ri.ui.admin.action;

import uo.ri.busyness.AdminService;
import uo.ri.common.BusinessException;
import uo.ri.conf.ServicesFactory;
import alb.util.console.Console;
import alb.util.menu.Action;

public class ListMechanicsAction implements Action 
{
	
	@Override
	public void execute() throws BusinessException 
	{
		Console.println("\nListado de mecánicos\n");
		
		AdminService as = ServicesFactory.getAdminService();
		as.listMechanic();
	}
}
