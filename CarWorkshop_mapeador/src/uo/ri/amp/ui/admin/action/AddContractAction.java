package uo.ri.amp.ui.admin.action;

import uo.ri.amp.business.AdminService;
import uo.ri.amp.model.Contrato;
import uo.ri.conf.ServicesFactory;
import uo.ri.model.exception.BusinessException;
import alb.util.console.Console;
import alb.util.date.DateUtil;
import alb.util.menu.Action;

public class AddContractAction implements Action
{

	@Override
	public void execute() throws BusinessException
	{
		Long idMecanico = Console.readLong("Id del mécanico");
		Long tipoContrato = Console.readLong("Id tipo contrato");
		Long categoria = Console.readLong("Id categoría");
		Double SalarioBaseAnual = Console.readDouble("Salario base anual bruto");
		
		String fecha_fin="";
		if(tipoContrato == 3)
			fecha_fin = Console.readString("fecha de finalización del contrato");
		
		AdminService as = ServicesFactory.getAdminService();
		Contrato contrato = new Contrato(as.findMechanicById(idMecanico), as.findTipoContratoByID(tipoContrato),as.findCategoriaByID(categoria));
		
		contrato.setSalarioBaseAnualBruto(SalarioBaseAnual);
		if(fecha_fin!="")
		contrato.setFechaFin(DateUtil.fromString(fecha_fin));
		as.newContract(contrato);
		
		Console.println("Nuevo Contrato adjudicado al mecánico "+idMecanico);
		
		
	}

}
