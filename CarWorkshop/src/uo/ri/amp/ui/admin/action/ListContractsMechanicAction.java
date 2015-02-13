package uo.ri.amp.ui.admin.action;

import uo.ri.amp.busyness.AdminService;
import uo.ri.amp.conf.ServicesFactory;
import alb.util.console.Console;
import alb.util.menu.Action;

/**
 * The Class ListContractsMechanicAction.
 */
public class ListContractsMechanicAction implements Action
{

	/* (non-Javadoc)
	 * @see alb.util.menu.Action#execute()
	 */
	@Override
	public void execute() throws Exception 
	{
		int MecanicoID = Console.readInt("Id de mecánico");
		
		AdminService as = ServicesFactory.getAdminService();
		as.listContractsMechanic(MecanicoID);
		
	}

}
