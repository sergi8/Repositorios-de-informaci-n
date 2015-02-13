package uo.ri.amp.ui.admin;

import uo.ri.amp.ui.admin.action.EliminarNominaAction;
import uo.ri.amp.ui.admin.action.GeneraNominasAction;
import uo.ri.amp.ui.admin.action.ListarNominasEmpleadoAction;
import uo.ri.amp.ui.admin.action.ListarNominasEmpleadoProfundidadAction;
import alb.util.menu.BaseMenu;

/**
 * clase que muestra el menú de administración de nóminas.
 *
 * @author uo227806
 */
public class NominasMenu extends BaseMenu
{
	
	/**
	 * constructor sin parametro de la clase.
	 */
	public NominasMenu() 
	{
		menuOptions = new Object[][] { 
				{"Administrador > Gestión de nominas", null},
				
				{ "Generar nóminas", 	GeneraNominasAction.class }, 
				//se mostrará el mes y el total neto percibido
				{ "Listar nóminas de empleado", 	ListarNominasEmpleadoAction.class }, 
				//se mostrará el mes, total bruto, total neto y el desglose por conceptos.
				{ "Listar en profundidad nóminas de empleado", ListarNominasEmpleadoProfundidadAction.class },
				
				{ "Eliminar última nómina de empleado", EliminarNominaAction.class },
			};
	}

}
