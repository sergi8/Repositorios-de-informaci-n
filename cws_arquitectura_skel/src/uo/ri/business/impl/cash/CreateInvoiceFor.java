package uo.ri.business.impl.cash;

import java.util.List;

import uo.ri.model.Factura;
import uo.ri.model.exception.BusinessException;

public class CreateInvoiceFor {

	private List<Long> idsAveria;

	public CreateInvoiceFor(List<Long> idsAveria) {
		this.idsAveria = idsAveria;
	}

	public Factura execute() throws BusinessException {

		return null;
	}

}
