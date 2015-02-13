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
 * The Class DeleteContract.
 */
public class DeleteContract implements Action
{
	
	/** The id contract. */
	private int idContract;
	
	/**
	 * Instantiates a new delete contract.
	 *
	 * @param idContract the id contract
	 */
	public DeleteContract(int idContract) 
	{
		this.idContract = idContract;
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
			cg.DeleteContrac(idContract);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

}
