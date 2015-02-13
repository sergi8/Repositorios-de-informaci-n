package uo.ri.amp.busyness.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.amp.conf.PersistentFactory;
import uo.ri.amp.persistence.NominaGateway;
import uo.ri.common.BusinessException;

// TODO: Auto-generated Javadoc
/**
 * The Class ListarNominasEmpleado.
 */
public class ListarNominasEmpleado
{
	
	/** The id mecanico. */
	int idMecanico;

	/**
	 * Instantiates a new listar nominas empleado.
	 *
	 * @param idMecanico the id mecanico
	 */
	public ListarNominasEmpleado(int idMecanico) 
	{
		super();
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
			ng.ListarNominasEmpleado(idMecanico);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
		
	}

}
