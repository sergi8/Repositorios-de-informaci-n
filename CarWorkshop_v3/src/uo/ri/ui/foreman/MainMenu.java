package uo.ri.ui.foreman;

import uo.ri.ui.foreman.action.RevisarHistorialClienteAction;
import alb.util.menu.BaseMenu;

public class MainMenu extends BaseMenu {

	public MainMenu() {
		menuOptions = new Object[][] { 
			{ "Jefe de Taller", null },
			{ "Recepción en taller", RecepcionMenu.class }, 
			{ "Gestión de clientes", ClientesMenu.class },
			{ "Gestión de vehículos", VehiculosMenu.class },
			{ "Revisar historial de un cliente", RevisarHistorialClienteAction.class }, 
		};
	}

	public static void main(String[] args) {
		new MainMenu().execute();
	}

}
