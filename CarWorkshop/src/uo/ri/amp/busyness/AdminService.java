package uo.ri.amp.busyness;

import uo.ri.common.BusinessException;
// TODO: Auto-generated Javadoc
/**
 * interfaz de adminservice empleada para conseguir un mayor encapsulamiento y una 
 * mejor separación de las capas de lógivca e interfáz de usuario.
 * el interfáz de usuartio conoce este interfáz, pero no la implementación de laa clase.
 * @author uo227806
 *
 */
public interface AdminService 
{
	
	/**
	 * Adds the mechanic.
	 *
	 * @param nombre the nombre
	 * @param apellido the apellido
	 * @throws BusinessException the business exception
	 */
	public void addMechanic(String nombre, String apellido) throws BusinessException;
	
	/**
	 * Delete mechanic.
	 *
	 * @param idMecanico the id mecanico
	 * @throws BusinessException the business exception
	 */
	public void deleteMechanic(Long idMecanico)throws BusinessException;
	
	/**
	 * List mechanic.
	 *
	 * @throws BusinessException the business exception
	 */
	public void listMechanic()throws BusinessException;
	
	/**
	 * List all mechanics.
	 *
	 * @throws BusinessException the business exception
	 */
	public void ListAllMechanics()throws BusinessException;
	
	/**
	 * Update mechanic.
	 *
	 * @param id the id
	 * @param nombre the nombre
	 * @param apellidos the apellidos
	 * @throws BusinessException the business exception
	 */
	public void updateMechanic(Long id, String nombre, String apellidos)throws BusinessException;
	
	/**
	 * Adds the contract.
	 *
	 * @param idMecanico the id mecanico
	 * @param tipoContrato the tipo contrato
	 * @param categoria the categoría
	 * @param salarioBase the salario base
	 * @param fechaContrato the fecha contrato
	 * @param fechaFin the fecha fin
	 * @throws BusinessException the business exception
	 */
	public void addContract(int idMecanico,String tipoContrato, String categoria, double salarioBase, String fechaContrato, String fechaFin) throws BusinessException;
	
	/**
	 * Update contract.
	 *
	 * @param id_contrato the id_contrato
	 * @param tipoContrato the tipo contrato
	 * @param categoria the categoria
	 * @param salarioBase the salario base
	 * @param fechaContrato the fecha contrato
	 * @param fechaFin the fecha fin
	 * @throws BusinessException the business exception
	 */
	public void UpdateContract(int id_contrato, String tipoContrato,String categoria,double salarioBase,String fechaContrato,String fechaFin)throws BusinessException;
	
	/**
	 * Delete contrac.
	 *
	 * @throws BusinessException the business exception
	 */
	public void DeleteContrac()throws BusinessException;
	
	/**
	 * Finalizar contrato.
	 *
	 * @param idContrato the id contrato
	 * @throws BusinessException the business exception
	 */
	public void FinalizarContrato(Long idContrato)throws BusinessException;
	
	/**
	 * Extinguir contrato.
	 *
	 * @param idContrato the id contrato
	 * @throws BusinessException the business exception
	 */
	public void ExtinguirContrato(Long idContrato)throws BusinessException;
	
	/**
	 * List contracts mechanic.
	 *
	 * @param MecanicoID the mecanico id
	 * @throws BusinessException the business exception
	 */
	public void listContractsMechanic(int MecanicoID)throws BusinessException;
	
	/**
	 * Show contract.
	 *
	 * @param contratoID the contrato id
	 * @throws BusinessException the business exception
	 */
	public void showContract(int contratoID)throws BusinessException;
	
	/**
	 * Lis tipos contrato.
	 *
	 * @throws BusinessException the business exception
	 */
	public void lisTiposContrato()throws BusinessException;
	
	/**
	 * Delete contract.
	 *
	 * @param idContract the id contract
	 * @throws BusinessException the business exception
	 */
	public void deleteContract(int idContract) throws BusinessException;
	
	/**
	 * Genera nominas.
	 *
	 * @throws BusinessException the business exception
	 */
	public void generaNominas() throws BusinessException;
	
	/**
	 * Listar nominas empleado.
	 *
	 * @param idMecanico the id mecanico
	 * @throws BusinessException the business exception
	 */
	public void listarNominasEmpleado(int idMecanico) throws BusinessException;
	
	/**
	 * Listar nominas empleado profundidad.
	 *
	 * @param idMecanico the id mecanico
	 * @throws BusinessException the business exception
	 */
	public void listarNominasEmpleadoProfundidad(int idMecanico) throws BusinessException;
	
	/**
	 * Listcategorias.
	 *
	 * @throws BusinessException the business exception
	 */
	public void listcategorias()throws BusinessException;
	
	/**
	 * List tipos contrato.
	 *
	 * @throws BusinessException the business exception
	 */
	public void listTiposContrato() throws BusinessException;
	
	/**
	 * Adds the tipo contrato.
	 *
	 * @param nombre the nombre
	 * @param diasIndemnizacion the dias indemnizacion
	 * @throws BusinessException the business exception
	 */
	public void addTipoContrato(String nombre, int diasIndemnizacion)throws BusinessException;
	/*aparecerá un acumulado de salario bruto de los trabajadores de ese tipo de contrato 
	 * y el número de trabajadores.*/
	/**
	 * List mechanics by contract type.
	 *
	 * @param idContractType the id contract type
	 * @throws BusinessException the business exception
	 */
	public void listMechanicsByContractType(Long idContractType) throws BusinessException;
	//No se podrán borrar tipos de contrato que tengan contratos asociados.
	/**
	 * Delete contract type.
	 *
	 * @param idContractType the id contract type
	 * @throws BusinessException the business exception
	 */
	public void deleteContractType(Long idContractType)throws BusinessException;
	
	/**
	 * Update contarct type.
	 *
	 * @param id the id
	 * @param nombre the nombre
	 * @param diasIndemnizacion the dias indemnizacion
	 * @throws BusinessException the business exception
	 */
	public void updateContarctType(Long id,String nombre, int diasIndemnizacion)throws BusinessException;

}
