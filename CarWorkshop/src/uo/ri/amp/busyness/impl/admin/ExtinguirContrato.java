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
 * The Class ExtinguirContrato.
 */
public class ExtinguirContrato implements Action
{
	
	/** The contract id. */
	private Long contractId;

	/**
	 * Instantiates a new extinguir contrato.
	 *
	 * @param contractId the contract id
	 */
	public ExtinguirContrato(Long contractId) 
	{
		this.contractId = contractId;
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
			cg.extinguirContrato(contractId);
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

}
