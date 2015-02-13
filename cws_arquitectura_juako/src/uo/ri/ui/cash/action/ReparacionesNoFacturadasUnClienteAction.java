package uo.ri.ui.cash.action;

import java.util.List;

import uo.ri.business.CashService;
import uo.ri.conf.ServicesFactory;
import uo.ri.model.Averia;
import uo.ri.model.exception.BusinessException;
import uo.ri.ui.util.Printer;
import alb.util.console.Console;
import alb.util.menu.Action;

public class ReparacionesNoFacturadasUnClienteAction implements Action {

	@Override
	public void execute() throws BusinessException {
		CashService cs = ServicesFactory.getCashService();

		String dni = Console.readString("DNI de cliente");
		
		Console.println("\nReparaciones no facturadas del cliente\n");  
		
		List<Averia> reps = cs.findRepairsByClient( dni ); 
		
		if (reps.size() == 0) {
			Console.printf("No tiene reparaciones pendientes\n");
			return;
		}
		
		for(Averia rep : reps) {
			Printer.printRepairing( rep );
		}
	}

}
