package uo.ri.business;

import java.util.List;

import uo.ri.model.Mecanico;
import uo.ri.model.exception.BusinessException;

public interface AdminService {

	void newMechanic(Mecanico mecanico) throws BusinessException;
	void deleteMechanic(Long idMecanico) throws BusinessException;
	void updateMechanic(Mecanico mecanico) throws BusinessException;

	Mecanico findMechanicById(Long id) throws BusinessException;
	List<Mecanico> findAllMechanics() throws BusinessException;
	
	// resto de métodos que faltan...

}
