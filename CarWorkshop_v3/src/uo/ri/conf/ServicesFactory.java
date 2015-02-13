package uo.ri.conf;

import uo.ri.busyness.AdminService;
import uo.ri.busyness.CashService;
import uo.ri.busyness.impl.AdminServiceImpl;
import uo.ri.busyness.impl.CashServiceImpl;

public class ServicesFactory 
{
	public static AdminService getAdminService()
	{
		return new AdminServiceImpl();
	}
	
	public static CashService getCashService()
	{
		return new CashServiceImpl();
	}

}
