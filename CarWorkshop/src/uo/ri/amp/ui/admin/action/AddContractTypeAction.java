package uo.ri.amp.ui.admin.action;

import uo.ri.amp.busyness.AdminService;
import uo.ri.amp.conf.ServicesFactory;
import alb.util.console.Console;
import alb.util.menu.Action;

// TODO: Auto-generated Javadoc
/**
 * The Class AddContractTypeAction.
 */
public class AddContractTypeAction implements Action
{

	/* (non-Javadoc)
	 * @see alb.util.menu.Action#execute()
	 */
	@Override
	public void execute() throws Exception 
	{
		String tipoContrato =Console.readString("nuevo tipo de contrato");
		int diasIndemnizacion = Console.readInt("nº de días de indemnización");
		
		AdminService as = ServicesFactory.getAdminService();
		as.addTipoContrato(tipoContrato, diasIndemnizacion);
		Console.println("nuevo Tipo de contrano añadido.");
		
	}

}
