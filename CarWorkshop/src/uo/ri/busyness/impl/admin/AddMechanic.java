package uo.ri.busyness.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;

import alb.util.jdbc.Jdbc;
import uo.ri.common.BusinessException;
import uo.ri.amp.conf.PersistentFactory;
import uo.ri.amp.persistence.MechanicGateway;

public class AddMechanic 
{

	private String nombre;
	private String apellidos;
	
	public AddMechanic(String nombre, String apellidos)
	{
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
				mg.addMechanic(nombre, apellidos);
			} catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				throw new RuntimeException(e);
			}
			
			
		
		
	}

}
