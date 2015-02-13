package uo.ri.busyness.impl.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import uo.ri.common.BusinessException;
import alb.util.jdbc.Jdbc;

public class UpdateMechanic
{

	private Long id;
	private String nombre;
	private String apellidos;
	private static String SQL;
	
	

	public UpdateMechanic(Long id, String nombre, String apellidos) 
	{
		
		SQL = "update TMecanicos " +"set nombre = ?, apellidos = ? " +"where id = ?";
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	public void execute() throws BusinessException 
	{
		
		// Procesar
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement(SQL);
			pst.setString(1, nombre);
			pst.setString(2, apellidos);
			pst.setLong(3, id);
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
	}
}
