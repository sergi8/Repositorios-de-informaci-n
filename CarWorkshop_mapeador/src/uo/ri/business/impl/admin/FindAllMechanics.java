package uo.ri.business.impl.admin;

import java.util.List;

import uo.ri.amp.persistence.MecanicoFinder;
import uo.ri.model.Mecanico;

public class FindAllMechanics implements Command
{

	public List<Mecanico> execute() 
	{
		
		return MecanicoFinder.findAll();
		
	}

}
