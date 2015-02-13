package uo.ri.amp.ui.admin;

import uo.ri.ui.admin.RepuestosMenu;
import uo.ri.ui.admin.TiposVehiculoMenu;
import alb.util.menu.BaseMenu;
import alb.util.menu.NotYetImplementedAction;

public class MainMenu extends BaseMenu {

	public MainMenu() {
		menuOptions = new Object[][] { 
			{ "Administrador", null },
			{ "Gesti�n de mec�nicos", 			MecanicosMenu.class }, 
			{ "Gesti�n de repuestos", 			RepuestosMenu.class },
			{ "Gesti�n de tipos de veh�culo", 	TiposVehiculoMenu.class },
			{ "Gesti�n de contratos",			ContratosMenu.class},
			{ "Gesti�n de n�minas",				NotYetImplementedAction.class},
			{ "Gestion de tipos de contrato",	NotYetImplementedAction.class},
		};
	}

	public static void main(String[] args) {
		new MainMenu().execute();
	}

}
