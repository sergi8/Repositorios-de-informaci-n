package uo.ri.busyness.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;

import uo.ri.common.BusinessException;
import uo.ri.amp.conf.PersistentFactory;
import uo.ri.amp.persistence.MechanicGateway;
import alb.util.jdbc.Jdbc;

public class UpdateMechanic
{

	private Long id;
	private String nombre;
	private String apellidos;

	public UpdateMechanic(Long id, String nombre, String apellidos) 
	{
		
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	public void execute() throws BusinessException 
	{
		
		// Procesar
		Connection conn = null;

		try {
			conn = Jdbc.getConnection();
			MechanicGateway mg = PersistentFactory.getMechanicGateway();
			mg.setConnection(conn);
			mg.updateMechanic(id, nombre, apellidos);
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
}
