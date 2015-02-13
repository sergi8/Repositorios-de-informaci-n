package uo.ri.amp.ui.admin.action;

import java.util.List;

import uo.ri.amp.business.AdminService;
import uo.ri.amp.ui.util.Printer;
import uo.ri.conf.ServicesFactory;
import uo.ri.model.Mecanico;
import uo.ri.model.exception.BusinessException;
import alb.util.console.Console;
import alb.util.menu.Action;

public class ListMechanicsAction implements Action 
{

	@Override
	public void execute() throws BusinessException 
	{
	
		AdminService as = ServicesFactory.getAdminService();
		List<Mecanico> mechanics = as.findMechanics();
		
		Console.println("\nListado de mecánicos\n");
		
		for(Mecanico m : mechanics)
			Printer.printMechanic( m );
		
	}
}
