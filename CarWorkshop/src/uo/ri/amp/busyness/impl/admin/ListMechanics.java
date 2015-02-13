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
 * una lista de los mecánicos de la base de datos que tienen contrato en vigor
 * @author uo227806
 *
 */
public class ListMechanics implements Action
{
	/**
	 * metodo de Action
	 */
	@Override
	public void execute() throws BusinessException 
	{
		Connection conn = null;

		try {
			conn = Jdbc.getConnection();
			MechanicGateway mg = PersistentFactory.getMechanicGateway();
			mg.setConnection(conn);
			mg.listMechanics();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

}
