package uo.ri.persistence;

import uo.ri.model.Factura;
import uo.ri.persistence.util.Jpa;

public class FacturaFinder {

	public static Factura findByNumber(Long numero) {

		return null;
	}

	public static Factura findById(Long id) {

		return null;
	}

	public static Long getNextInvoiceNumber() {
		return Jpa.getManager()
				.createNamedQuery("Factura.getNextInvoiceNumber", Long.class)
				.getSingleResult();
	}

}
