package uo.ri.busyness.impl.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;

public class AddMechanic 
{

	private static String SQL;
	private String nombre;
	private String apellidos;
	
	public AddMechanic(String nombre, String apellidos)
	{
		SQL = "insert into TMecanicos(nombre, apellidos) values (?, ?)";
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	public void execute() throws BusinessException 
	{
		// Procesar
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			conn = Jdbc.getConnection();
			
			pst = conn.prepareStatement(SQL);
			pst.setString(1, nombre);
			pst.setString(2, apellidos);
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, conn);
		}
		
	}

}
