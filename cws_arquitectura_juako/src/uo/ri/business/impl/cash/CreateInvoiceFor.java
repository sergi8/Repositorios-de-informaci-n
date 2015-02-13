package uo.ri.business.impl.cash;

import java.util.List;

import uo.ri.business.impl.Command;
import uo.ri.model.Averia;
import uo.ri.model.Factura;
import uo.ri.model.exception.BusinessException;
import uo.ri.persistence.AveriaFinder;
import uo.ri.persistence.FacturaFinder;
import uo.ri.persistence.util.Jpa;

public class CreateInvoiceFor implements Command{

	private List<Long> idsAveria;

	public CreateInvoiceFor(List<Long> idsAveria) {
		this.idsAveria = idsAveria;
	}

	public Factura execute() throws BusinessException {
		Long numero = FacturaFinder.getNextInvoiceNumber();
		List<Averia> averias = AveriaFinder.findByIds(idsAveria);
		Factura f = new Factura(numero, averias);
		Jpa.getManager().persist(f);
		return f;
	}

}
