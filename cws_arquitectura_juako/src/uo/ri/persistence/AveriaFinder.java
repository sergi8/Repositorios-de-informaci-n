package uo.ri.persistence;

import java.util.List;

import uo.ri.model.Averia;
import uo.ri.persistence.util.Jpa;

public class AveriaFinder {

	public static List<Averia> findByIds(List<Long> idsAveria) {
		return Jpa.getManager()
				.createNamedQuery("Averia.findByIds", Averia.class)
				.setParameter(1, idsAveria).getResultList();
	}

	public static List<Averia> findNoFacturadasByDni(String dni) {

		return null;
	}

}
