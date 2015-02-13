package uo.ri.persistence;

import java.util.List;

import uo.ri.model.MedioPago;
import uo.ri.persistence.util.Jpa;

public class MedioPagoFinder {

	@SuppressWarnings("unchecked")
	public static List<MedioPago> findPaymentMeansByInvoiceId(Long idFactura) {
		return Jpa.getManager().createNamedQuery("MedioPago.findByInvoiceId").setParameter(1, idFactura).getResultList();
	}

	public static MedioPago findById(Long idMedio) {
		return Jpa.getManager().find(MedioPago.class, idMedio);
	}

}
