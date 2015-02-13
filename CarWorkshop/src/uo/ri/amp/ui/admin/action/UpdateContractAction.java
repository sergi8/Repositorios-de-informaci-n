package uo.ri.amp.ui.admin.action;

import uo.ri.amp.busyness.AdminService;
import uo.ri.amp.conf.ServicesFactory;
import uo.ri.common.BusinessException;
import alb.util.console.Console;
import alb.util.menu.Action;

// TODO: Auto-generated Javadoc
/**
 * The Class UpdateContractAction.
 */
public class UpdateContractAction implements Action
{

	/* (non-Javadoc)
	 * @see alb.util.menu.Action#execute()
	 */
	@Override
	public void execute() throws BusinessException 
	{
		AdminService as = ServicesFactory.getAdminService();
		String fechaFin=null;
		
		// id del contrato que se pretend modificar
		int id_contrato =Console.readInt("id del contrato a modificar");
		System.out.println("\n datos actuales del contrato seleccionado:\n");
		as.showContract(id_contrato);
		System.out.println("---------------------------------------\n");
		
		//INDEFINIDO OBRA TEMPORALES(fecha de fin conocida)
		String tipoContrato = Console.readString("tipo de contrato").toUpperCase();
		String categoria = Console.readString("categoría").toUpperCase();
		double salarioBase = Console.readDouble("Salario base");
		String fechaContrato = Console.readString("fecha de inicio del contrato");
		if(tipoContrato.equals("TEMPORAL"))
				fechaFin = Console.readString("fecha de finalización del contrato");
		as.UpdateContract(id_contrato, tipoContrato, categoria, salarioBase, fechaContrato, fechaFin);
		Console.println("Contrato actualizado");
		
	}

}
