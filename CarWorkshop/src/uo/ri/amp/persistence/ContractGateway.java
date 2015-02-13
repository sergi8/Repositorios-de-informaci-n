package uo.ri.amp.persistence;

import java.sql.Connection;

// TODO: Auto-generated Javadoc
/**
 * The Interface ContractGateway.
 */
public interface ContractGateway 
{
	
	/**
	 * Sets the connection.
	 *
	 * @param conn the new connection
	 */
	public void setConnection(Connection conn);
	
	/**
	 * List contracts mechanic.
	 *
	 * @param MecanicoID the mecanico id
	 */
	public void listContractsMechanic(int MecanicoID);
	
	/**
	 * Adds the contract.
	 *
	 * @param idMecanico the id mecanico
	 * @param tipoContrato the tipo contrato
	 * @param categoria the categoria
	 * @param salarioBase the salario base
	 * @param fechaContrato the fecha contrato
	 * @param fechaFin the fecha fin
	 */
	public void addContract(int idMecanico,String tipoContrato, String categoria, double salarioBase, String fechaContrato, String fechaFin);
	
	/**
	 * Update contract.
	 *
	 * @param id_contrato the id_contrato
	 * @param tipoContrato the tipo contrato
	 * @param categoria the categoria
	 * @param salarioBase the salario base
	 * @param fechaContrato the fecha contrato
	 * @param fechaFin the fecha fin
	 */
	public void UpdateContract(int id_contrato, String tipoContrato, String categoria,double salarioBase, String fechaContrato, String fechaFin);
	
	/**
	 * Delete contrac.
	 *
	 * @param contratoID the contrato id
	 */
	public void DeleteContrac(int contratoID);
	
	/**
	 * Finalizar contrato.
	 *
	 * @param contractId the contract id
	 */
	public void finalizarContrato(Long contractId);
	
	/**
	 * Extinguir contrato.
	 *
	 * @param contractId the contract id
	 */
	public void extinguirContrato(Long contractId);
	
	/**
	 * Show contract.
	 *
	 * @param contractID the contract id
	 */
	public void ShowContract(int contractID);
	
	/**
	 * Lis tcategorias.
	 */
	public void lisTcategorias();
	
	/**
	 * Lis tipos contrato.
	 */
	public void lisTiposContrato();
	
	/**
	 * Adds the contract type.
	 *
	 * @param nombre the nombre
	 * @param diasIndemnizacion the dias indemnizacion
	 */
	public void addContractType(String nombre, int diasIndemnizacion);
	
	/**
	 * List mechanics by contract type.
	 *
	 * @param contractTypeId the contract type id
	 */
	public void ListMechanicsByContractType(Long contractTypeId);
	
	/**
	 * Delete contract type.
	 *
	 * @param contractTypeId the contract type id
	 */
	public void deleteContractType(Long contractTypeId);
	
	/**
	 * Update contract type.
	 *
	 * @param id the id
	 * @param nombre the nombre
	 * @param diasIndemnizacion the dias indemnizacion
	 */
	public void updateContractType(Long id, String nombre, int diasIndemnizacion);
	
}
