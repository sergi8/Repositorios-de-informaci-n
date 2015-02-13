package uo.ri.amp.ui.admin.action;

import uo.ri.amp.busyness.AdminService;
import uo.ri.amp.conf.ServicesFactory;
import alb.util.console.Console;
import alb.util.menu.Action;

// TODO: Auto-generated Javadoc
/**
 * The Class DeleteContractAction.
 */
public class DeleteContractAction implements Action
{

	/* (non-Javadoc)
	 * @see alb.util.menu.Action#execute()
	 */
	@Override
	public void execute() throws Exception 
	{
		int idContract = Console.readInt("Id del contrato a eliminar");
		
		AdminService as = ServicesFactory.getAdminService();
		as.deleteContract(idContract);
	
	}

}
