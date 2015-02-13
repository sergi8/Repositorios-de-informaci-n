package uo.ri.business.impl.admin;

import java.util.List;

import uo.ri.business.impl.Command;
import uo.ri.model.Mecanico;
import uo.ri.persistence.MecanicoFinder;

public class FindAllMechanics implements Command
{

	public List<Mecanico> execute() 
	{

		return MecanicoFinder.findAll();
		
	}

}
