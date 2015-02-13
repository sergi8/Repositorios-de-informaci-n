package uo.ri.busyness;

import java.util.List;
import java.util.Map;

import uo.ri.common.BusinessException;

public interface CashService 
{
	public void buscarReparacionPorMatrícula()throws BusinessException;
	public Map<String, Object> facturarReparaciones(List<Long> idsAveria)throws BusinessException;
	public void liquidarFactura()throws BusinessException;
	public void reparacionesNofacturadasUncliente()throws BusinessException;
}
