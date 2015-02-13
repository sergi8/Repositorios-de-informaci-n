package uo.ri.ui.cash.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import uo.ri.busyness.cash.FacturaReparaciones;
import uo.ri.common.BusinessException;
import alb.util.console.Console;
import alb.util.menu.Action;

public class FacturarReparacionesAction implements Action 
{
	
	@Override
	public void execute() throws BusinessException 
	{
		List<Long> idsAveria = new ArrayList<Long>();
		
		// pedir las averias a incluir en la factura
		do 
		{
			Long id = Console.readLong("ID de averia");
			idsAveria.add(id);
		} 
		while ( masAverias() );
		Map<String, Object> datos =	new FacturaReparaciones(idsAveria).execute();
		
		mostrarFactura(datos);
	}
	
	private boolean masAverias() 
	{
		return Console.readString("¿Añadir más averias? (s/n) ").equalsIgnoreCase("s");
	}

	private void mostrarFactura(Map<String, Object> datos) 
	{
		
		Console.printf("Factura nº: %d\n", datos.get("numeroFactura"));
		Console.printf("\tFecha: %1$td/%1$tm/%1$tY\n", datos.get("fechaFactura"));
		Console.printf("\tTotal: %.2f €\n", datos.get("totalFactura"));
		Console.printf("\tIva: %.1f %% \n", datos.get("iva"));
		Console.printf("\tTotal con IVA: %.2f €\n", datos.get("totalConIva"));
	}

}
