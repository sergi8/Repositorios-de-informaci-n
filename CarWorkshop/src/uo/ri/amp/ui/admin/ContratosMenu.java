package uo.ri.amp.ui.admin;

import uo.ri.amp.ui.admin.action.AddContractAction;
import uo.ri.amp.ui.admin.action.DeleteContractAction;
import uo.ri.amp.ui.admin.action.ExtinguirContratoAction;
import uo.ri.amp.ui.admin.action.FinalizarContratoAction;
import uo.ri.amp.ui.admin.action.ListContractsMechanicAction;
import uo.ri.amp.ui.admin.action.UpdateContractAction;
import alb.util.menu.BaseMenu;

/**
 * clase que muestra el menu de administración de contratos.
 *
 * @author uo227806
 */
public class ContratosMenu extends BaseMenu
{
	
	/**
	 * constructor sin parámetros de la clase.
	 */
	public ContratosMenu() 
	{
		menuOptions = new Object[][] { 
				{"Administrador > Gestión de contratos", null},
				//Si el mecánico ya tenía un contrato en vigor, el anterior pasa a estar EXTINTO y el nuevo en vigor
				{ "Añadir contrato", 				AddContractAction.class }, 
				{ "Modificar datos del contrato", 	UpdateContractAction.class },
				//solo se podrá eliminar si el mecánico no ha realizado ninguna actividad durante su vigencia.
				{ "Eliminar contrato", 				DeleteContractAction.class },
				//se debe reflejar claramente cuál es el que está en vigor
				{ "Listar contratos de un mecánico", ListContractsMechanicAction.class },
				//finaliza un contrato
				{ "Finalizar contrato",				FinalizarContratoAction.class},
				//establecerá la fecha de extinción al final del mes en curso y le calculará el importe de la liquidación.
				{ "Extinguir contrato",				ExtinguirContratoAction.class}
			};
	}
}
