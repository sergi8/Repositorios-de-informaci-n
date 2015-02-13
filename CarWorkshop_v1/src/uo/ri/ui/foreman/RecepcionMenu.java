package uo.ri.ui.foreman;

import alb.util.menu.BaseMenu;
import alb.util.menu.NotYetImplementedAction;
import uo.ri.ui.foreman.action.AsignarAveriaAction;
import uo.ri.ui.foreman.action.EliminarAveriaAction;
import uo.ri.ui.foreman.action.ListarAveriasAction;
import uo.ri.ui.foreman.action.ListarMecanicosAction;
import uo.ri.ui.foreman.action.ModificarAveriaAction;
import uo.ri.ui.foreman.action.RegistrarAveriaAction;

public class RecepcionMenu extends BaseMenu {

	public RecepcionMenu() 
	{
		menuOptions = new Object[][] { 
			{"Jefe de Taller > Recepción en taller", null},
			
			{"Registrar avería", 		RegistrarAveriaAction.class }, 
			{"Modificar averia", 		ModificarAveriaAction.class },
			{"Eliminar una averia", 	EliminarAveriaAction.class },
			{"", null},
			{"Listar averías", 			ListarAveriasAction.class }, 
			{"Ver una avería", 			NotYetImplementedAction.class },
			{"", null},
			{"Listar mecánicos", 		ListarMecanicosAction.class }, 
			{"Asignar una avería",  	AsignarAveriaAction.class },
		};
	}

}
