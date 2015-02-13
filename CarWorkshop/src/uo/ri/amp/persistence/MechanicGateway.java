package uo.ri.amp.persistence;

import java.sql.Connection;

// TODO: Auto-generated Javadoc
/**
 * The Interface MechanicGateway.
 */
public interface MechanicGateway 
{
	
	/**
	 * Sets the connection.
	 *
	 * @param conn the new connection
	 */
	public void setConnection(Connection conn);
	
	/**
	 * Adds the mechanic.
	 *
	 * @param nombre the nombre
	 * @param apellidos the apellidos
	 */
	public void addMechanic(String nombre, String apellidos);
	
	/**
	 * Delete mechanic.
	 *
	 * @param idMecanico the id mecanico
	 */
	public void deleteMechanic(Long idMecanico);
	
	/**
	 * List mechanics.
	 */
	public void listMechanics();
	
	/**
	 * List all mechanics.
	 */
	public void listAllMechanics();
	
	/**
	 * Update mechanic.
	 *
	 * @param id the id
	 * @param nombre the nombre
	 * @param apellidos the apellidos
	 */
	public void updateMechanic(Long id, String nombre, String apellidos);

}
