package uo.ri.amp.ui.admin.action;

import java.util.List;

import uo.ri.amp.business.AdminService;
import uo.ri.amp.ui.util.Printer;
import uo.ri.conf.ServicesFactory;
import uo.ri.model.Mecanico;
import alb.util.console.Console;
import alb.util.menu.Action;

public class ListAllMechanicsAction implements Action
{

	@Override
	public void execute() throws Exception 
	{
		
		AdminService as = ServicesFactory.getAdminService();
		List<Mecanico> mechanics = as.findAllMechanics();
		
		Console.println("\nListado de mecánicos\n");
		
		for(Mecanico m : mechanics)
			Printer.printMechanic( m );
		
	}

}
