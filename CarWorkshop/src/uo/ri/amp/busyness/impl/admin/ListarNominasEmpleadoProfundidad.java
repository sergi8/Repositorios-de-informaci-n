package uo.ri.amp.busyness.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;

import uo.ri.amp.conf.PersistentFactory;
import uo.ri.amp.persistence.NominaGateway;
import uo.ri.common.BusinessException;
import alb.util.jdbc.Jdbc;

// TODO: Auto-generated Javadoc
/**
 * The Class ListarNominasEmpleadoProfundidad.
 */
public class ListarNominasEmpleadoProfundidad
{
	
	/** The id mecanico. */
	int idMecanico;

	/**
	 * Instantiates a new listar nominas empleado profundidad.
	 *
	 * @param idMecanico the id mecanico
	 */
	public ListarNominasEmpleadoProfundidad(int idMecanico) 
	{
		this.idMecanico = idMecanico;
	}


	/**
	 * Execute.
	 *
	 * @throws BusinessException the business exception
	 */
	public void execute() throws BusinessException
	{
		Connection conn = null;

		
		try {
			conn = Jdbc.getConnection();
			NominaGateway ng = PersistentFactory.getNominaGateway();
			ng.setConnection(conn);
			ng.ListarNominasEmpleadoProfundidad(idMecanico);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
		
		
	}

}
