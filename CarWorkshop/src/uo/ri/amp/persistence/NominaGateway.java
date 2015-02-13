package uo.ri.amp.persistence;

import java.sql.Connection;

// TODO: Auto-generated Javadoc
/**
 * The Interface NominaGateway.
 */
public interface NominaGateway 
{
	
	/**
	 * Sets the connection.
	 *
	 * @param conn the new connection
	 */
	public void setConnection(Connection conn);
	
	/**
	 * Genera nominas.
	 */
	public void generaNominas();
	
	/**
	 * Listar nominas empleado profundidad.
	 *
	 * @param id_mecanico the id_mecanico
	 */
	public void ListarNominasEmpleadoProfundidad(int id_mecanico);
	
	/**
	 * Listar nominas empleado.
	 *
	 * @param id_mecanico the id_mecanico
	 */
	public void ListarNominasEmpleado(int id_mecanico);

}
