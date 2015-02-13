package uo.ri.amp.business;

import java.util.List;

import uo.ri.amp.model.Categoria;
import uo.ri.amp.model.Contrato;
import uo.ri.amp.model.TipoContrato;
import uo.ri.model.Mecanico;
import uo.ri.model.exception.BusinessException;

public interface AdminService 
{

	void newMechanic(Mecanico mecanico) throws BusinessException;
	void deleteMechanic(Long idMecanico) throws BusinessException;
	void updateMechanic(Mecanico mecanico) throws BusinessException;

	Mecanico findMechanicById(Long id) throws BusinessException;
	List<Mecanico> findAllMechanics() throws BusinessException;
	List<Mecanico> findMechanics() throws BusinessException;
	
	void newContract(Contrato contrato) throws BusinessException;
	Contrato findContratoByID(Long id) throws BusinessException;
	void updateContract(Contrato contrato) throws BusinessException;
	List<Contrato> findAllcontractsFromMechanic(Long idMecanico)throws BusinessException;
	
	TipoContrato findTipoContratoByID(Long id) throws BusinessException;
	Categoria findCategoriaByID(Long id) throws BusinessException;

}
