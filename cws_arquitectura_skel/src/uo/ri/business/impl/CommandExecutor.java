package uo.ri.business.impl;


import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import uo.ri.model.exception.BusinessException;
import uo.ri.persistence.util.Jpa;

public class CommandExecutor 
{

	public Object execute(Command command) throws BusinessException {
		
		EntityManager em = Jpa.createEntityManager();
		EntityTransaction trx = em.getTransaction();
		trx.begin();
		
		Object res;
		try {
			res = command.execute();
			trx.commit();
			return res;
		} catch (BusinessException e) {
			if(trx != null)
				if(trx.isActive())
					trx.rollback();
			throw e;
		} catch (PersistenceException pe) {
			if(trx != null)
				if(trx.isActive())
					trx.rollback();
			throw pe;
		} finally {
			if(em != null)
				if(em.isOpen())
					em.close();
		}
		
		
	}

}
