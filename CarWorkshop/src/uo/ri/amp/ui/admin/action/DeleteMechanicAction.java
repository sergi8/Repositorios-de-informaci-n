package uo.ri.amp.ui.admin.action;

import uo.ri.amp.busyness.AdminService;
import uo.ri.common.BusinessException;
import uo.ri.amp.conf.ServicesFactory;
import alb.util.console.Console;
import alb.util.menu.Action;

// TODO: Auto-generated Javadoc
/**
 * The Class DeleteMechanicAction.
 */
public class DeleteMechanicAction implements Action 
{

	/* (non-Javadoc)
	 * @see alb.util.menu.Action#execute()
	 */
	@Override
	public void execute() throws BusinessException 
	{
		Long idMecanico = Console.readLong("Id de mecánico");
		
		AdminService as = ServicesFactory.getAdminService();
		as.deleteMechanic(idMecanico);
	}

}
