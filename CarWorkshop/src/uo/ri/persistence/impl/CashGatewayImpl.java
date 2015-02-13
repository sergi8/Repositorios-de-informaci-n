package uo.ri.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.persistence.CashGateway;

public class CashGatewayImpl implements CashGateway
{
	private Connection connection;
	
	
	private static final String SQL_IMPORTE_REPUESTOS = 
			"select sum(s.cantidad * r.precio) " +
			"	from  TSustituciones s, TRepuestos r " +
			"	where s.repuesto_id = r.id " +
			"		and s.intervencion_averia_id = ?";
		
		private static final String SQL_IMPORTE_MANO_OBRA =
			"select sum(i.minutos * tv.precioHora / 60) " +
			"	from TAverias a, TIntervenciones i, TVehiculos v, TTiposVehiculo tv" +
			"	where i.averia_id = a.id " +
			"		and a.vehiculo_id = v.id" +
			"		and v.tipo_id = tv.id" +
			"		and a.id = ?" +
			"		and a.status = 'TERMINADA'";

		private static final String SQL_ULTIMO_NUMERO_FACTURA = 
			"select max(numero) from TFacturas";

		private static final String SQL_INSERTAR_FACTURA = 
			"insert into TFacturas(numero, fecha, iva, importe, status) " +
			"	values(?, ?, ?, ?, ?)";

		private static final String SQL_RECUPERAR_CLAVE_GENERADA = 
			"select id from TFacturas where numero = ?";
		

	@Override
	public void setConnection(Connection conn) 
	{
		this.connection=conn;
		
	}

	@Override
	public Long generarNuevoNmeroFactura() throws SQLException 
	{
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try{
			pst = connection.prepareStatement(SQL_ULTIMO_NUMERO_FACTURA);
			rs = pst.executeQuery();
			
			if (rs.next()) 
				return rs.getLong(1) + 1; // +1, el siguiente
			 else   // todavía no hay ninguna
				return 1L;
		
		}finally {
			Jdbc.close(rs, pst);
		}
		
	}

	@Override
	public long crearFactura(long numeroFactura, Date fechaFactura, double iva,double totalConIva) throws SQLException 
	{
		PreparedStatement pst = null;
		
		try {
			pst = connection.prepareStatement(SQL_INSERTAR_FACTURA);
			pst.setLong(1, numeroFactura);
			pst.setDate(2, new java.sql.Date(fechaFactura.getTime()));
			pst.setDouble(3, iva);
			pst.setDouble(4, totalConIva);
			pst.setString(5, "SIN_ABONAR");

			pst.executeUpdate();

			return getGeneratedKey(numeroFactura); // Id de la nueva factura generada
			
		} finally {
			Jdbc.close(pst);
		}
	}

	@Override
	public long getGeneratedKey(long numeroFactura) throws SQLException 
	{
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			pst = connection.prepareStatement(SQL_RECUPERAR_CLAVE_GENERADA);
			pst.setLong(1, numeroFactura);
			rs = pst.executeQuery();
			rs.next();

			return rs.getLong(1);
			
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public double consultaImporteManoObra(Long idAveria)throws BusinessException, SQLException 
	{
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = connection.prepareStatement(SQL_IMPORTE_MANO_OBRA);
			pst.setLong(1, idAveria);
			
			rs = pst.executeQuery();
			if (rs.next() == false) {
				throw new BusinessException("La averia no existe o no se puede facturar");
			}
			
			return rs.getDouble(1);
			
		} catch (BusinessException e) {
			throw e;
		}
		finally {
			Jdbc.close(rs, pst);
		}			
	}

	@Override
	public double consultaImporteRepuestos(Long idAveria) throws SQLException 
	{
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = connection.prepareStatement(SQL_IMPORTE_REPUESTOS);
			pst.setLong(1, idAveria);
			
			rs = pst.executeQuery();
			if (rs.next() == false) {
				return 0.0; // La averia puede no tener repuestos
			}
			
			return rs.getDouble(1);
			
		}
		finally {
			Jdbc.close(rs, pst);
		}
	}

}
