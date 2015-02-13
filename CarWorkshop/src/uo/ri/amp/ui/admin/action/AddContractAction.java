package uo.ri.amp.ui.admin.action;

import uo.ri.amp.busyness.AdminService;
import uo.ri.amp.conf.ServicesFactory;
import uo.ri.common.BusinessException;
import alb.util.console.Console;
import alb.util.menu.Action;

// TODO: Auto-generated Javadoc
/**
 * The Class AddContractAction.
 */
public class AddContractAction implements Action
{

	/* (non-Javadoc)
	 * @see alb.util.menu.Action#execute()
	 */
	@Override
	public void execute() throws BusinessException 
	{
		AdminService as = ServicesFactory.getAdminService();
		String fechaFin=null;
		
		int idMecanico =Console.readInt("id de mecánico");
		//INDEFINIDO OBRA TEMPORALES(fecha de fin conocida)
		Console.println("\nTipos de contrato existentes: \n");
		as.lisTiposContrato();
		
		String tipoContrato = Console.readString("tipo de contrato").toUpperCase();
		Console.println("\nCategorias existentes: \n");
		as.listcategorias();
		String categoria = Console.readString("categoría").toUpperCase();
		double salarioBase = Console.readDouble("Salario base");
		String fechaContrato = Console.readString("fecha de inicio del contrato (formato fecha YYYY-MM-DD)");
		if(tipoContrato.equals("TEMPORAL"))
				fechaFin = Console.readString("fecha de finalización del contrato (formato fecha YYYY-MM-DD)");
		as.addContract(idMecanico, tipoContrato, categoria, salarioBase, fechaContrato, fechaFin);
		Console.println("nuevo contrato añadido al mécánico "+idMecanico+".");
	}

}
