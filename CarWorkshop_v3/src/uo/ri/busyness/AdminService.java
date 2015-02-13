package uo.ri.busyness;

import uo.ri.common.BusinessException;

public interface AdminService 
{
	public void addMechanic(String nombre, String apellido) throws BusinessException;
	public void deleteMechanic(Long idMecanico)throws BusinessException;
	public void listMechanic()throws BusinessException;
	public void updateMechanic(Long id, String nombre, String apellidos)throws BusinessException;

}
