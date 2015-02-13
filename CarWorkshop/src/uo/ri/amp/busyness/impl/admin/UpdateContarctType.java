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
 * The Class UpdateContarctType.
 */
public class UpdateContarctType implements Action
{
	
	/** The id. */
	private Long id;
	
	/** The nombre. */
	private String nombre;
	
	/** The dias indemnizacion. */
	private int diasIndemnizacion;
	
	/**
	 * Instantiates a new update contarct type.
	 *
	 * @param id the id
	 * @param nombre the nombre
	 * @param diasIndemnizacion the dias indemnizacion
	 */
	public UpdateContarctType(Long id, String nombre, int diasIndemnizacion) 
	{
		this.id= id;
		this.nombre = nombre;
		this.diasIndemnizacion = diasIndemnizacion;
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
			cg.updateContractType(id, nombre, diasIndemnizacion);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
		
	}

}
