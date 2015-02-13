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
	 * contructor sin par�metros de la clase
	 */
	public MecanicosMenu() 
	{
		menuOptions = new Object[][] { 
			{"Administrador > Gesti�n de mec�nicos", null},
			
			{ "A�adir mec�nico", 				AddMechanicAction.class }, 
			{ "Modificar datos de mec�nico", 	UpdateMechanicAction.class },
			//S�lo se podr� borrarsi no tiene aver�as, intervenciones asignadas, ni contratos, ni ha cobrado n�minas
			{ "Eliminar mec�nico", 				DeleteMechanicAction.class },
			//s�lo saldr�n los que tienen contrato en vigor.
			{ "Listar mec�nicos", 				ListMechanicsAction.class },
			{ "Listar todos los mec�nicos", 	ListAllMechanicsAction.class },
			
		};
	}
}
