package uo.ri.persistence;

import uo.ri.model.Factura;
import uo.ri.persistence.util.Jpa;


public class FacturaFinder {

	public static Factura findByNumber(Long numero) {
		return (Factura) Jpa.getManager().createNamedQuery("Factura.findByNumber").setParameter(1, numero).getSingleResult();
	}

	public static Factura findById(Long id) {
		return Jpa.getManager().find(Factura.class, id);
	}

	public static Long getNextInvoiceNumber() {
		return (Long) Jpa.getManager().createNamedQuery("Factura.getNextInvoiceNumber").getSingleResult();
	}

}
