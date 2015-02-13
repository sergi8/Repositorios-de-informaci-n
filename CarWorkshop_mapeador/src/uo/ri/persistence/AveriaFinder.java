package uo.ri.persistence;

import java.util.List;

import uo.ri.model.Averia;
import uo.ri.persistence.util.Jpa;

public class AveriaFinder {

	@SuppressWarnings("unchecked")
	public static List<Averia> findByIds(List<Long> idsAveria) {
		return Jpa.getManager().createNamedQuery("Averia.findByIds").setParameter(1, idsAveria).getResultList();
	}

	@SuppressWarnings("unchecked")
	public static List<Averia> findNoFacturadasByDni(String dni) {
		return Jpa.getManager().createNamedQuery("Averia.findNoFacturadasByDni").setParameter(1, dni).getResultList();
	}



}
