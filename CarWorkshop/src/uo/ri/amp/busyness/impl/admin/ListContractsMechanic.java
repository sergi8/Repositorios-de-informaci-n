package uo.ri.amp.busyness.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;

import uo.ri.amp.conf.PersistentFactory;
import uo.ri.amp.persistence.ContractGateway;
import uo.ri.common.BusinessException;
import alb.util.jdbc.Jdbc;

// TODO: Auto-generated Javadoc
/**
 * The Class ListContractsMechanic.
 */
public class ListContractsMechanic
{
	
	/** The Mecanico id. */
	private int MecanicoID;
	
	/**
	 * Instantiates a new list contracts mechanic.
	 *
	 * @param MecanicoID the mecanico id
	 */
	public ListContractsMechanic(int MecanicoID) 
	{
		this.MecanicoID=MecanicoID;
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
			ContractGateway cg = PersistentFactory.getContractGateway();
			cg.setConnection(conn);
			cg.listContractsMechanic(MecanicoID);
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
}
