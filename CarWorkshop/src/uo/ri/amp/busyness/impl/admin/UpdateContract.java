package uo.ri.amp.busyness.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;

import uo.ri.amp.conf.PersistentFactory;
import uo.ri.amp.persistence.ContractGateway;
import uo.ri.common.BusinessException;
import alb.util.jdbc.Jdbc;

// TODO: Auto-generated Javadoc
/**
 * The Class UpdateContract.
 */
public class UpdateContract
{
	
	/** The id_contrato. */
	private int id_contrato;
	
	/** The tipo contrato. */
	private String tipoContrato;
	
	/** The categoria. */
	private String categoria;
	
	/** The salario base. */
	private double salarioBase;
	
	/** The fecha contrato. */
	private String fechaContrato;
	
	/** The fecha fin. */
	private String fechaFin;
	
	/**
	 * Instantiates a new update contract.
	 *
	 * @param id_contrato the id_contrato
	 * @param tipoContrato the tipo contrato
	 * @param categoria the categoria
	 * @param salarioBase the salario base
	 * @param fechaContrato the fecha contrato
	 * @param fechaFin the fecha fin
	 */
	public UpdateContract(int id_contrato, String tipoContrato, String categoria,double salarioBase, String fechaContrato, String fechaFin) 
	{
		this.id_contrato = id_contrato;
		this.tipoContrato = tipoContrato;
		this.categoria = categoria;
		this.salarioBase = salarioBase;
		this.fechaContrato = fechaContrato;
		this.fechaFin = fechaFin;
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
			cg.UpdateContract(id_contrato, tipoContrato,categoria,salarioBase,fechaContrato,fechaFin);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
		
	}

}
