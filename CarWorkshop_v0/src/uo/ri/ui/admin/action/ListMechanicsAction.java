package uo.ri.ui.admin.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import uo.ri.common.BusinessException;
import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import alb.util.menu.Action;

public class ListMechanicsAction implements Action {

	private static String SQL = "select id, nombre, apellidos from TMecanicos";
	
	@Override
	public void execute() throws BusinessException {

		Console.println("\nListado de mecánicos\n");  

		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement(SQL);
			
			rs = pst.executeQuery();
			while(rs.next()) {
				Console.printf("\t%d %s %s\n",  
					rs.getLong(1)
					,  rs.getString(2) 
					,  rs.getString(3)
				);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
	}
}
