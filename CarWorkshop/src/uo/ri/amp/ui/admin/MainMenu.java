package uo.ri.amp.ui.admin;

import uo.ri.ui.admin.RepuestosMenu;
import uo.ri.ui.admin.TiposVehiculoMenu;
import alb.util.menu.BaseMenu;
import alb.util.menu.NotYetImplementedAction;

/**
 * clase que muestra el menú principal del administrador.
 *
 * @author uo227806
 */
public class MainMenu extends BaseMenu 
{
	
	/**
	 * constructor sin parámetros de la clase.
	 */
	public MainMenu() {
		menuOptions = new Object[][] { 
			{ "Administrador", null },
			{ "Gestión de mecánicos", 			MecanicosMenu.class },
			{ "Gestión de contratos", 			ContratosMenu.class },
			{ "Gestión de nóminas", 			NominasMenu.class },
			{ "Gestión de tipos de contrato", 	TipoContratoMenu.class },
			
			//no implementado, mi UO termina en un número par
			{ "Gestión de categorias", 			NotYetImplementedAction.class },
			/*gestion de repuestos y de tipos de vehículos no entran dentro de la 
			 * ampliación, por lo que están implementadas en el paquete 
			 * uo.ri.ui.admin*/
			{ "Gestión de repuestos", 			RepuestosMenu.class },
			{ "Gestión de tipos de vehículo", 	TiposVehiculoMenu.class },
		};
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		new MainMenu().execute();
	}

}
