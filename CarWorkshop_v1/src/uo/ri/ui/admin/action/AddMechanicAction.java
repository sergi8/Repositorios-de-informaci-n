package uo.ri.ui.admin.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.busyness.admin.AddMechanic;
import uo.ri.common.BusinessException;

public class AddMechanicAction implements Action 
{
	
	@Override
	public void execute() throws BusinessException 
	{
		String nombre = Console.readString("Nombre"); 
		String apellidos = Console.readString("Apellidos");
		
		AddMechanic admechanic = new AddMechanic(nombre, apellidos);
		admechanic.execute();
		// Mostrar resultado
		Console.println("Nuevo mecánico añadido");
	}

}
