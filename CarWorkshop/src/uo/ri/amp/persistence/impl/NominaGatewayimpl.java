package uo.ri.amp.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import uo.ri.amp.persistence.NominaGateway;
import alb.util.console.Console;
import alb.util.jdbc.Jdbc;

// TODO: Auto-generated Javadoc
/**
 * The Class NominaGatewayimpl.
 */
public class NominaGatewayimpl implements NominaGateway
{

	/** The conn. */
	private Connection conn;

	/* (non-Javadoc)
	 * @see uo.ri.amp.persistence.NominaGateway#setConnection(java.sql.Connection)
	 */
	@Override
	public void setConnection(Connection conn) 
	{
		this.conn=conn;
	}
	
	//salario base en contrato es el bruto anual , en nomina es es el salario mensual antes de los extras y las reducciones
	/* (non-Javadoc)
	 * @see uo.ri.amp.persistence.NominaGateway#generaNominas()
	 */
	@SuppressWarnings({ "deprecation", "resource", "unused" })
	@Override
	public void generaNominas() 
	{
		/* de aquí se sacan los datos para el contrato_id , para calcular  el salario base y el descuento por irpf
		 * y buscar lod porcentajes para calcula el extra de productividad y trienios*/
		double salario_base=0;
		double pagaExtra=0;
		double plusProductividad=0;
		double plusTrienio=0;
		double descuentoIRPF=0;
		double descuentoSeguridadSocial=0;
		double importeTotAverias=0;
		Calendar cal = Calendar.getInstance();
		
		String QUERYcontratos = "select id, salario_base, mecanico_id, categoria_id, fecha_contrato from tcontratos where estado = 'ACTIVO'";
		String Querycategoria = "select porcentaje_productividad, porcentaje_trienio from tcategorias where id = ?";
		String Queryaverias = "select importe from taverias where mecanico_id =? and fecha between '"+new SimpleDateFormat("YYYY-MM").format(cal.getTime())+"-01'"+" and now()";
		String SQL = "insert into tnominas (contrato_id, salario_base, paga_extra, plus, trienio, irpf, descuento, fecha) values (?,?,?,?,?,?,?,now())";
		PreparedStatement pst = null;
		ResultSet rsContratos = null;
		
		try {
			pst = conn.prepareStatement(QUERYcontratos);
			rsContratos = pst.executeQuery();
			while(rsContratos.next()) 
			{
				salario_base = rsContratos.getDouble(2)/14;
				if(cal.get(Calendar.MONTH)+1==12 ||cal.get(Calendar.MONTH)+1==6)
					pagaExtra = rsContratos.getDouble(2)/14;
				
				pst = conn.prepareStatement(Queryaverias);
				pst.setInt(1, rsContratos.getInt(3));
				ResultSet rs1Averias = pst.executeQuery();
				while(rs1Averias.next())
					importeTotAverias+=rs1Averias.getDouble(1);
				
				pst = conn.prepareStatement(Querycategoria);
				pst.setInt(1, rsContratos.getInt(4));
				ResultSet rsCategoria = pst.executeQuery();
				rsCategoria.next();
				plusProductividad = (importeTotAverias*rsCategoria.getDouble(1))/100;
				
				int aniostrabajados = restarFechas(rsContratos.getDate(5), cal.getTime())/365;
				plusTrienio = (aniostrabajados/3*rsCategoria.getDouble(2)*salario_base)/100;
				
				if(rsContratos.getDouble(2)<12.000)
				{
					descuentoIRPF= (rsContratos.getDouble(2)*7/100)/14;
				}else if(rsContratos.getDouble(2)<15.000)
				{
					descuentoIRPF= (rsContratos.getDouble(2)*10/100)/14;
				}else if(rsContratos.getDouble(2)<20.000)
				{
					descuentoIRPF= (rsContratos.getDouble(2)*13/100)/14;
				}else if(rsContratos.getDouble(2)<25.000)
				{
					descuentoIRPF= (rsContratos.getDouble(2)*17/100)/14;
				}else if(rsContratos.getDouble(2)<30.000)
				{
					descuentoIRPF= (rsContratos.getDouble(2)*20/100)/14;
				}else if(rsContratos.getDouble(2)>30.000)
				{
					descuentoIRPF= (rsContratos.getDouble(2)*25/100)/14;
				}
				descuentoSeguridadSocial = (salario_base+pagaExtra)*5/100;
				
				pst = conn.prepareStatement(SQL);
				pst.setInt(1, rsContratos.getInt(1));
				pst.setDouble(2, salario_base);
				pst.setDouble(3, pagaExtra);
				pst.setDouble(4, plusProductividad);
				pst.setDouble(5, plusTrienio);
				pst.setDouble(6, descuentoIRPF);
				pst.setDouble(7, descuentoSeguridadSocial);
				pst.executeUpdate();
				
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Jdbc.close(rsContratos, pst, conn);
		}
		
	}
	
	/**
	 * Restar fechas.
	 *
	 * @param fechaIn the fecha in
	 * @param fechaFinal the fecha final
	 * @return the int
	 */
	public static int restarFechas(Date fechaIn, Date fechaFinal )
	{
		GregorianCalendar fechaInicio= new GregorianCalendar();
		fechaInicio.setTime(fechaIn);
		GregorianCalendar fechaFin= new GregorianCalendar();
		fechaFin.setTime(fechaFinal);
		
		int dias = 0;
		if(fechaFin.get(Calendar.YEAR)==fechaInicio.get(Calendar.YEAR))
		{
			dias =(fechaFin.get(Calendar.DAY_OF_YEAR)- fechaInicio.get(Calendar.DAY_OF_YEAR))+1;
		}
		else
		{
			int rangoAnyos = fechaFin.get(Calendar.YEAR) - fechaInicio.get(Calendar.YEAR);
			for(int i=0;i<=rangoAnyos;i++)
			{
					int diasAnio = fechaInicio.isLeapYear(fechaInicio.get(Calendar.YEAR)) ? 366 : 365;
					if(i==0)
					{
						dias=1+dias +(diasAnio- fechaInicio.get(Calendar.DAY_OF_YEAR));
					}
					else
						if(i==rangoAnyos)
						{
							dias=dias +fechaFin.get(Calendar.DAY_OF_YEAR);
						}
						else
						{
							dias=dias+diasAnio;
						}
			}
		}
		return dias;
	}

	/* (non-Javadoc)
	 * @see uo.ri.amp.persistence.NominaGateway#ListarNominasEmpleado(int)
	 */
	@Override
	public void ListarNominasEmpleado(int id_mecanico) 
	{
		String SQL ="select n.salario_base, n.paga_extra, n.plus, n.trienio, n.irpf, n.descuento, n.fecha from tnominas n, tcontratos c where n.contrato_id = c.id and c.mecanico_id = ?";
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = conn.prepareStatement(SQL);
			pst.setInt(1, id_mecanico);
			rs = pst.executeQuery();
			while(rs.next()) 
			{
				String mes = rs.getString(7).split("-")[1];
				Double tNeto = rs.getDouble(1)+rs.getDouble(2)+rs.getDouble(3)+rs.getDouble(4)-rs.getDouble(5)-rs.getDouble(6);
				
				Console.printf("\t%s\n %s\n",  
					"Mes de la nómina = "+mes
					,  "Total neto percibido = "+tNeto+"€" 
				);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Jdbc.close(rs, pst, conn);
		}
		
	}

	/* (non-Javadoc)
	 * @see uo.ri.amp.persistence.NominaGateway#ListarNominasEmpleadoProfundidad(int)
	 */
	@Override
	public void ListarNominasEmpleadoProfundidad(int id_mecanico) 
	{
		String SQL ="select n.salario_base, n.paga_extra, n.plus, n.trienio, n.irpf, n.descuento, n.fecha from tnominas n, tcontratos c where n.contrato_id = c.id and c.mecanico_id = ?";
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = conn.prepareStatement(SQL);
			pst.setInt(1, id_mecanico);
			rs = pst.executeQuery();
			while(rs.next()) 
			{
				String mes = rs.getString(7).split("-")[1];
				Double tNeto = rs.getDouble(1)+rs.getDouble(2)+rs.getDouble(3)+rs.getDouble(4)-rs.getDouble(5)-rs.getDouble(6);
				
				Console.printf("\t%s\n %s\n %s\n %s\n %s\n %s\n %s\n %s\n",  
					"Mes de la nómina = "+mes
					,  "Total bruto percibido ="+rs.getDouble(1)+"€"
					,  "Total neto percibido = "+tNeto+"€" 
					,  "paga extra = "+rs.getDouble(2)+"€"
					,  "plus de productividad = "+rs.getDouble(3)+"€"
					,  "por trienios = "+rs.getDouble(4)+"€"
					,  "descuento por IRPF = "+rs.getDouble(5)+"€"
					,  "descuento por aportación a la seguridad social = "+rs.getDouble(6)+"€"
				);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Jdbc.close(rs, pst, conn);
		}
		
	}

}
