package uo.ri.amp.ui.admin.action;

import uo.ri.amp.busyness.AdminService;
import uo.ri.amp.conf.ServicesFactory;
import uo.ri.common.BusinessException;
import alb.util.console.Console;
import alb.util.menu.Action;

// TODO: Auto-generated Javadoc
/**
 * The Class ListAllMechanicsAction.
 */
public class ListAllMechanicsAction implements Action
{

	/**
	 * metodo de Action de la capa de presentacion que en este caso se encarga de mandarle a adminservice 
	 * la petición de listar todo los mecánicos independientemente de si tienen contraton activo o no.
	 * interactua directamente con el usuario
	 *
	 * @throws BusinessException the business exception
	 */
	@Override
	public void execute() throws BusinessException
	{
		Console.println("\nListado de mecánicos\n");
		
		AdminService as = ServicesFactory.getAdminService();
		as.ListAllMechanics();
		
	}

}
