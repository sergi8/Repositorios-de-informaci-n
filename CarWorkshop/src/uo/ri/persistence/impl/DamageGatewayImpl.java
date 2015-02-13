package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.persistence.DamageGateway;

public class DamageGatewayImpl implements DamageGateway
{
	private Connection connection;

		private static final String SQL_UPDATE_IMPORTE_AVERIA = 
			"update TAverias set importe = ? where id = ?";

		private static final String SQL_VINCULAR_AVERIA_FACTURA = 
			"update TAverias set factura_id = ? where id = ?";

		private static final String SQL_ACTUALIZAR_ESTADO_AVERIA = 
			"update TAverias set status = ? where id = ?";

		private static final String SQL_VERIFICAR_ESTADO_AVERIA = 
			"select status from TAverias where id = ?";
		

	@Override
	public void setConnection(Connection conn) 
	{
		this.connection=conn;
		
	}

	@SuppressWarnings("resource")
	@Override
	public void verificarAveriasTerminadas(List<Long> idsAveria) throws SQLException, BusinessException 
	{
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = connection.prepareStatement(SQL_VERIFICAR_ESTADO_AVERIA);
			
			for (Long idAveria : idsAveria) {
				pst.setLong(1, idAveria);
				
				rs = pst.executeQuery();
				if (rs.next() == false) {
					throw new BusinessException("No existe la averia " + idAveria);
				}
				
				String status = rs.getString(1); 
				if (! "TERMINADA".equalsIgnoreCase(status) ) {
					throw new BusinessException("No está terminada la avería " + idAveria);
				}
				
				rs.close();
			}
		} finally {
			Jdbc.close(rs, pst);
		}
		
	}

	@Override
	public void vincularAveriasConFacturas(long idFactura, List<Long> idsAveria) throws SQLException 
	{
		PreparedStatement pst = null;
		try {
			pst = connection.prepareStatement(SQL_VINCULAR_AVERIA_FACTURA);

			for (Long idAveria : idsAveria) {
				pst.setLong(1, idFactura);
				pst.setLong(2, idAveria);

				pst.executeUpdate();
			}
		} finally {
			Jdbc.close(pst);
		}
	}

	@Override
	public void cambiarEstadoAverias(List<Long> idsAveria, String status) throws SQLException 
	{
		PreparedStatement pst = null;
		try {
			pst = connection.prepareStatement(SQL_ACTUALIZAR_ESTADO_AVERIA);
			
			for (Long idAveria : idsAveria) {
				pst.setString(1, status);
				pst.setLong(2, idAveria);

				pst.executeUpdate();
			}
		} finally {
			Jdbc.close(pst);
		}
		
	}

	@Override
	public void actualizarImporteAveria(Long idAveria, double totalAveria)throws SQLException 
	{
		PreparedStatement pst = null;
		
		try {
			pst = connection.prepareStatement(SQL_UPDATE_IMPORTE_AVERIA);
			pst.setDouble(1, totalAveria);
			pst.setLong(2, idAveria);
			pst.executeUpdate();
		}	
		finally {
			Jdbc.close(pst);
		}
		
	}

}
