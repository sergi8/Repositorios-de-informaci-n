package uo.ri.amp.busyness.impl;

import uo.ri.amp.busyness.AdminService;
import uo.ri.amp.busyness.impl.admin.*;
import uo.ri.busyness.impl.admin.AddMechanic;
import uo.ri.busyness.impl.admin.UpdateMechanic;
import uo.ri.common.BusinessException;

// TODO: Auto-generated Javadoc
/**
 * implementacion de adminService. La capa de presentación no conoce esta clase, únicamente su interfáz.
 * La usa a traves de la factoría que le da una instancia.
 * 
 * engloba todas las acciones que puede realizar el administrador.
 * para ejecutar cada accion emplea una instancia de la clase que la implementa
 * @author uo227806
 *
 */
public class AdminServiceImpl implements AdminService
{
	/**
	 * 
	 */
	@Override
	public void addMechanic(String nombre, String apellidos) throws BusinessException 
	{
		AddMechanic admechanic = new AddMechanic(nombre, apellidos);
		admechanic.execute();
		
	}
	/**
	 * 
	 */
	@Override
	public void deleteMechanic(Long idMecanico) throws BusinessException 
	{
		DeleteMechanic delmechanic = new DeleteMechanic(idMecanico);
		delmechanic.execute();
		
	}
	/**
	 * @throws BusinessExceptionException 
	 * 
	 */
	@Override
	public void listMechanic() throws BusinessException
	{
		ListMechanics listMechanics = new ListMechanics();
		listMechanics.execute();
		
	}
	
	/**
	 * 
	 */
	@Override
	public void ListAllMechanics() throws BusinessException 
	{
		ListAllMechanics listm = new ListAllMechanics();
		listm.execute();
	}
	/**
	 * 
	 */
	@Override
	public void updateMechanic(Long id, String nombre, String apellidos) throws BusinessException
	{
		UpdateMechanic updateMechanic = new UpdateMechanic(id, nombre, apellidos);
		updateMechanic.execute();
		
	}
	
	/* (non-Javadoc)
	 * @see uo.ri.amp.busyness.AdminService#addContract(int, java.lang.String, java.lang.String, double, java.lang.String, java.lang.String)
	 */
	@Override
	public void addContract(int idMecanico,String tipoContrato, String categoría, double salarioBase, String fechaContrato, String fechaFin) throws BusinessException
	{
		AddContract addContract = new AddContract(idMecanico,tipoContrato,categoría,salarioBase,fechaContrato,fechaFin);
		addContract.execute();
	}
	
	/* (non-Javadoc)
	 * @see uo.ri.amp.busyness.AdminService#UpdateContract(int, java.lang.String, java.lang.String, double, java.lang.String, java.lang.String)
	 */
	@Override
	public void UpdateContract(int id_contrato, String tipoContrato,String categoria,double salarioBase,String fechaContrato,String fechaFin) throws BusinessException 
	{
		UpdateContract updateContact = new UpdateContract(id_contrato, tipoContrato, categoria, salarioBase, fechaContrato, fechaFin);
		updateContact.execute();
	}
	
	/* (non-Javadoc)
	 * @see uo.ri.amp.busyness.AdminService#DeleteContrac()
	 */
	@Override
	public void DeleteContrac() throws BusinessException 
	{
		// TODO Auto-generated method stub
		
	}
	
	/* (non-Javadoc)
	 * @see uo.ri.amp.busyness.AdminService#FinalizarContrato(java.lang.Long)
	 */
	@Override
	public void FinalizarContrato(Long idContrato) throws BusinessException 
	{
		FinalizarContrato finalizarContrato = new FinalizarContrato(idContrato);
		finalizarContrato.execute();
	}
	
	/* (non-Javadoc)
	 * @see uo.ri.amp.busyness.AdminService#ExtinguirContrato(java.lang.Long)
	 */
	@Override
	public void ExtinguirContrato(Long idContrato) throws BusinessException 
	{
		ExtinguirContrato extinguirContrato = new ExtinguirContrato(idContrato);
		extinguirContrato.execute();
		
	}
	
	/* (non-Javadoc)
	 * @see uo.ri.amp.busyness.AdminService#listContractsMechanic(int)
	 */
	@Override
	public void listContractsMechanic(int MecanicoID) throws BusinessException 
	{
		ListContractsMechanic listContractsMechanic= new ListContractsMechanic(MecanicoID);
		listContractsMechanic.execute();
		
	}
	
