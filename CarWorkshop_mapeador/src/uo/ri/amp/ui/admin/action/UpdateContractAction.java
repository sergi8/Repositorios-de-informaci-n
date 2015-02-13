package uo.ri.amp.ui.admin.action;

import uo.ri.amp.business.AdminService;
import uo.ri.amp.model.Contrato;
import uo.ri.conf.ServicesFactory;
import alb.util.console.Console;
import alb.util.date.DateUtil;
import alb.util.menu.Action;

public class UpdateContractAction implements Action
{

	@Override
	public void execute() throws Exception 
	{
		Long idContrato = Console.readLong("Id del contrato a modificar");
		Long tipoContrato = Console.readLong("Id tipo contrato");
		Long categoria = Console.readLong("Id categoría");
		Double salarioBaseAnual = Console.readDouble("Salario base anual bruto");
		
		String fecha_fin="";
		if(tipoContrato == 3)
			fecha_fin = Console.readString("fecha de finalización del contrato");
		AdminService as = ServicesFactory.getAdminService();
		Contrato contrato = as.findContratoByID(idContrato);
		
		contrato.setSalarioBaseAnualBruto(salarioBaseAnual);
		if(fecha_fin!="")
			contrato.setFechaFin(DateUtil.fromString(fecha_fin));
		
		as.updateContract(contrato);
		
		Console.println("Contrato actualizado");
		
	}

}
