package uo.ri.amp.ui.admin;

import java.text.SimpleDateFormat;
import java.util.Date;

import uo.ri.amp.ui.admin.action.AddContractAction;
import uo.ri.amp.ui.admin.action.DeleteContractAction;
import uo.ri.amp.ui.admin.action.ExtinguirContratoAction;
import uo.ri.amp.ui.admin.action.FinalizarContratoAction;
import uo.ri.amp.ui.admin.action.ListContractsMechanicAction;
import uo.ri.amp.ui.admin.action.ListMechanicsAction;
import uo.ri.amp.ui.admin.action.UpdateContractAction;
import uo.ri.ui.admin.action.AddMechanicAction;
import uo.ri.ui.admin.action.DeleteMechanicAction;
import uo.ri.ui.admin.action.UpdateMechanicAction;
import alb.util.menu.BaseMenu;

public class ContratosMenu extends BaseMenu
{
	public ContratosMenu() 
	{
		
		menuOptions = new Object[][] { 
				{"Administrador > Gesti�n de contratos", null},
				//Si el mec�nico ya ten�a un contrato en vigor, el anterior pasa a estar EXTINTO y el nuevo en vigor
				{ "A�adir contrato", 				AddContractAction.class }, 
				{ "Modificar datos del contrato", 	UpdateContractAction.class },
				//solo se podr� eliminar si el mec�nico no ha realizado ninguna actividad durante su vigencia.
				{ "Eliminar contrato", 				DeleteContractAction.class },
				//se debe reflejar claramente cu�l es el que est� en vigor
				{ "Listar contratos de un mec�nico", ListContractsMechanicAction.class },
				//finaliza un contrato
				{ "Finalizar contrato",				FinalizarContratoAction.class},
				//establecer� la fecha de extinci�n al final del mes en curso y le calcular� el importe de la liquidaci�n.
				{ "Extinguir contrato",				ExtinguirContratoAction.class}
			};
	}
}
