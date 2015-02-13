package uo.ri.business.impl;

import uo.ri.model.exception.BusinessException;

public interface Command {
	Object execute() throws BusinessException;

}
