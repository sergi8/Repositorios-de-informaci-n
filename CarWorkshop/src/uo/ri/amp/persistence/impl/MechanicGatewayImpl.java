package uo.ri.amp.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import uo.ri.amp.persistence.MechanicGateway;
// TODO: Auto-generated Javadoc
//select M.id, M.nombre, M.apellidos from TMecanicos M, TContratos C where M.id = C.mecanico_id and C.estado='ACTIVO';
/**
 * The Class MechanicGatewayImpl.
 */
public class MechanicGatewayImpl implements MechanicGateway
{
	
	/** The conn. */
	private Connection conn;

	/* (non-Javadoc)
	 * @see uo.ri.amp.persistence.MechanicGateway#setConnection(java.sql.Connection)
	 */
	@Override
	public void setConnection(Connection conn) 
	{
		this.conn=conn;
	}
	
	
	/* (non-Javadoc)
	 * @see uo.ri.amp.persistence.MechanicGateway#addMechanic(java.lang.String, java.lang.String)
	 */
	@Override
	public void addMechanic(String nombre, String apellidos)
	{
		String SQL = "insert into TMecanicos(nombre, apellidos) values (?, ?)";
		ResultSet rs = null;
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(SQL);
			pst.setString(1, nombre);
			pst.setString(2, apellidos);
			
			pst.executeUpdate();
			
		} catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}finally {
			Jdbc.close(rs, pst, conn);
		}
	}


	/* (non-Javadoc)
	 * @see uo.ri.amp.persistence.MechanicGateway#deleteMechanic(java.lang.Long)
	 */
	@SuppressWarnings("resource")
	@Override
	public void deleteMechanic(Long idMecanico) 
	{
		//String SQL= "delete from TMecanicos where id = ?";
		String SQL= "delete from tmecanicos where id = ? and id not in(select mecanico_id from taverias a where a.status = 'ASIGNADA')and id not in(select mecanico_id from tintervenciones) and id not in(select mecanico_id from tcontratos)";
		String QUERY = "select count(*) from tmecanicos where id = ? and id not in(select mecanico_id from taverias a where a.status = 'ASIGNADA')and id not in(select mecanico_id from tintervenciones) and id not in(select mecanico_id from tcontratos)";
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = conn.prepareStatement(QUERY);
			pst.setLong(1, idMecanico);
			rs = pst.executeQuery();
			
			pst = conn.prepareStatement(SQL);
			pst.setLong(1, idMecanico);
			pst.executeUpdate();
			
			rs.next();
			if(rs.getInt(1)<1)
				Console.printf("\t%s\n", "no se ha podido eliminar a este mecánico");
			else
				Console.printf("\t%s\n", "Se ha eliminado el mecánico");
		} catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, conn);
		}
		
	}

	/**
	 * metodo que realiza una consulta sobre la base de datos, en la que se busca el id, el nombre,
	 * y los apellidos de los mecánicos que tengan algún contrato en estado activo.
	 * 
	 * tabla sobre la que se busca:
	 * "create table public.tcontratos(dtipe varchar not null, id integer not null, mecanico_id integer not null, nomina_id integer, estado varchar not null, fecha_contrato date not null, fecha_fin date, categoria varchar, primary key(id));"
	 */
	@Override
	public void listMechanics() 
	{
		String SQL="select M.id, M.nombre, M.apellidos from TMecanicos M, TContratos C where M.id = C.mecanico_id and C.estado='ACTIVO'";
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = conn.prepareStatement(SQL);
			rs = pst.executeQuery();
			while(rs.next()) 
			{
				Console.printf("\t%d %s %s\n",  
					rs.getLong(1)
					,  rs.getString(2) 
					,  rs.getString(3)
				);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Jdbc.close(rs, pst, conn);
		}
	}

	/**
	 * realiza una consulta sobre la tabla mecánicos en la que se buscan todos lod mecánicos.
	 */
	@Override
	public void listAllMechanics() 
	{
		String SQL= "select id, nombre, apellidos from TMecanicos";
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = conn.prepareStatement(SQL);
			rs = pst.executeQuery();
			while(rs.next()) 
			{
				Console.printf("\t%d %s %s\n",  
					rs.getLong(1)
					,  rs.getString(2) 
					,  rs.getString(3)
				);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Jdbc.close(rs, pst, conn);
		}
	}

	/**
	 * métoso encargado de modificar los campos de una entrada de la tabla mecánicos
	 */
	@Override
	public void updateMechanic(Long id, String nombre, String apellidos) 
	{
		String SQL= "update TMecanicos " +"set nombre = ?, apellidos = ? " +"where id = ?";
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = conn.prepareStatement(SQL);
			pst.setString(1, nombre);
			pst.setString(2, apellidos);
			pst.setLong(3, id);
			
			pst.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Jdbc.close(rs, pst, conn);
		}
		
	}

}
