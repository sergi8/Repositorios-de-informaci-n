package uo.ri.amp.ui.admin.action;

import uo.ri.amp.busyness.AdminService;
import uo.ri.amp.conf.ServicesFactory;
import alb.util.menu.Action;

/**
 * The Class ListCategoriasAction.
 */
public class ListCategoriasAction implements Action
{

	/* (non-Javadoc)
	 * @see alb.util.menu.Action#execute()
	 */
	@Override
	public void execute() throws Exception 
	{
		AdminService as = ServicesFactory.getAdminService();
		as.listcategorias();
	}

}
