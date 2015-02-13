package uo.ri.ui.admin.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import uo.ri.common.BusinessException;
import alb.util.console.Console;
import alb.util.jdbc.Jdbc;
import alb.util.menu.Action;

public class DeleteMechanicAction implements Action {

	private static String SQL = "delete from TMecanicos where id = ?";

	@Override
	public void execute() throws BusinessException {
		Long idMecanico = Console.readLong("Id de mecánico"); 
		
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getConnection();
			
			pst = c.prepareStatement(SQL);
			pst.setLong(1, idMecanico);
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		finally {
			Jdbc.close(rs, pst, c);
		}
		
		Console.println("Se ha eliminado el mecánico");
	}

}
