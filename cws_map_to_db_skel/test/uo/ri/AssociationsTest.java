package uo.ri;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import uo.ri.model.Averia;
import uo.ri.model.Cargo;
import uo.ri.model.Cliente;
import uo.ri.model.Factura;
import uo.ri.model.Intervencion;
import uo.ri.model.Mecanico;
import uo.ri.model.Metalico;
import uo.ri.model.Repuesto;
import uo.ri.model.Sustitucion;
import uo.ri.model.TipoVehiculo;
import uo.ri.model.Vehiculo;
import uo.ri.model.exception.BusinessException;
import uo.ri.model.types.AveriaStatus;
import alb.util.date.DateUtil;


public class AssociationsTest {
	private Mecanico mecanico;
	private Averia averia;
	private Intervencion intervencion;
	private Repuesto repuesto;
	private Sustitucion sustitucion;
	private Vehiculo vehiculo;
	private TipoVehiculo tipoVehiculo;
	private Cliente cliente;
	private Factura factura;
	private Metalico metalico;
	private Cargo cargo;

	@Before
	public void setUp() throws BusinessException {
		tipoVehiculo = new TipoVehiculo("coche", 50.0);
		cliente = new Cliente("dni-cliente", "nombre", "apellidos");
		repuesto = new Repuesto("R1001", "junta la trocla", 100.0);

		vehiculo = new Vehiculo("1234 GJI", "seat", "ibiza");
		cliente.addVehiculo( vehiculo );
		tipoVehiculo.addVehiculo( vehiculo );
		
		averia = new Averia(vehiculo, "falla la junta la trocla");

		mecanico = new Mecanico("dni-mecanico", "nombre", "apellidos");
	
		intervencion = new Intervencion(mecanico, averia);
		intervencion.setMinutos(60);
		
		sustitucion = new Sustitucion(repuesto, intervencion);
		sustitucion.setCantidad(2);
		
		factura = new Factura(0L, DateUtil.today());
		averia.setStatus(AveriaStatus.TERMINADA);
		factura.addAveria( averia );
		
		metalico = new Metalico( cliente );
		
		cargo = new Cargo(factura, metalico, 100.0);
	}
	
	@Test
	public void testPoseerAdd() throws BusinessException {
		assertTrue( cliente.getVehiculos().contains( vehiculo ));
		assertTrue( vehiculo.getCliente() == cliente );
	}

	@Test
	public void testPoseerRemove() throws BusinessException {
		cliente.removeVehiculo(vehiculo);

		assertTrue( ! cliente.getVehiculos().contains( vehiculo ));
		assertTrue( vehiculo.getCliente() == null );
	}

	@Test
	public void testSerAdd() throws BusinessException {
		assertTrue( tipoVehiculo.getVehiculos().contains( vehiculo ));
		assertTrue( vehiculo.getTipo() == tipoVehiculo );
	}

	@Test
	public void testSerRemove() throws BusinessException {
		tipoVehiculo.removeVehiculo(vehiculo);

		assertTrue( ! tipoVehiculo.getVehiculos().contains( vehiculo ));
		assertTrue( vehiculo.getTipo() == null );
	}

	@Test
	public void testTenerAdd() throws BusinessException {
		assertTrue( vehiculo.getAverias().contains( averia ));
		assertTrue( averia.getVehiculo() == vehiculo );
		assertTrue( vehiculo.getNumAverias() == 1 );
	}

	@Test
	public void testTenerRemove() throws BusinessException {
		vehiculo.removeAveria( averia );
		
		assertTrue( ! vehiculo.getAverias().contains( averia ));
		assertTrue( averia.getVehiculo() == null );
		assertTrue( vehiculo.getNumAverias() == 0 );
	}

	@Test
	public void testArreglarAdd() throws BusinessException {
		assertTrue( averia.getIntervenciones().contains( intervencion ));
		assertTrue( intervencion.getAveria() == averia );
	}

	@Test
	public void testArreglarRemove() throws BusinessException {
		intervencion.unlink();
		
		assertTrue( ! averia.getIntervenciones().contains( intervencion ));
		assertTrue( averia.getIntervenciones().size() == 0 );
		assertTrue( intervencion.getAveria() == null );
	}

