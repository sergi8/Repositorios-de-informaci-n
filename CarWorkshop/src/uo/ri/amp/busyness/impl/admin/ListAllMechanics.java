package uo.ri.amp.busyness.impl.admin;

import java.sql.Connection;
import java.sql.SQLException;

import uo.ri.common.BusinessException;
import uo.ri.amp.conf.PersistentFactory;
import uo.ri.amp.persistence.MechanicGateway;
import alb.util.jdbc.Jdbc;
import alb.util.menu.Action;
// TODO: Auto-generated Javadoc
/**
 * esta clase se encarga de mandarle a la capa de persistenciaa que busque e imprima
 * una lista de todos los mecánicos de la base de datos
 * @author uo227806
 *
 */
public class ListAllMechanics implements Action 
{
	/**
	 * método execute definido en la interfáz Actionn
	 */
	@Override
	public void execute() throws BusinessException 
	{
		Connection conn = null;

		try {
			conn = Jdbc.getConnection();
			MechanicGateway mg = PersistentFactory.getMechanicGateway();
			mg.setConnection(conn);
			mg.listAllMechanics();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
}
