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
 * The Class FinalizarContrato.
 */
public class FinalizarContrato implements Action
{
	
	/** The contract id. */
	private Long contractId;

	/**
	 * Instantiates a new finalizar contrato.
	 *
	 * @param contractId the contract id
	 */
	public FinalizarContrato(Long contractId) 
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
			cg.finalizarContrato(contractId);
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

}
