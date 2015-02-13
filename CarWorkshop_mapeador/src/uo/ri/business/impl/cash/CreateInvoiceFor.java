package uo.ri.business.impl.cash;

import java.util.List;

import uo.ri.model.Averia;
import uo.ri.model.Factura;
import uo.ri.model.exception.BusinessException;
import uo.ri.persistence.AveriaFinder;
import uo.ri.persistence.FacturaFinder;
import uo.ri.persistence.util.Jpa;

public class CreateInvoiceFor 
{

	private List<Long> idsAveria;

	public CreateInvoiceFor(List<Long> idsAveria) 
	{
		this.idsAveria = idsAveria;
	}

	public Factura execute() throws BusinessException 
	{

		List<Averia> averias = AveriaFinder.findByIds(idsAveria);
		
		Long numFactura = FacturaFinder.getNextInvoiceNumber();
		
		Factura f = new Factura(numFactura, averias);
		
		Jpa.getManager().persist(f);

		return null;
	}

}
