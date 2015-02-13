package uo.ri.amp.ui.admin.action;

import uo.ri.amp.busyness.AdminService;
import uo.ri.amp.conf.ServicesFactory;
import alb.util.console.Console;
import alb.util.menu.Action;

/**
 * The Class ExtinguirContratoAction.
 */
public class ExtinguirContratoAction implements Action
{

	/* (non-Javadoc)
	 * @see alb.util.menu.Action#execute()
	 */
	@Override
	public void execute() throws Exception 
	{
		Long ContratoID = Console.readLong("Id del contrato a extinguir");
		AdminService as = ServicesFactory.getAdminService();
		as.ExtinguirContrato(ContratoID);
		
	}

}
