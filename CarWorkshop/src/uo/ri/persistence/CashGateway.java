package uo.ri.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import uo.ri.common.BusinessException;

public interface CashGateway 
{
	public void setConnection(Connection conn);
	
	public Long generarNuevoNmeroFactura() throws SQLException;
	public long crearFactura(long numeroFactura, Date fechaFactura,double iva, double totalConIva) throws SQLException;
	public long getGeneratedKey(long numeroFactura) throws SQLException;
	public double consultaImporteManoObra(Long idAveria) throws BusinessException, SQLException;
	public double consultaImporteRepuestos(Long idAveria) throws SQLException;
	
}
