package uo.ri.amp.busyness.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;

import uo.ri.common.BusinessException;
import uo.ri.amp.conf.PersistentFactory;
import uo.ri.amp.persistence.MechanicGateway;
import alb.util.jdbc.Jdbc;

// TODO: Auto-generated Javadoc
/**
 * The Class DeleteMechanic.
 */
public class DeleteMechanic
{
	
	/** The id mecanico. */
	private long idMecanico;
	
	/**
	 * Instantiates a new delete mechanic.
	 *
	 * @param idMecanico the id mecanico
	 */
	public DeleteMechanic(long idMecanico) 
	{
		this.idMecanico= idMecanico;
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
			MechanicGateway mg = PersistentFactory.getMechanicGateway();
			mg.setConnection(conn);
			mg.deleteMechanic(idMecanico);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
