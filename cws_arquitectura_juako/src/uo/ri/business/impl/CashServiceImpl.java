package uo.ri.business.impl;

import java.util.List;
import java.util.Map;

import uo.ri.business.CashService;
import uo.ri.business.impl.cash.CreateInvoiceFor;
import uo.ri.model.Averia;
import uo.ri.model.Factura;
import uo.ri.model.MedioPago;
import uo.ri.model.exception.BusinessException;

public class CashServiceImpl implements CashService {
	
	CommandExecutor executor = new CommandExecutor();

	@Override
	public Factura createInvoiceFor(List<Long> idsAveria)
			throws BusinessException {
		return (Factura) executor.execute(new CreateInvoiceFor( idsAveria ));
	}

	@Override
	public Factura findInvoice(Long numeroFactura) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MedioPago> findPayMethodsForInvoice(Long idFactura)
			throws BusinessException {
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void settleInvoice(Long idFactura, Map<Long, Double> cargos)
			throws BusinessException {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Averia> findRepairsByClient(String dni) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
