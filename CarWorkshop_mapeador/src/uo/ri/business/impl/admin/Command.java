package uo.ri.business.impl.admin;

import uo.ri.model.exception.BusinessException;

public interface Command {
	
	Object execute() throws BusinessException;

}
