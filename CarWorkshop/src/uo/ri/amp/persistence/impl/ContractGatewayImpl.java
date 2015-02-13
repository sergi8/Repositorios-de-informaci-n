package uo.ri.amp.persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import uo.ri.amp.persistence.ContractGateway;
import alb.util.console.Console;
import alb.util.jdbc.Jdbc;

// TODO: Auto-generated Javadoc
/**
 * The Class ContractGatewayImpl.
 */
public class ContractGatewayImpl implements ContractGateway
{
	
	/** The conn. */
	private Connection conn;

	
	/* (non-Javadoc)
	 * @see uo.ri.amp.persistence.ContractGateway#setConnection(java.sql.Connection)
	 */
	@Override
	public void setConnection(Connection conn) 
	{
		this.conn=conn;
	}
	
	
	/* (non-Javadoc)
	 * @see uo.ri.amp.persistence.ContractGateway#listContractsMechanic(int)
	 */
	@Override
	public void listContractsMechanic(int MecanicoID) 
	{
		String SQL="select id, estado, tipo_id, fecha_contrato, fecha_fin, categoria_id, salario_base from tcontratos where mecanico_id = '"+MecanicoID+"' order by estado";
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = conn.prepareStatement(SQL);
			rs = pst.executeQuery();
			while(rs.next()) 
			{
				String Query = "select count(*) from tnominas, tcontratos where contrato_id =?";
				pst = conn.prepareStatement(Query);
				pst.setInt(1, rs.getInt(1));
				ResultSet rs1 = pst.executeQuery();
				rs1.next();
				Console.printf("\t%s\n \t%s\n \t%s\n \t%s\n\t%s\n \t%s\n \t%s\n \t%s\n\n",  
						"ID - "+rs.getInt(1)
					,	"Estado -"+ rs.getString(2)
					,  "Tipo - "+getNameToId(rs.getInt(3),"ttiposcontrato")
					,  "fecha de inicio del contrato - "+rs.getString(4)
					,  "fecha de finalización del contrato - "+rs.getString(5)
					,  "categoría del contrato - "+getNameToId(rs.getInt(6),"tcategorias")
					,  "salario base bruto anual - "+rs.getDouble(7)
					,  "nominas cobradas - "+rs1.getInt(1) 
				);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Jdbc.close(rs, pst, conn);
		}
		
	}

	
	/* (non-Javadoc)
	 * @see uo.ri.amp.persistence.ContractGateway#addContract(int, java.lang.String, java.lang.String, double, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("resource")
	@Override
	public void addContract(int idMecanico, String tipoContrato,String categoría, double salarioBase, String fechaContrato,String fechaFin) 
	{
		//sentencia SQL a ejecutar
		String SQL;
		//sentencia empleada en el caso de no especificar la fecha de fin de contrato
		if(fechaFin == null)
			SQL ="insert into tcontratos (mecanico_id, tipo_id, estado,categoria_id,fecha_contrato, salario_base) values(?, ?, ?, ?, ?,?)";
		//sentencia a ejecutar en caso de estar especificada la fecha de fin de contrato
		else
			SQL = "insert into tcontratos (mecanico_id, tipo_id, estado,categoria_id,fecha_contrato, salario_base, fecha_fin) values(?,?,?,?,?,?,?)";
		
		ResultSet rs = null;
		PreparedStatement pst = null;
		
		try {
			//en caso de tener el mecánico un contrato activo anterior este pasa al estado EXTINTO
			{
				//sentencia para modificar el estado del contrato activo antiguo
				String SQL_borraExtintos ="update tcontratos set estado = 'EXTINTO' where mecanico_id ="+"'"+idMecanico+"'";
				pst = conn.prepareStatement(SQL_borraExtintos);
				pst.executeUpdate();
			}
			
			//se inserta el nuevo contrato con estado ACTIVO
			pst = conn.prepareStatement(SQL);
			pst.setInt(1, idMecanico);
			pst.setInt(2, getIdTipeFromName(tipoContrato,"ttiposcontrato"));
			pst.setString(3, "ACTIVO");
			pst.setInt(4, getIdTipeFromName(categoría,"tcategorias"));
			pst.setString(5,fechaContrato);
			pst.setDouble(6, salarioBase);
			if(fechaFin !=null)
				pst.setString(7, fechaFin);
			
			pst.executeUpdate();
			
		} catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}finally {
			Jdbc.close(rs, pst, conn);
		}
		
	}
	
	/**
	 * Gets the id tipe from name.
	 *
	 * @param name the name
	 * @param tabla the tabla
	 * @return the id tipe from name
	 */
	private int getIdTipeFromName(String name, String tabla)
	{
		String QUERY = "select id from "+tabla+" where nombre = '"+name+"'";
		java.sql.Statement stm;
		try {
			stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(QUERY);
			rs.next();
			return rs.getInt(1);
		} catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Gets the name to id.
	 *
	 * @param id the id
	 * @param table the table
	 * @return the name to id
	 */
	private String getNameToId(int id, String table)
	{
		String QUERY = "select nombre from "+table+" where id = '"+id+"'";
		java.sql.Statement stm;
		try {
			stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(QUERY);
			rs.next();
			return rs.getString(1);
		} catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}
	}

	
	/* (non-Javadoc)
	 * @see uo.ri.amp.persistence.ContractGateway#UpdateContract(int, java.lang.String, java.lang.String, double, java.lang.String, java.lang.String)
	 */
	@Override
	public void UpdateContract(int id_contrato, String tipoContrato, String categoria,double salarioBase, String fechaContrato, String fechaFin) 
	{
				String SQL;
				//sentencia empleada para actualizar los datos de la tabla
				SQL ="update tcontratos set tipo_id= ?, categoria_id = ?, salario_base = ?, fecha_contrato = ?, fecha_fin = ? where id = ?";
				
				ResultSet rs = null;
				PreparedStatement pst = null;
				
				try {
					pst = conn.prepareStatement(SQL);
					pst.setInt(1, getIdTipeFromName(tipoContrato,"ttiposcontrato"));
					pst.setInt(2, getIdTipeFromName(categoria, "tcategorias"));
					pst.setDouble(3, salarioBase);
					pst.setString(4, fechaContrato);
					pst.setString(5,fechaFin);
					pst.setInt(6, id_contrato);
					
					pst.executeUpdate();
					
				} catch (SQLException e) 
				{
					throw new RuntimeException(e);
				}finally {
					Jdbc.close(rs, pst, conn);
				}
		
	}

	
	/* (non-Javadoc)
	 * @see uo.ri.amp.persistence.ContractGateway#DeleteContrac(int)
	 */
	@SuppressWarnings("resource")
	@Override
	public void DeleteContrac(int contratoID) 
	{
			String SQL= "delete from tcontratos where id = ? and id not in(select distinct c.id  from taverias a, tcontratos c where a.mecanico_id = c.mecanico_id  and  a.fecha between c.fecha_contrato and c.fecha_fin or a.fecha > c.fecha_contrato and c.fecha_fin is null)";
			//String SQL= "delete from tcontratos where id = ? and mecanico_id not in(select mecanico_id from taverias a, tcontratos c, tintervenciones i where a.fecha between c.fecha_contrato and c.fecha_fin and i.averia_id = a.id)";
			String QUERY = "select count(*) from tcontratos where id = ? and id not in(select distinct c.id  from taverias a, tcontratos c where a.mecanico_id = c.mecanico_id  and  a.fecha between c.fecha_contrato and c.fecha_fin or a.fecha > c.fecha_contrato and c.fecha_fin is null)";
			PreparedStatement pst = null;
			ResultSet rs = null;
				
				try {
					pst = conn.prepareStatement(QUERY);
					pst.setInt(1, contratoID);
					rs = pst.executeQuery();
					
					pst = conn.prepareStatement(SQL);
					pst.setLong(1, contratoID);
					pst.executeUpdate();
					
					rs.next();
					if(rs.getInt(1)<1)
						Console.printf("\t%s\n", "no se ha podido eliminar este mecánico porque el mecánico ha realizado alguna actividad durante la vigencia del contrato");
					else
						Console.printf("\t%s\n", "Se ha eliminado el contrato");
				} catch (SQLException e) 
				{
					throw new RuntimeException(e);
				}
				finally {
					Jdbc.close(rs, pst, conn);
				}
		
	}
	
	
	/* (non-Javadoc)
	 * @see uo.ri.amp.persistence.ContractGateway#finalizarContrato(java.lang.Long)
	 */
	@Override
	public void finalizarContrato(Long contractId) 
	{
		String SQL = "update tcontratos set estado = 'EXTINTO' where id = ?";
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try 
		{
			pst = conn.prepareStatement(SQL);
			pst.setLong(1, contractId);
			rs = pst.executeQuery();
			
			rs.next();
			if(rs.getInt(1)<1)
				Console.printf("\t%s\n", "no se ha podido finalizar el contrato. puede que no exista el contrato indicado.");
			else
				Console.printf("\t%s\n", "Se ha finalizado el contrato");
		} catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}finally 
		{
			Jdbc.close(rs, pst, conn);
		}
		
	}

	
	/* (non-Javadoc)
	 * @see uo.ri.amp.persistence.ContractGateway#extinguirContrato(java.lang.Long)
	 */
	@SuppressWarnings("resource")
	@Override
	public void extinguirContrato(Long contractId) 
	{
		Calendar cal=Calendar.getInstance();
		int diasIndemnizacion = 0;
		String fechaInicio;
		String fechafin;
		double salarioBrutoAnual;
		Double total = 0D;
		
		String finDeMes =new SimpleDateFormat("YYYY-MM").format(cal.getTime())+"-"+cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		//System.out.println(finDeMes);
		
		String SQL = "update tcontratos set fecha_fin = '"+finDeMes+"' where id = ?";
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try 
		{
			pst = conn.prepareStatement(SQL);
			pst.setLong(1, contractId);
			pst.executeUpdate();
			
			SQL = "select fecha_contrato from tcontratos where id = ?";
			pst = conn.prepareStatement(SQL);
			pst.setLong(1, contractId);
			rs = pst.executeQuery();
			rs.next();
			fechaInicio = rs.getString(1);
			
			SQL = "select fecha_fin from tcontratos where id = ?";
			pst = conn.prepareStatement(SQL);
			pst.setLong(1, contractId);
			rs = pst.executeQuery();
			rs.next();
			fechafin = rs.getString(1);
			
			SQL = "select dias_indemnizacion from ttiposcontrato T, tcontratos C where C.id = ? and T.id = C.tipo_id ";
			pst = conn.prepareStatement(SQL);
			pst.setLong(1, contractId);
			rs = pst.executeQuery();
			rs.next();
			diasIndemnizacion = rs.getInt(1);
			
			SQL = "select sum(salario_base + paga_extra + plus  + trienio) from tnominas  where fecha between ? and now() and contrato_id = ? ";
			pst = conn.prepareStatement(SQL);
			pst.setString(1, fechaDeHaceUnAnio());
			pst.setLong(2, contractId);
			rs = pst.executeQuery();
			rs.next();
			salarioBrutoAnual = rs.getLong(1);
			
			try {
				total = (salarioBrutoAnual/365)*diasIndemnizacion*(restarFechas(StringToDate(fechaInicio), StringToDate(fechafin))/365);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			SQL = "insert into tindemnizaciones(id_contrato, total) values (?,?)";
			pst = conn.prepareStatement(SQL);
			pst.setLong(1, contractId);
			pst.setDouble(2, total);
			pst.executeUpdate();
			
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Jdbc.close(rs, pst, conn);
		}
	}
	
	/**
	 * Fecha del hace un año.
	 *
	 * @return the string
	 */
	public String fechaDeHaceUnAnio()
	{
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -1);
		return new SimpleDateFormat("YYYY-MM-dd").format(cal.getTime());
	}
	
	/**
	 * String to date.
	 *
	 * @param fechaS the fecha s
	 * @return the date
	 * @throws ParseException the parse exception
	 */
	public static Date StringToDate(String fechaS) throws ParseException
	{
		SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd");
		
		try {
			 
			Date date = formatter.parse(fechaS);
			return date;
	 
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
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
	 * @see uo.ri.amp.persistence.ContractGateway#ShowContract(int)
	 */
	@Override
	public void ShowContract(int contractID) 
	{
		String SQL="select mecanico_id, tipo_id, estado, fecha_contrato, fecha_fin, categoria_id, salario_base from tcontratos where id = '"+contractID+"'";
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = conn.prepareStatement(SQL);
			rs = pst.executeQuery();
			while(rs.next()) 
			{
				Console.printf("\t%s\n \t%s\n \t%s\n \t%s\n\t%s\n \t%s\n",  
						"contrato de mecánico - "+rs.getString(1)
					,	"TIPO DE CONTRATO: "+ getNameToId(rs.getInt(2),"ttiposcontrato")
					,  "estado - "+rs.getString(3) 
					,  "fecha de inicio del contrato - "+rs.getString(4)
					,  "fecha de finalización del contrato - "+rs.getString(5)
					,  "categoría del contrato - "+ getNameToId(rs.getInt(6),"tcategorias")
					,  "salario base bruto anual - "+rs.getString(7)
				);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Jdbc.close(rs, pst, conn);
		}
		
	}

	
	/* (non-Javadoc)
	 * @see uo.ri.amp.persistence.ContractGateway#lisTcategorias()
	 */
	@Override
	public void lisTcategorias() 
	{
		String SQL="select nombre from tcategorias";
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = conn.prepareStatement(SQL);
			rs = pst.executeQuery();
			while(rs.next()) 
			{
				Console.printf("\t%s\n",  
					rs.getString(1)
				);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Jdbc.close(rs, pst, conn);
		}
	}
	
	/* (non-Javadoc)
	 * @see uo.ri.amp.persistence.ContractGateway#lisTiposContrato()
	 */
	@Override
	public void lisTiposContrato() 
	{
		String SQL="select id, nombre from ttiposcontrato";
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			pst = conn.prepareStatement(SQL);
			rs = pst.executeQuery();
			while(rs.next()) 
			{
				Console.printf("\t%s\n",  
					rs.getString(1)+" - "+rs.getString(2)
				);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			Jdbc.close(rs, pst, conn);
		}
	}

	/* (non-Javadoc)
	 * @see uo.ri.amp.persistence.ContractGateway#addContractType(java.lang.String, int)
	 */
	@Override
	public void addContractType(String nombre, int diasIndemnizacion) 
	{
		String SQL = "insert into ttiposcontrato (nombre, dias_indemnizacion) values(?,?)";
		
		ResultSet rs = null;
		PreparedStatement pst = null;
		
		try {
			//se inserta el nuevo contrato con estado ACTIVO
			pst = conn.prepareStatement(SQL);
			pst.setString(1, nombre);
			pst.setInt(2, diasIndemnizacion);
			
			pst.executeUpdate();
			
		} catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}finally {
			Jdbc.close(rs, pst, conn);
		}
		
	}

	/* (non-Javadoc)
	 * @see uo.ri.amp.persistence.ContractGateway#ListMechanicsByContractType(java.lang.Long)
	 */
	@SuppressWarnings("resource")
	@Override
	public void ListMechanicsByContractType(Long contractTypeId) 
	{
		String tipoContrato;
		String SQL = "select nombre from ttiposcontrato where id = ?";
		ResultSet rs = null;
		PreparedStatement pst = null;
		
		try {
			//se inserta el nuevo contrato con estado ACTIVO
			pst = conn.prepareStatement(SQL);
			pst.setLong(1, contractTypeId);
			rs = pst.executeQuery();
			rs.next();
			tipoContrato = rs.getString(1);
			
			
			SQL = "select * from tmecanicos where id in(select mecanico_id from tcontratos C, ttiposcontrato T where C.estado = 'ACTIVO' and C.tipo_id = T.id and T.id = ?)";
			pst = conn.prepareStatement(SQL);
			pst.setLong(1, contractTypeId);
			rs = pst.executeQuery();
			
			Console.printf("\t\n%s\n\n", "Mecánicos con contrato "+tipoContrato+" en estado ACTIVO:");
			while(rs.next()) 
			{
				Console.printf("\t%s \t%s \t%s\n",  
						"ID mecánico - "+rs.getInt(1)
					,	"Nombre -"+ rs.getString(3)
					,  	"Apellido - "+ rs.getString(2)
				
				);
			}
			
			SQL = "select sum (salario_base + paga_extra + plus  + trienio) from tnominas where contrato_id in (select contrato_id from tnominas N, tcontratos C where N.contrato_id = C.id and C.estado = 'ACTIVO' and C.tipo_id = ?)";
			pst = conn.prepareStatement(SQL);
			pst.setLong(1, contractTypeId);
			rs = pst.executeQuery();
			rs.next();
			Console.printf("\n\t%s\n", "Acumulado del salario bruto de los trabajadores: "+rs.getDouble(1));
			
			SQL = "select count (*) from tmecanicos where id in (select mecanico_id from tcontratos C, ttiposcontrato T where C.estado = 'ACTIVO' and C.tipo_id = T.id and T.id = ? )";
			pst = conn.prepareStatement(SQL);
			pst.setLong(1, contractTypeId);
			rs = pst.executeQuery();
			rs.next();
			Console.printf("\t%s\n", "Número de mecánicos: "+rs.getInt(1));
			
		} catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}finally {
			Jdbc.close(rs, pst, conn);
		}
		
	}

	/* (non-Javadoc)
	 * @see uo.ri.amp.persistence.ContractGateway#deleteContractType(java.lang.Long)
	 */
	@Override
	public void deleteContractType(Long contractTypeId) 
	{
		String SQL = "delete from ttiposcontrato where id = ? and id not in (select tipo_id from tcontratos)";
		ResultSet rs = null;
		PreparedStatement pst = null;
		
		try 
		{
			pst = conn.prepareStatement(SQL);
			pst.setLong(1, contractTypeId);
			//pst.executeUpdate();
			pst.executeUpdate();
			
		} catch (SQLException e) 
		{

			throw new RuntimeException(e);
		}finally {
			Jdbc.close(rs, pst, conn);
		}
		
	}

	/* (non-Javadoc)
	 * @see uo.ri.amp.persistence.ContractGateway#updateContractType(java.lang.Long, java.lang.String, int)
	 */
	@Override
	public void updateContractType(Long id, String nombre, int diasIndemnizacion) 
	{
		String SQL = "update ttiposcontrato set nombre = ? , dias_indemnizacion = ? where id = ?";
		
		ResultSet rs = null;
		PreparedStatement pst = null;
		
		try {
			//se inserta el nuevo contrato con estado ACTIVO
			pst = conn.prepareStatement(SQL);
			pst.setString(1, nombre);
			pst.setInt(2, diasIndemnizacion);
			pst.setLong(3, id);
			
			pst.executeUpdate();
			
		} catch (SQLException e) 
		{
			throw new RuntimeException(e);
		}finally {
			Jdbc.close(rs, pst, conn);
		}
		
	}

}
