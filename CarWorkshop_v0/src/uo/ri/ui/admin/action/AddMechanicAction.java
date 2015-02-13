package uo.ri.ui.admin.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import alb.util.menu.Action;
import uo.ri.common.BusinessException;

public class AddMechanicAction implements Action {

	private static String SQL = "insert into TMecanicos(nombre, apellidos) values (?, ?)";

	@Override
	public void execute() throws BusinessException {
		
		// Pedir datos
		String nombre = Console.readString("Nombre"); 
		String apellidos = Console.readString("Apellidos");
		
		// Procesar
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement(SQL);
			pst.setString(1, nombre);
			pst.setString(2, apellidos);
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		
		// Mostrar resultado
		Console.println("Nuevo mecánico añadido");
	}

}