	/* (non-Javadoc)
	 * @see uo.ri.amp.busyness.AdminService#showContract(int)
	 */
	@Override
	public void showContract(int contratoID) throws BusinessException 
	{
		ShowContract showContract = new ShowContract(contratoID);
		showContract.execute();
		
	}
	
	/* (non-Javadoc)
	 * @see uo.ri.amp.busyness.AdminService#lisTiposContrato()
	 */
	@Override
	public void lisTiposContrato() throws BusinessException
	{
		ListTiposContrato listTiposContrato = new ListTiposContrato();
		listTiposContrato.execute();
		
	}
	
	/* (non-Javadoc)
	 * @see uo.ri.amp.busyness.AdminService#listcategorias()
	 */
	@Override
	public void listcategorias() throws BusinessException 
	{
		Listcategorias listcategorias = new Listcategorias();
		listcategorias.execute();
		
	}
	
	/* (non-Javadoc)
	 * @see uo.ri.amp.busyness.AdminService#deleteContract(int)
	 */
	@Override
	public void deleteContract(int idContract) throws BusinessException 
	{
		DeleteContract deleteContract = new DeleteContract(idContract);
		deleteContract.execute();
		
	}
	
	/* (non-Javadoc)
	 * @see uo.ri.amp.busyness.AdminService#generaNominas()
	 */
	@Override
	public void generaNominas() throws BusinessException 
	{
		GeneraNominas generanominas = new GeneraNominas();
		generanominas.execute();
		
	}
	
	/* (non-Javadoc)
	 * @see uo.ri.amp.busyness.AdminService#listarNominasEmpleado(int)
	 */
	@Override
	public void listarNominasEmpleado(int idMecanico) throws BusinessException 
	{
		ListarNominasEmpleado listarNominasEmpleado = new ListarNominasEmpleado(idMecanico);
		listarNominasEmpleado.execute();
		
	}
	
	/* (non-Javadoc)
	 * @see uo.ri.amp.busyness.AdminService#listarNominasEmpleadoProfundidad(int)
	 */
	@Override
	public void listarNominasEmpleadoProfundidad(int idMecanico) throws BusinessException 
	{
		ListarNominasEmpleadoProfundidad listarNominasEmpleadoProfundidad = new ListarNominasEmpleadoProfundidad(idMecanico);
		listarNominasEmpleadoProfundidad.execute();
		
	}
	
	/* (non-Javadoc)
	 * @see uo.ri.amp.busyness.AdminService#addTipoContrato(java.lang.String, int)
	 */
	@Override
	public void addTipoContrato(String nombre, int diasIndemnizacion) throws BusinessException 
	{
		AddContractType addContractType = new AddContractType(nombre, diasIndemnizacion);
		addContractType.execute();
		
	}
	
	/* (non-Javadoc)
	 * @see uo.ri.amp.busyness.AdminService#deleteContractType(java.lang.Long)
	 */
	@Override
	public void deleteContractType(Long idContractType)throws BusinessException 
	{
		DeleteContractType deleteContractType = new DeleteContractType(idContractType);
		deleteContractType.execute();
		
	}
	
	/* (non-Javadoc)
	 * @see uo.ri.amp.busyness.AdminService#updateContarctType(java.lang.Long, java.lang.String, int)
	 */
	@Override
	public void updateContarctType(Long id, String nombre, int diasIndemnizacion) throws BusinessException
	{
		UpdateContarctType updateContarctType = new UpdateContarctType(id, nombre, diasIndemnizacion);
		updateContarctType.execute();
		
	}
	
	/* (non-Javadoc)
	 * @see uo.ri.amp.busyness.AdminService#listTiposContrato()
	 */
	@Override
	public void listTiposContrato() throws BusinessException 
	{
		ListTiposContrato listTiposContrato = new ListTiposContrato();
		listTiposContrato.execute();
		
	}
	
	/* (non-Javadoc)
	 * @see uo.ri.amp.busyness.AdminService#listMechanicsByContractType(java.lang.Long)
	 */
	@Override
	public void listMechanicsByContractType(Long idContractType)throws BusinessException 
	{
		ListMechanicsByContractType listMechanicsByContractType = new ListMechanicsByContractType(idContractType);
		listMechanicsByContractType.execute();
	}
	

}
