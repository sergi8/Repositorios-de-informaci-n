package uo.ri.ui.util;

import java.util.List;

import uo.ri.model.Averia;
import uo.ri.model.Factura;
import uo.ri.model.Mecanico;
import uo.ri.model.MedioPago;
import alb.util.console.Console;

public class Printer {

	public static void printInvoice(Factura invoice) {

		double importeConIVa = invoice.getImporte();
		double iva =  (Double) invoice.getIva();
		double importeSinIva = importeConIVa / (1 + iva / 100);

		Console.printf("Factura nº: %d\n", 				invoice.getNumero() );
		Console.printf("\tFecha: %1$td/%1$tm/%1$tY\n", 	invoice.getFecha());
		Console.printf("\tTotal: %.2f €\n", 			importeSinIva);
		Console.printf("\tIva: %.1f %% \n", 			invoice.getIva() );
		Console.printf("\tTotal con IVA: %.2f €\n", 	invoice.getImporte() );
		Console.printf("\tEstado: %s\n", 				invoice.getStatus() );
	}

	public static void printPaymentMeans(List<MedioPago> medios) {
		Console.println();
		Console.println("Medios de pago disponibles");
		
		Console.printf("\t%s \t%-8.8s \t%s \n", "ID", "Tipo", "Acumulado");
		for (MedioPago medio : medios) {
			Console.println( medio.toFormatedString() );
		}
	}

	public static void printRepairing(Averia rep) {
		
		Console.printf("\t%d \t%-40.40s \t%td/%<tm/%<tY \t%-12.12s \t%.2f\n",  
				rep.getId()
				, rep.getDescripcion() 
				, rep.getFecha()
				, rep.getStatus()
				, rep.getImporte()
		);
	}

	public static void printMechanic(Mecanico m) {

		Console.printf("\t%d %-10.10s %-25.25s %-25.25s\n",  
				m.getId()
				, m.getDni()
				, m.getNombre()
				, m.getApellidos()
			);
	}

}
