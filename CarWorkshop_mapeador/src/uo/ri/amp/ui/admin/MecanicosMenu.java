package uo.ri.amp.ui.admin;

import uo.ri.amp.ui.admin.action.ListAllMechanicsAction;
import uo.ri.amp.ui.admin.action.ListMechanicsAction;
import uo.ri.ui.admin.action.AddMechanicAction;
import uo.ri.ui.admin.action.DeleteMechanicAction;
import uo.ri.ui.admin.action.UpdateMechanicAction;
import alb.util.menu.BaseMenu;

public class MecanicosMenu extends BaseMenu 
{

	/**
	 * contructor sin parámetros de la clase
	 */
	public MecanicosMenu() 
	{
		menuOptions = new Object[][] { 
			{"Administrador > Gestión de mecánicos", null},
			
			{ "Añadir mecánico", 				AddMechanicAction.class }, 
			{ "Modificar datos de mecánico", 	UpdateMechanicAction.class },
			//Sólo se podrá borrarsi no tiene averías, intervenciones asignadas, ni contratos, ni ha cobrado nóminas
			{ "Eliminar mecánico", 				DeleteMechanicAction.class },
			//sólo saldrán los que tienen contrato en vigor.
			{ "Listar mecánicos", 				ListMechanicsAction.class },
			{ "Listar todos los mecánicos", 	ListAllMechanicsAction.class },
			
		};
	}
}
