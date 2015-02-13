package uo.ri.business.impl.admin;

import java.util.List;

import uo.ri.business.impl.Command;
import uo.ri.model.Mecanico;
import uo.ri.persistence.MecanicoFinder;

public class FindAllMechanics implements Command{

	public List<Mecanico> execute() {
		//named porque es una que sube de orm.xml
		return MecanicoFinder.findAll();
	//	return Jpa.getManager()
	//			.createNamedQuery("Mecanico.findAll", Mecanico.class)
	//			.getResultList();
	}

}
