package uo.ri.busyness.impl;

import java.util.List;
import java.util.Map;

import uo.ri.busyness.CashService;
import uo.ri.busyness.impl.cash.FacturaReparaciones;
import uo.ri.common.BusinessException;

public class CashServiceImpl implements CashService
{

	@Override
	public void buscarReparacionPorMatrícula() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map<String, Object> facturarReparaciones(List<Long> idsAveria) throws BusinessException 
	{
		return new FacturaReparaciones(idsAveria).execute();
		
	}

	@Override
	public void liquidarFactura() 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reparacionesNofacturadasUncliente() 
	{
		// TODO Auto-generated method stub
		
	}

}