	@Test
	public void testTrabajarAdd() throws BusinessException {
		assertTrue( mecanico.getIntervenciones().contains( intervencion ));
		assertTrue( intervencion.getMecanico() == mecanico );
	}

	@Test
	public void testTrabajarRemove() throws BusinessException {
		intervencion.unlink();
		
		assertTrue( ! mecanico.getIntervenciones().contains( intervencion ));
		assertTrue( mecanico.getIntervenciones().size() == 0 );
		assertTrue( intervencion.getMecanico() == null );
	}

	@Test
	public void testAsignarAdd() throws BusinessException {
		mecanico.addAveria( averia );
		
		assertTrue( mecanico.getAsignadas().contains( averia ));
		assertTrue( averia.getMecanico() == mecanico );
	}

	@Test
	public void testAsignarRemove() throws BusinessException {
		mecanico.removeAveria( averia );
		
		assertTrue( ! mecanico.getAsignadas().contains( averia ));
		assertTrue( mecanico.getAsignadas().size() == 0 );
		assertTrue( averia.getMecanico() == null );
	}

	@Test
	public void testSustituirAdd() throws BusinessException {
		assertTrue( sustitucion.getIntervencion().equals( intervencion ));
		assertTrue( sustitucion.getRepuesto().equals( repuesto ));
		
		assertTrue( repuesto.getSustituciones().contains( sustitucion ));
		assertTrue( intervencion.getSustituciones().contains( sustitucion ));
	}

	@Test
	public void testSustituirRemove() throws BusinessException {
		sustitucion.unlink();
		
		assertTrue( sustitucion.getIntervencion() == null);
		assertTrue( sustitucion.getRepuesto() == null);
		
		assertTrue( ! repuesto.getSustituciones().contains( sustitucion ));
		assertTrue( repuesto.getSustituciones().size() == 0 );

		assertTrue( ! intervencion.getSustituciones().contains( sustitucion ));
		assertTrue( intervencion.getSustituciones().size() == 0 );
	}

	@Test
	public void testFacturarAdd() throws BusinessException {
		assertTrue( factura.getAverias().contains( averia ));
		assertTrue( averia.getFactura() == factura);
	}

	@Test
	public void testFacturarRemove() throws BusinessException {
		factura.removeAveria( averia );
		
		assertTrue( ! factura.getAverias().contains( averia ));
		assertTrue( factura.getAverias().size() == 0 );
		assertTrue( averia.getFactura() == null);
		
		assertTrue( factura.getImporte() == 0.0 );
	}

	@Test
	public void testPagarAdd() throws BusinessException {
		assertTrue( cliente.getMediosPago().contains( metalico ));
		assertTrue( metalico.getCliente() == cliente );
	}

	@Test
	public void testPagarRemove() throws BusinessException {
		cliente.removeMedioPago( metalico );
		
		assertTrue( ! cliente.getMediosPago().contains( metalico ));
		assertTrue( cliente.getMediosPago().size() == 0 );
		assertTrue( metalico.getCliente() == null );
	}

	@Test
	public void testCargarAdd() throws BusinessException {
		assertTrue( metalico.getCargos().contains( cargo ));
		assertTrue( factura.getCargos().contains( cargo ));
		
		assertTrue( cargo.getFactura() == factura );
		assertTrue( cargo.getMedioPago() == metalico );
		
		assertTrue( metalico.getAcumulado() == 100.0 );
	}

	@Test
	public void testCargarRemove() throws BusinessException {
		cargo.unlink();
		
		assertTrue( ! metalico.getCargos().contains( cargo ));
		assertTrue( metalico.getCargos().size() == 0 );

		assertTrue( ! factura.getCargos().contains( cargo ));
		assertTrue( metalico.getCargos().size() == 0 );
		
		assertTrue( cargo.getFactura() == null );
		assertTrue( cargo.getMedioPago() == null );
		
		assertTrue( metalico.getAcumulado() == 0.0 );
	}

}
