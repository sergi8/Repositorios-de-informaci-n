package uo.ri.amp.business.impl;

import java.util.List;

import uo.ri.amp.business.AdminService;
import uo.ri.amp.business.impl.admin.AddContract;
import uo.ri.amp.business.impl.admin.DeleteMechanic;
import uo.ri.amp.business.impl.admin.FindCategoriaById;
import uo.ri.amp.business.impl.admin.FindContractsFromMechanic;
import uo.ri.amp.business.impl.admin.FindContratoByID;
import uo.ri.amp.business.impl.admin.FindMechanics;
import uo.ri.amp.business.impl.admin.FindTipoContratoById;
import uo.ri.amp.business.impl.admin.UpdateContract;
import uo.ri.amp.model.Categoria;
import uo.ri.amp.model.Contrato;
import uo.ri.amp.model.TipoContrato;
import uo.ri.business.impl.admin.AddMechanic;
import uo.ri.business.impl.admin.FindAllMechanics;
import uo.ri.business.impl.admin.FindMechanicById;
import uo.ri.business.impl.admin.UpdateMechanic;
import uo.ri.model.Mecanico;
import uo.ri.model.exception.BusinessException;

public class AdminServiceImpl implements AdminService 
{
	
	private CommandExecutor executor = new CommandExecutor();

	@Override
	public void newMechanic(Mecanico mecanico) throws BusinessException 
	{
		executor.execute(new AddMechanic( mecanico ));
	}

	@Override
	public void updateMechanic(Mecanico mecanico) throws BusinessException {
		executor.execute(new UpdateMechanic( mecanico ));
	}

	@Override
	public void deleteMechanic(Long idMecanico) throws BusinessException {
		executor.execute(new DeleteMechanic(idMecanico));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Mecanico> findAllMechanics() throws BusinessException 
	{
		return (List<Mecanico>) executor.execute(new FindAllMechanics());
	}

	@Override
	public Mecanico findMechanicById(Long id) throws BusinessException 
	{
		return (Mecanico) executor.execute(new FindMechanicById(id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Mecanico> findMechanics() throws BusinessException 
	{
		return (List<Mecanico>) executor.execute(new FindMechanics());
	}

	@Override
	public void newContract(Contrato contrato) throws BusinessException 
	{
		executor.execute(new AddContract(contrato));
		
	}
	@Override
	public Contrato findContratoByID(Long id) throws BusinessException 
	{
		return (Contrato) executor.execute(new FindContratoByID(id));
	}

	@Override
	public void updateContract(Contrato contrato) throws BusinessException 
	{
		executor.execute(new UpdateContract(contrato));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contrato> findAllcontractsFromMechanic(Long idMecanico)	throws BusinessException 
	{
		return (List<Contrato>) executor.execute(new FindContractsFromMechanic(idMecanico));
	}
	
	@Override
	public TipoContrato findTipoContratoByID(Long id)throws BusinessException 
	{
		return (TipoContrato) executor.execute(new FindTipoContratoById(id));
	}

	@Override
	public Categoria findCategoriaByID(Long id) throws BusinessException 
	{
		return (Categoria) executor.execute(new FindCategoriaById(id));
	}



}
