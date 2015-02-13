package uo.ri.busyness.impl;

import uo.ri.busyness.AdminService;
import uo.ri.busyness.impl.admin.AddMechanic;
import uo.ri.busyness.impl.admin.DeleteMechanic;
import uo.ri.busyness.impl.admin.ListMechanics;
import uo.ri.busyness.impl.admin.UpdateMechanic;
import uo.ri.common.BusinessException;

public class AdminServiceImpl implements AdminService
{

	@Override
	public void addMechanic(String nombre, String apellidos) throws BusinessException 
	{
		AddMechanic admechanic = new AddMechanic(nombre, apellidos);
		admechanic.execute();
		
	}

	@Override
	public void deleteMechanic(Long idMecanico) throws BusinessException 
	{
		DeleteMechanic delmechanic = new DeleteMechanic(idMecanico);
		delmechanic.execute();
		
	}

	@Override
	public void listMechanic() throws BusinessException 
	{
		ListMechanics listm = new ListMechanics();
		listm.execute();
		
	}

	@Override
	public void updateMechanic(Long id, String nombre, String apellidos) throws BusinessException
	{
		UpdateMechanic updateMechanic = new UpdateMechanic(id, nombre, apellidos);
		updateMechanic.execute();
		
	}

}
