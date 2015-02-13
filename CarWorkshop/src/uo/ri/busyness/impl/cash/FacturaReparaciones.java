package uo.ri.busyness.impl.cash;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uo.ri.common.BusinessException;
import uo.ri.amp.conf.PersistentFactory;
import uo.ri.persistence.CashGateway;
import uo.ri.persistence.DamageGateway;
import alb.util.date.DateUtil;
import alb.util.jdbc.Jdbc;
import alb.util.math.Round;

public class FacturaReparaciones 
{
		private Connection connection;
		private List<Long> idsAveria;
		
		public FacturaReparaciones(List<Long> idsAveria)
		{
			this.idsAveria=idsAveria;
		}
		
		public Map<String, Object> execute() throws BusinessException
		{
			try {
				createConn();
				verificarAveriasTerminadas(idsAveria);
				Map<String, Object>  datos = new HashMap<>();

				long numeroFactura = generarNuevoNumeroFactura();
				Date fechaFactura = DateUtil.today();
				double totalFactura = calcularImportesAverias(idsAveria);
				double iva = porcentajeIva(totalFactura, fechaFactura);
				double importe = totalFactura * (1 + iva/100);
				importe = Round.twoCents(importe);
				
				long idFactura = crearFactura(numeroFactura, fechaFactura, iva, importe);
				vincularAveriasConFactura(idFactura, idsAveria);
				cambiarEstadoAverias(idsAveria, "FACTURADA");
				connection.commit();
				
				
				datos.put("numeroFactura", numeroFactura);
				datos.put("fechaFactura", fechaFactura);
				datos.put("totalFactura", totalFactura);
				datos.put("iva", iva);
				datos.put("importe", importe);
				return datos;
				
			}
			catch (SQLException e) {
				try { connection.rollback(); } catch (SQLException ex) {};
				throw new RuntimeException(e);
			}
			catch (BusinessException e) {
				try { connection.rollback(); } catch (SQLException ex) {};
				throw e;
			}
			finally {
				Jdbc.close(connection);
			}
		}
		
		public void createConn() throws SQLException
		{
			connection = Jdbc.getConnection();
			connection.setAutoCommit(false);
		}
		
		private void verificarAveriasTerminadas(List<Long> idsAveria) throws SQLException, BusinessException 
		{
			DamageGateway dg = PersistentFactory.getDamageGateway();
			dg.setConnection(connection);
			dg.verificarAveriasTerminadas(idsAveria);
		}
		
		private Long generarNuevoNumeroFactura() throws SQLException 
		{
			CashGateway cg = PersistentFactory.getCashGateway();
			cg.setConnection(connection);
			return cg.generarNuevoNmeroFactura();
		}
		
		protected double calcularImportesAverias(List<Long> idsAveria)throws BusinessException, SQLException 
		{
			
			double totalFactura = 0.0;
			DamageGateway dg = PersistentFactory.getDamageGateway();
			CashGateway cg = PersistentFactory.getCashGateway();
			dg.setConnection(connection);
			cg.setConnection(connection);
			for(Long idAveria : idsAveria) {
				double importeManoObra = cg.consultaImporteManoObra(idAveria);
				double importeRepuestos = cg.consultaImporteRepuestos(idAveria);
				double totalAveria = importeManoObra + importeRepuestos;
				
				dg.actualizarImporteAveria(idAveria, totalAveria);
				
				totalFactura += totalAveria; 
			}
			return totalFactura;
		}
		
		private double porcentajeIva(double totalFactura, Date fechaFactura) 
		{
			return DateUtil.fromString("1/7/2012").before(fechaFactura) ? 21.0 : 18.0;
		}
		
		private long crearFactura(long numeroFactura, Date fechaFactura,double iva, double totalConIva) throws SQLException
		{
			CashGateway cg = PersistentFactory.getCashGateway();
			cg.setConnection(connection);
			return cg.crearFactura(numeroFactura, fechaFactura, iva, totalConIva);
		}
		
		private void vincularAveriasConFactura(long idFactura, List<Long> idsAveria) throws SQLException 
		{
			DamageGateway dg = PersistentFactory.getDamageGateway();
			dg.setConnection(connection);
			dg.vincularAveriasConFacturas(idFactura, idsAveria);
		}
		
		private void cambiarEstadoAverias(List<Long> idsAveria, String status) throws SQLException 
		{
			DamageGateway dg = PersistentFactory.getDamageGateway();
			dg.setConnection(connection);
			dg.cambiarEstadoAverias(idsAveria, status);
		}

}
