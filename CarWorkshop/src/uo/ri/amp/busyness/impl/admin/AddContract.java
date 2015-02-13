package uo.ri.amp.busyness.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;

import uo.ri.amp.conf.PersistentFactory;
import uo.ri.amp.persistence.ContractGateway;
import uo.ri.common.BusinessException;
import alb.util.jdbc.Jdbc;

// TODO: Auto-generated Javadoc
/**
 * The Class AddContract.
 */
public class AddContract 
{
	
	/** The id mecanic. */
	private int idMecanic;
	
	/** The tipo contrato. */
	private String tipoContrato;
	
	/** The categoría. */
	private String categoría;
	
	/** The salario base. */
	private double salarioBase;
	
	/** The fecha contrato. */
	private String fechaContrato;
	
	/** The fecha fin. */
	private String fechaFin;
	
	/**
	 * Instantiates a new adds the contract.
	 *
	 * @param idMecanic the id mecanic
	 * @param tipoContrato the tipo contrato
	 * @param categoria the categoria
	 * @param salarioBase the salario base
	 * @param fechaContrato the fecha contrato
	 * @param fechaFin the fecha fin
	 */
	public AddContract(int idMecanic, String tipoContrato, String categoria,double salarioBase, String fechaContrato, String fechaFin) 
	{
		this.idMecanic = idMecanic;
		this.tipoContrato = tipoContrato;
		this.categoría = categoria;
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
		// Procesar
		Connection conn = null;

		
			try {
				conn = Jdbc.getConnection();
				ContractGateway cg = PersistentFactory.getContractGateway();
				cg.setConnection(conn);
				cg.addContract(idMecanic, tipoContrato, categoría, salarioBase, fechaContrato, fechaFin);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}	
	}

	

}
