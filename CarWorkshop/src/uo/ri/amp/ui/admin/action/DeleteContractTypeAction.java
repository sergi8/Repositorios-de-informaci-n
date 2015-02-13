package uo.ri.amp.ui.admin.action;

import uo.ri.amp.busyness.AdminService;
import uo.ri.amp.conf.ServicesFactory;
import alb.util.console.Console;
import alb.util.menu.Action;

// TODO: Auto-generated Javadoc
/**
 * The Class DeleteContractTypeAction.
 */
public class DeleteContractTypeAction implements Action
{

	/* (non-Javadoc)
	 * @see alb.util.menu.Action#execute()
	 */
	@Override
	public void execute() throws Exception 
	{
		AdminService as = ServicesFactory.getAdminService();
		as.listTiposContrato();
		Long tipoContrato =Console.readLong("tipo de contrato");
		as.deleteContractType(tipoContrato);
		Console.println("Tipo de contrato eliminado.");
		
	}

}
