package uo.ri.amp.ui.admin.action;

import uo.ri.amp.busyness.AdminService;
import uo.ri.amp.conf.ServicesFactory;
import alb.util.console.Console;
import alb.util.menu.Action;

// TODO: Auto-generated Javadoc
/**
 * The Class UpdateContractTypeAction.
 */
public class UpdateContractTypeAction implements Action
{

	/* (non-Javadoc)
	 * @see alb.util.menu.Action#execute()
	 */
	@Override
	public void execute() throws Exception 
	{
		AdminService as = ServicesFactory.getAdminService();
		as.listTiposContrato();
		Long tipoContrato =Console.readLong("Tipo de contrato a modificar");
		String nombre = Console.readString("Nuevo nombre");
		int diasIndemnizacion = Console.readInt("nº de días de indemnización");
		as.updateContarctType(tipoContrato, nombre, diasIndemnizacion);
		Console.println("Tipo de contrano actualizado.");
		
	}

}
