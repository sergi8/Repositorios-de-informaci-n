package uo.ri.business.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import uo.ri.model.exception.BusinessException;
import uo.ri.persistence.util.Jpa;

public class CommandExecutor implements Command{

	public Object execute(Command command) throws BusinessException {
		EntityManager em = Jpa.getManager();
		EntityTransaction trx = em.getTransaction();
		// Contexto persistencia abierto
		trx.begin();

		try {
			Object res = command.execute();
			trx.commit();
			return res;
		} catch (BusinessException e) {
			if (trx.isActive())
				trx.rollback();
			throw e;
		} catch (PersistenceException p) {
			if (trx.isActive())
				trx.rollback();
			throw p;
		} finally {
			if (em.isOpen())
				em.close();
		} //Contexto persistencia cerrado.
		return res;
	}

}
