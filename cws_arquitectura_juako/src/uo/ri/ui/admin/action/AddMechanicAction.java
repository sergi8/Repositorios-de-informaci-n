package uo.ri.ui.admin.action;

import uo.ri.business.AdminService;
import uo.ri.conf.ServicesFactory;
import uo.ri.model.Mecanico;
import uo.ri.model.exception.BusinessException;
import alb.util.console.Console;
import alb.util.menu.Action;

public class AddMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {
		
		// Pedir datos
		String dni = Console.readString("Dni"); 
		String nombre = Console.readString("Nombre"); 
		String apellidos = Console.readString("Apellidos");
		
		// Procesar
		AdminService as = ServicesFactory.getAdminService();
		as.newMechanic( new Mecanico(dni, nombre, apellidos) );
				
		// Mostrar resultado
		Console.println("Nuevo mecánico añadido");
	}

}
