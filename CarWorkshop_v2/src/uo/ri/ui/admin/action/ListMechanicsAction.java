package uo.ri.ui.admin.action;

import uo.ri.busyness.AdminService;
import uo.ri.busyness.impl.AdminServiceImpl;
import uo.ri.common.BusinessException;
import alb.util.console.Console;
import alb.util.menu.Action;

public class ListMechanicsAction implements Action 
{
	
	@Override
	public void execute() throws BusinessException 
	{
		Console.println("\nListado de mecánicos\n");
		
		AdminService as = new AdminServiceImpl();
		as.listMechanic();
	}
}
