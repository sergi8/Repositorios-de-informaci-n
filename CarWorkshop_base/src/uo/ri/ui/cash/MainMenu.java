package uo.ri.ui.cash;

import alb.util.menu.BaseMenu;
import alb.util.menu.NotYetImplementedAction;

public class MainMenu extends BaseMenu {

	public MainMenu() {
		menuOptions = new Object[][] { 
			{ "Caja de Taller", null },
			{ "Buscar reparación por matrícula", NotYetImplementedAction.class }, 
			{ "Facturar reparación", NotYetImplementedAction.class },
		};
	}

	public static void main(String[] args) {
		new MainMenu().execute();
	}

}
