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
 * The Class DeleteContractType.
 */
public class DeleteContractType implements Action
{
	
	/** The id contract type. */
	private Long idContractType;
	
	/**
	 * Instantiates a new delete contract type.
	 *
	 * @param idContractType the id contract type
	 */
	public DeleteContractType(Long idContractType) 
	{
		this.idContractType = idContractType;
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
			cg.deleteContractType(idContractType);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
		
	}

}
