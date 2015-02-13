package uo.ri.busyness.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import uo.ri.common.BusinessException;
import alb.util.jdbc.Jdbc;

public class DeleteMechanic
{

	private static String SQL;
	private long idMecanico;
	
	public DeleteMechanic(long idMecanico) 
	{
		SQL = "delete from TMecanicos where id = ?";
		this.idMecanico= idMecanico;
	}

	public void execute() throws BusinessException 
	{	
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
	}

}
