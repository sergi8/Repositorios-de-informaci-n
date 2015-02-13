package uo.ri.ui.admin.action;

import uo.ri.busyness.admin.ListMechanics;
import uo.ri.common.BusinessException;
import alb.util.console.Console;
import alb.util.menu.Action;

public class ListMechanicsAction implements Action 
{
	
	@Override
	public void execute() throws BusinessException 
	{
		Console.println("\nListado de mecánicos\n");
		
		ListMechanics listm = new ListMechanics();
		listm.execute();
	}
}
