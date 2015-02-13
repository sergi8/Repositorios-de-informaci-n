package uo.ri.amp.busyness.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;

import uo.ri.amp.conf.PersistentFactory;
import uo.ri.amp.persistence.NominaGateway;
import uo.ri.common.BusinessException;
import alb.util.jdbc.Jdbc;

public class GeneraNominas
{

	public void execute() throws BusinessException
	{
		Connection conn = null;

		try {
			conn = Jdbc.getConnection();
			NominaGateway ng = PersistentFactory.getNominaGateway();
			ng.setConnection(conn);
			ng.generaNominas();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

}
