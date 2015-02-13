package uo.ri.ui.admin.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.amp.busyness.AdminService;
import uo.ri.common.BusinessException;
import uo.ri.amp.conf.ServicesFactory;

public class AddMechanicAction implements Action 
{
	
	@Override
	public void execute() throws BusinessException 
	{
		String nombre = Console.readString("Nombre"); 
		String apellidos = Console.readString("Apellidos");
		
		AdminService as = ServicesFactory.getAdminService();
		as.addMechanic(nombre,apellidos);
		
		// Mostrar resultado
		Console.println("Nuevo mecánico añadido");
	}

}
