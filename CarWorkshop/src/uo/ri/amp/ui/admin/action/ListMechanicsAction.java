package uo.ri.amp.ui.admin.action;

import uo.ri.amp.busyness.AdminService;
import uo.ri.common.BusinessException;
import uo.ri.amp.conf.ServicesFactory;
import alb.util.console.Console;
import alb.util.menu.Action;
// TODO: Auto-generated Javadoc
/**
 * 
 * @author uo227806
 *
 */
public class ListMechanicsAction implements Action 
{
	/**
	 * metodo de Action de la capa de presentacion que en este caso se encarga de mandarle a adminservice 
	 * la petición de listar los mecánicos 
	 * interactua directamente con el usuario
	 */
	@Override
	public void execute() throws BusinessException 
	{
		Console.println("\nListado de mecánicos\n");
		
		AdminService as = ServicesFactory.getAdminService();
		as.listMechanic();
	}
}
