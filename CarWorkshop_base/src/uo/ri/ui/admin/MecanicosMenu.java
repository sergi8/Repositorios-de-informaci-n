package uo.ri.ui.admin;

import alb.util.menu.BaseMenu;
import alb.util.menu.NotYetImplementedAction;

public class MecanicosMenu extends BaseMenu {

	public MecanicosMenu() {
		menuOptions = new Object[][] { 
			{"Administrador > Gestión de mecánicos", null},
			
			{ "Añadir mecánico", 				NotYetImplementedAction.class }, 
			{ "Modificar datos de mecánico", 	NotYetImplementedAction.class }, 
			{ "Eliminar mecánico", 				NotYetImplementedAction.class }, 
			{ "Listar mecánicos", 				NotYetImplementedAction.class },
		};
	}

}
