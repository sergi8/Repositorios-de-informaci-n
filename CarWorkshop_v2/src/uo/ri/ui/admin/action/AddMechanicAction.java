package uo.ri.ui.admin.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.busyness.AdminService;
import uo.ri.busyness.impl.AdminServiceImpl;
import uo.ri.common.BusinessException;

public class AddMechanicAction implements Action 
{
	
	@Override
	public void execute() throws BusinessException 
	{
		String nombre = Console.readString("Nombre"); 
		String apellidos = Console.readString("Apellidos");
		
		AdminService as = new AdminServiceImpl();
		as.addMechanic(nombre,apellidos);
		
		// Mostrar resultado
		Console.println("Nuevo mecánico añadido");
	}

}
