package uo.ri.amp.ui.admin.action;

import uo.ri.amp.busyness.AdminService;
import uo.ri.amp.conf.ServicesFactory;
import alb.util.console.Console;
import alb.util.menu.Action;

/**
 * The Class ListarNominasEmpleadoProfundidadAction.
 */
public class ListarNominasEmpleadoProfundidadAction implements Action{

	/* (non-Javadoc)
	 * @see alb.util.menu.Action#execute()
	 */
	@Override
	public void execute() throws Exception 
	{
		int idMecanico =  Console.readInt("Id de mecánico");
		AdminService as = ServicesFactory.getAdminService();
		as.listarNominasEmpleadoProfundidad(idMecanico);
		
	}

}
