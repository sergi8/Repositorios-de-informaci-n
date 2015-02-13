package uo.ri.ui.foreman;

import uo.ri.ui.foreman.action.ModificarAveriaAction;
import uo.ri.ui.foreman.action.RegistrarAveriaAction;
import alb.util.menu.BaseMenu;
import alb.util.menu.NotYetImplementedAction;

public class RecepcionMenu extends BaseMenu {

	public RecepcionMenu() {
		menuOptions = new Object[][] { 
			{"Jefe de Taller > Recepción en taller", null},
			
			{"Registrar avería", 		RegistrarAveriaAction.class }, 
			{"Modificar averia", 		ModificarAveriaAction.class },
			{"Eliminar una averia", 	NotYetImplementedAction.class },
			{"", null},
			{"Listar averías", 			NotYetImplementedAction.class }, 
			{"Ver una avería", 			NotYetImplementedAction.class },
			{"", null},
			{"Listar mecánicos", 		NotYetImplementedAction.class }, 
			{"Asignar una avería",  	NotYetImplementedAction.class },
		};
	}

}
