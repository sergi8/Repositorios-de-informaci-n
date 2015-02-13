package uo.ri.amp.busyness.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;

import uo.ri.amp.conf.PersistentFactory;
import uo.ri.amp.persistence.ContractGateway;
import uo.ri.common.BusinessException;
import alb.util.jdbc.Jdbc;
import alb.util.menu.Action;

// TODO: Auto-generated Javadoc
/**
 * The Class ShowContract.
 */
public class ShowContract implements Action
{
	
	/** The contrato id. */
	private int contratoID;

	/**
	 * Instantiates a new show contract.
	 *
	 * @param contratoID the contrato id
	 */
	public ShowContract(int contratoID) 
	{
		this.contratoID=contratoID;
	}
	
	/* (non-Javadoc)
	 * @see alb.util.menu.Action#execute()
	 */
	@Override
	public void execute() throws BusinessException
	{
		Connection conn = null;

		try {
			conn = Jdbc.getConnection();
			ContractGateway cg = PersistentFactory.getContractGateway();
			cg.setConnection(conn);
			cg.ShowContract(contratoID);
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

}
