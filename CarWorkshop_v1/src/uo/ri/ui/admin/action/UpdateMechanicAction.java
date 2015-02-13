package uo.ri.ui.admin.action;

import uo.ri.busyness.admin.UpdateMechanic;
import uo.ri.common.BusinessException;
import alb.util.console.Console;
import alb.util.menu.Action;

public class UpdateMechanicAction implements Action 
{

	@Override
	public void execute() throws BusinessException {
		
		// Pedir datos
		Long id = Console.readLong("Id del mecánico"); 
		String nombre = Console.readString("Nombre"); 
		String apellidos = Console.readString("Apellidos");
		UpdateMechanic updateMechanic = new UpdateMechanic(id, nombre, apellidos);
		updateMechanic.execute();
		
		// Mostrar resultado
		Console.println("Mecánico actualizado");
	}

}
