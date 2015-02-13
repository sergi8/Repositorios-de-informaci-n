package uo.ri.amp.ui.admin.action;

import java.util.List;

import uo.ri.amp.business.AdminService;
import uo.ri.amp.model.Contrato;
import uo.ri.amp.ui.util.Printer;
import uo.ri.conf.ServicesFactory;
import alb.util.console.Console;
import alb.util.menu.Action;

public class ListContractsMechanicAction implements Action
{

	@Override
	public void execute() throws Exception 
	{
		Long idMecanico = Console.readLong("Id del mécanico");
		AdminService as = ServicesFactory.getAdminService();
		List<Contrato> contratos = as.findAllcontractsFromMechanic(idMecanico);
		
		Console.println("Contratosd del mecánico "+ idMecanico);
		
		for(Contrato c : contratos)
			Printer.printContract(c);
		
	}

}
