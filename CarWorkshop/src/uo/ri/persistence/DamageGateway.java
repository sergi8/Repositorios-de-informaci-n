package uo.ri.persistence;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import uo.ri.common.BusinessException;

public interface DamageGateway 
{
	public void setConnection(Connection conn);
	
	public void verificarAveriasTerminadas(List<Long> idsAveria) throws SQLException, BusinessException;
	public void vincularAveriasConFacturas(long idFactura, List<Long> idsAveria) throws SQLException;
	public void cambiarEstadoAverias(List<Long> idsAveria, String status) throws SQLException;
	public void actualizarImporteAveria(Long idAveria, double totalAveria) throws SQLException;

}
