package uo.ri.conf;

import uo.ri.business.AdminService;
import uo.ri.business.CashService;
import uo.ri.business.ForemanService;
import uo.ri.business.MechanicService;
import uo.ri.business.impl.AdminServiceImpl;
import uo.ri.business.impl.CashServiceImpl;

public class ServicesFactory {

	public static AdminService getAdminService() {
		return new AdminServiceImpl();
	}

	public static CashService getCashService() {
		return new CashServiceImpl();
	}

	public static MechanicService getMechanicService() {
		return null;
	}

	public static ForemanService getForemanService() {
		return null;
	}
}
