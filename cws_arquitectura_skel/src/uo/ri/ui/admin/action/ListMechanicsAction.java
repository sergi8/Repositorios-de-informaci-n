package uo.ri.ui.admin.action;

import java.util.List;

import uo.ri.business.AdminService;
import uo.ri.conf.ServicesFactory;
import uo.ri.model.Mecanico;
import uo.ri.model.exception.BusinessException;
import uo.ri.ui.util.Printer;
import alb.util.console.Console;
import alb.util.menu.Action;

public class ListMechanicsAction implements Action {

	@Override
	public void execute() throws BusinessException {
	
		AdminService as = ServicesFactory.getAdminService();
		List<Mecanico> mechanics = as.findAllMechanics();
		
		Console.println("\nListado de mecánicos\n");  
		for(Mecanico m : mechanics) {
			Printer.printMechanic( m );
		}

	}
}
