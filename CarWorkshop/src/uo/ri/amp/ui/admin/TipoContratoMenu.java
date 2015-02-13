package uo.ri.amp.ui.admin;

import uo.ri.amp.ui.admin.action.DeleteContractTypeAction;
import uo.ri.amp.ui.admin.action.AddContractTypeAction;
import uo.ri.amp.ui.admin.action.ListMechanicsByContractTypeAction;
import uo.ri.amp.ui.admin.action.UpdateContractTypeAction;
import alb.util.menu.BaseMenu;
// TODO: Auto-generated Javadoc

/**
 * clase que muestra el menú de administracion de los tipos de contrato.
 *
 * @author uo227806
 */
public class TipoContratoMenu extends BaseMenu
{
	
	/**
	 * constructor sin parámetros de la clase.
	 */
	public TipoContratoMenu() 
	{
		menuOptions = new Object[][] 
			{ 
				{"Administrador > Gestión de tipos de contrato", null},
				//pedirá nombre del tipo de contrato, número de días de indemnización
				{ "añadir nuevo Tipo de contrato", 	AddContractTypeAction.class }, 
				//Al final del listado aparecerá un acumulado de salario bruto de los trabajadores de ese tipo de contrato y el número de trabajadores.
				{ "Listar trabajadores por tipo contrato", ListMechanicsByContractTypeAction.class },
				//no se podrán borrar aquellos tipos que tengan contratos asociados
				{ "Eliminar TipoContrato", DeleteContractTypeAction.class },
				//no se podrán modificar los datos que definan la identidad del tipo de contrato
				{ "Modificar tipo de contrato", UpdateContractTypeAction.class },
			};
	}
}
