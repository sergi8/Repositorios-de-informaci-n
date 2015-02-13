package uo.ri.ui.cash;

import uo.ri.ui.cash.action.*;
import alb.util.menu.BaseMenu;

public class MainMenu extends BaseMenu {

	public MainMenu() {
		menuOptions = new Object[][] { 
			{ "Caja de Taller", null },
			{ "Buscar reparaciones no facturadas de un cliente", ReparacionesNoFacturadasUnClenteAction.class }, 
			{ "Buscar reparación por matrícula", 	BuscarReparacionPorMatriculaAction.class }, 
			{ "Facturar reparaciones", 				FacturarReparacionesAction.class },
			{ "Liquidar factura ", 					LiquidarFacturaAction.class },
		};
	}

	public static void main(String[] args) {
		new MainMenu().execute();
	}

}
