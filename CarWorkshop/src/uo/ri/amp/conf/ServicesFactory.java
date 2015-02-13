package uo.ri.amp.conf;

import uo.ri.amp.busyness.AdminService;
import uo.ri.busyness.CashService;
import uo.ri.amp.busyness.impl.AdminServiceImpl;
import uo.ri.busyness.impl.CashServiceImpl;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Services objects.
 */
public class ServicesFactory 
{
	
	/**
	 * Gets the admin service.
	 *
	 * @return the admin service
	 */
	public static AdminService getAdminService()
	{
		return new AdminServiceImpl();
	}
	
	/**
	 * Gets the cash service.
	 *
	 * @return the cash service
	 */
	public static CashService getCashService()
	{
		return new CashServiceImpl();
	}

}
