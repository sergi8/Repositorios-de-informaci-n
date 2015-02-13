package uo.ri.amp.ui.admin;

import uo.ri.ui.admin.RepuestosMenu;
import uo.ri.ui.admin.TiposVehiculoMenu;
import alb.util.menu.BaseMenu;
import alb.util.menu.NotYetImplementedAction;

public class MainMenu extends BaseMenu {

	public MainMenu() {
		menuOptions = new Object[][] { 
			{ "Administrador", null },
			{ "Gestión de mecánicos", 			MecanicosMenu.class }, 
			{ "Gestión de repuestos", 			RepuestosMenu.class },
			{ "Gestión de tipos de vehículo", 	TiposVehiculoMenu.class },
			{ "Gestión de contratos",			ContratosMenu.class},
			{ "Gestión de nóminas",				NotYetImplementedAction.class},
			{ "Gestion de tipos de contrato",	NotYetImplementedAction.class},
		};
	}

	public static void main(String[] args) {
		new MainMenu().execute();
	}

}
