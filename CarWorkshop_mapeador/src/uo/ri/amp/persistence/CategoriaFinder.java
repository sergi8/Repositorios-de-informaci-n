package uo.ri.amp.persistence;

import uo.ri.amp.model.Categoria;
import uo.ri.persistence.util.Jpa;

public class CategoriaFinder 
{
	public static Categoria findByNombre(Long id) 
	{
		return Jpa.getManager().find(Categoria.class, id);
	}

}
