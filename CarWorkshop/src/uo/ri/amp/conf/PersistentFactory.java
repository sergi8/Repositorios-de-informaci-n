package uo.ri.amp.conf;

import uo.ri.amp.persistence.ContractGateway;
import uo.ri.amp.persistence.MechanicGateway;
import uo.ri.amp.persistence.NominaGateway;
import uo.ri.amp.persistence.impl.ContractGatewayImpl;
import uo.ri.amp.persistence.impl.MechanicGatewayImpl;
import uo.ri.amp.persistence.impl.NominaGatewayimpl;
import uo.ri.persistence.CashGateway;
import uo.ri.persistence.DamageGateway;
import uo.ri.persistence.impl.CashGatewayImpl;
import uo.ri.persistence.impl.DamageGatewayImpl;

// TODO: Auto-generated Javadoc
/**
 * A factory for creating Persistent objects.
 */
public class PersistentFactory 
{
	
	/**
	 * Gets the cash gateway.
	 *
	 * @return the cash gateway
	 */
	public static CashGateway getCashGateway()
	{
		return new CashGatewayImpl();
	}
	
	/**
	 * Gets the damage gateway.
	 *
	 * @return the damage gateway
	 */
	public static DamageGateway getDamageGateway()
	{
		return new DamageGatewayImpl();
	}
	
	/**
	 * Gets the mechanic gateway.
	 *
	 * @return the mechanic gateway
	 */
	public static MechanicGateway getMechanicGateway()
	{
		return new MechanicGatewayImpl();
	}
	
	/**
	 * Gets the contract gateway.
	 *
	 * @return the contract gateway
	 */
	public static ContractGateway getContractGateway()
	{
		return new ContractGatewayImpl();
	}
	
	/**
	 * Gets the nomina gateway.
	 *
	 * @return the nomina gateway
	 */
	public static NominaGateway getNominaGateway()
	{
		return new NominaGatewayimpl();
	}

}
