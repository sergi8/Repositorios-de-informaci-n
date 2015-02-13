package uo.ri;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import uo.ri.model.Averia;
import uo.ri.model.Cliente;
import uo.ri.model.Factura;
import uo.ri.model.Intervencion;
import uo.ri.model.Mecanico;
import uo.ri.model.Repuesto;
import uo.ri.model.Sustitucion;
import uo.ri.model.TipoVehiculo;
import uo.ri.model.Vehiculo;
import uo.ri.model.exception.BusinessException;
import uo.ri.model.types.AveriaStatus;
import uo.ri.model.types.FacturaStatus;
import alb.util.date.DateUtil;


public class DomainModelTest {
	
	private Mecanico mecanico;
	private Averia averia;
	private Intervencion intervencion;
	private Repuesto repuesto;
	private Sustitucion sustitucion;
	private Vehiculo vehiculo;
	private TipoVehiculo tipoVehiculo;
	private Cliente cliente;

	@Before
	public void setUp() {
		tipoVehiculo = new TipoVehiculo("coche", 50.0);
		cliente = new Cliente("dni-cliente", "nombre", "apellidos");
		repuesto = new Repuesto("R1001", "junta la trocla", 100.0);

		vehiculo = new Vehiculo("1234 GJI", "ibiza", "seat");
		cliente.addVehiculo( vehiculo );
		tipoVehiculo.addVehiculo( vehiculo );
		
		averia = new Averia(vehiculo, "falla la junta la trocla");

		mecanico = new Mecanico("dni-mecanico", "nombre", "apellidos");
	
		intervencion = new Intervencion(mecanico, averia);
		intervencion.setMinutos(60);
		
		sustitucion = new Sustitucion(repuesto, intervencion);
		sustitucion.setCantidad(2);
	}
	
	@Test
	public void testImporteSustitucion() {
		assertTrue( sustitucion.getImporte() == 200.0 );
	}

	@Test
	public void testImporteIntervencion() {
		assertTrue( intervencion.getImporte() == 250.0 );
	}

	@Test
	public void testImporteAveria() {
		averia.calcularImporte();
		assertTrue( averia.getImporte() == 250.0 );
	}

	@Test
	public void testImporteAveriaConDosIntervenciones() {
		Intervencion i = new Intervencion(new Mecanico("1", "a", "n"), averia);
		i.setMinutos(30);
		
		averia.calcularImporte();
		assertTrue( averia.getImporte() == 275.0 );
	}

	@Test( expected = BusinessException.class )
	public void testAveriaNoTerminadaException() throws BusinessException {
		List<Averia> averias = new ArrayList<Averia>();
		averias.add( averia );
		new Factura( 0L,  averias ); // salta excepcion: la averia no esta terminada
	}

	@Test
	public void testImporteFactura() throws BusinessException {
		List<Averia> averias = new ArrayList<Averia>();
		averia.setStatus(AveriaStatus.TERMINADA);
		averias.add( averia );
		Factura factura = new Factura( 0L, averias );
		
		assertTrue( factura.getImporte() ==  302.5 );
	}

	@Test
	public void testImporteFacturaAddAveria() throws BusinessException {
		averia.setStatus(AveriaStatus.TERMINADA);
		Factura factura = new Factura( 0L ); // OL es el numero de factura
		factura.addAveria( averia );
		
		assertTrue( factura.getImporte() ==  302.5 );
	}

	@Test
	public void testImporteFacturaAddDosAverias() throws BusinessException {
		averia.setStatus(AveriaStatus.TERMINADA);
		Factura factura = new Factura( 0L );
		factura.addAveria( averia );
		factura.addAveria( crearOtraAveria() );
		
		assertTrue( factura.getImporte() ==  468.88 );

	}

	@Test
	public void testFacturaCreadaSinAbonar() throws BusinessException {
		List<Averia> averias = new ArrayList<Averia>();
		averia.setStatus(AveriaStatus.TERMINADA);
		averias.add( averia );
		Factura factura = new Factura( 0L, averias );
		
		assertTrue( factura.getStatus() ==  FacturaStatus.SIN_ABONAR );
	}

	@Test
	public void testImporteFacturaAntesDeJulio() throws BusinessException {
		Date _15_6_2012 = DateUtil.fromString("15/6/2012");
		
		List<Averia> averias = new ArrayList<Averia>();
		averia.setStatus(AveriaStatus.TERMINADA);
		averias.add( averia );
		Factura factura = new Factura( 0L, _15_6_2012, averias ); // iva 18%
		
		assertTrue( factura.getImporte() ==  295.0 );
	}

	@Test
	public void testAveriasFacturadas() throws BusinessException {
		List<Averia> averias = new ArrayList<Averia>();
		averia.setStatus(AveriaStatus.TERMINADA);
		averias.add( averia );
		new Factura( 0L, averias );
		
		assertTrue( averia.getStatus() == AveriaStatus.FACTURADA );
	}

	@Test
	public void testImporteFacturadDosAverias() throws BusinessException {
		List<Averia> averias = new ArrayList<Averia>();
		averia.setStatus(AveriaStatus.TERMINADA);
		averias.add( averia );
		averias.add( crearOtraAveria() );
		Factura factura = new Factura( 0L, averias );
		
		// importe = (137.5 nueva averia + 250.0 primera averia) * 21% iva
		assertTrue( factura.getImporte() ==  468.88 );
	}

	@Test
	public void testAveriasFacturadasAddAveria() throws BusinessException {
		averia.setStatus(AveriaStatus.TERMINADA);
		new Factura( 0L ).addAveria( averia );
		
		assertTrue( averia.getStatus() == AveriaStatus.FACTURADA );
	}

	@Test
	public void testDosAveriasFacturadasAddAveria() throws BusinessException {
		averia.setStatus(AveriaStatus.TERMINADA);
		Averia otraAveria = crearOtraAveria();
		
		Factura f = new Factura( 0L );
		f.addAveria( averia );
		f.addAveria( otraAveria );
		
		assertTrue( averia.getStatus() == AveriaStatus.FACTURADA );
		assertTrue( otraAveria.getStatus() == AveriaStatus.FACTURADA );
	}

	private Averia crearOtraAveria() {
		sleep( 100 );
		Averia averia = new Averia(vehiculo, "falla la junta la trocla otra vez");

		Intervencion intervencion = new Intervencion(mecanico, averia);
		intervencion.setMinutos( 45 );
		
		Sustitucion sustitucion = new Sustitucion(repuesto, intervencion);
		sustitucion.setCantidad( 1 );
		
		averia.setStatus( AveriaStatus.TERMINADA );
		
		// importe = 100 repuesto + 37.5 mano de obra
		return averia;
	}

	private void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// dont't care if this occurs
		}
	}

}
