package uo.ri.amp.ui.admin;

import uo.ri.amp.ui.admin.action.EliminarTipoContratoAction;
import uo.ri.amp.ui.admin.action.InsertarTipoContratoAction;
import uo.ri.amp.ui.admin.action.ListarTrabajadoresTipoContratoAction;
import alb.util.menu.BaseMenu;
/**
 * clase que muestra el menú de administracion de los tipos de contrato
 * @author uo227806
 *
 */
public class TipoContratoMenu extends BaseMenu
{
	/**
	 * constructor sin parámetros de la clase
	 */
	public TipoContratoMenu() 
	{
		menuOptions = new Object[][] 
			{ 
				{"Administrador > Gestión de tipos de contrato", null},
				//pedirá nombre del tipo de contrato, número de días de indemnización
				{ "insertarTipo de contrato", 	InsertarTipoContratoAction.class }, 
				//Al final del listado aparecerá un acumulado de salario bruto de los trabajadores de ese tipo de contrato y el número de trabajadores.
				{ "Listar trabajadores por tipo contrato", ListarTrabajadoresTipoContratoAction.class },
				//no se podrán borrar aquellos tipos que tengan contratos asociados
				{ "Eliminar TipoContrato", EliminarTipoContratoAction.class },
				//no se podrán modificar los datos que definan la identidad del tipo de contrato
				{ "Modificar tipo de contrato", EliminarTipoContratoAction.class },
			};
	}
}
