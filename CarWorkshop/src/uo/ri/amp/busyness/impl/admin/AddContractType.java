package uo.ri.amp.busyness.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.amp.conf.PersistentFactory;
import uo.ri.amp.persistence.ContractGateway;
import uo.ri.common.BusinessException;

// TODO: Auto-generated Javadoc
/**
 * The Class AddContractType.
 */
public class AddContractType 
{
	
	/** The nombre. */
	private String nombre;
	
	/** The dias indem nizacion. */
	int diasIndemNizacion;
	
	/**
	 * Instantiates a new adds the contract type.
	 *
	 * @param nombre the nombre
	 * @param diasIndemnizacion the dias indemnización
	 */
	public AddContractType(String nombre, int diasIndemnizacion) 
	{
		this.nombre = nombre;
		this.diasIndemNizacion = diasIndemnizacion;
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
			cg.addContractType(nombre, diasIndemNizacion);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
	}

}
