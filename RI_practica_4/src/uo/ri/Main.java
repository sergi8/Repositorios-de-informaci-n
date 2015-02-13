package uo.ri;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;



import com.mchange.v2.c3p0.DataSources;

public class Main {

	private static final int ITERACIONES = 100;

/*
 *	 Configuration for Oracle
*/
	 private static String DRIVER = "oracle.jdbc.driver.OracleDriver"; 
	 private static String URL = "jdbc:oracle:thin:@156.35.94.99:1521:DESA"; 
	 private static String USER = "UO227806"; 
	 private static String PASS = "prueba";
	 private static Connection con;
	
/*
 * Configuration for Hsqldb
 * */
	private static String DRIVERH = "org.hsqldb.jdbcDriver";
	private static String URLH = "jdbc:hsqldb:hsql://localhost";
	private static String USERH = "sa";
	private static String PASSH = "";

	
/*
 * Configuration for MySQL 
	private static String DRIVER = "com.mysql.jdbc.Driver"; 
	private static String URL = "jdbc:mysql://156.35.94.99:3306/test"; 
	private static String USER = "prueba"; 
	private static String PASS = "prueba";
*/
 
	private DataSource pooled;
	private DataSource unpooled;
	public static void main(String[] args) 
	{
		try {
			new Main().run();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Main() {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Driver not found in classpath", e);
		}
	}
	
	private void initializePool(int bd) throws SQLException
	{
		if(bd==0)
			unpooled = DataSources.unpooledDataSource(URL,USER,PASS);
		if(bd==1)
			unpooled = DataSources.unpooledDataSource(URLH,USERH,PASSH);
		Map overrides = new HashMap();
		//overrides.put("maxStatements", "200");
		overrides.put("initialPoolSize", "3");
		overrides.put("maxPoolSize", new Integer(30));
		overrides.put("minPoolSize","3");
		pooled = DataSources.pooledDataSource( unpooled,overrides );
	}
	private void createconn() throws SQLException
	{
			
			con = pooled.getConnection();
			System.out.println("-------->conexión establecida<--------");
		
	}

	private void run() throws SQLException 
	{
		ejercicio1();
		ejercicio2();
		ejercicio3();
	}
	
	public void ejercicio1() throws SQLException
	{
		String consulta = "select count(id)from tvehiculos";
		
		float t1 = System.currentTimeMillis();
		initializePool(0);
		for(int i=0; i< ITERACIONES;i++)
		{
			createconn();
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(consulta);
			rs.next();
			System.out.println(rs.getString(1)+"\t");
			con.close();
		}
		System.out.println("tiempo empleado con pool de conexiones(oracle) = "+(System.currentTimeMillis()-t1));
		
		consulta = "select count (*) from viajes";
		t1 = System.currentTimeMillis();
		initializePool(1);
		for(int i=0; i< ITERACIONES;i++)
		{
			createconn();
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(consulta);
			rs.next();
			System.out.println(rs.getString(1)+"\t");
			con.close();
		}
		System.out.println("tiempo empleado con pool de conexiones(Hsqldb) = "+(System.currentTimeMillis()-t1));
		
	
	}
	
	public void ejercicio2() throws SQLException
	{
		String consulta = "select * from student where dept_name = 'Physics'";
		initializePool(0);
		createconn();
		//si queremos imprimir del rebés tenemos que definir el tipo de resulset
		Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		ResultSet rs = statement.executeQuery(consulta);
		
		while(rs.next())
			{
				int cont=1;
				System.out.print("\n->");
				try
				{
					while(true)
					{
						System.out.print(rs.getString(cont)+"\t");
						cont++;
					}
				}catch(Exception e)
				{}
			}
		System.out.println("\n \n  imprimiendo del rebés");
		while(rs.previous())
		{
			int cont=1;
			System.out.print("\n->");
			try
			{
				while(true)
				{
					System.out.print(rs.getString(cont)+"\t");
					cont++;
				}
			}catch(Exception e)
			{}
		}
		
	}
	
	public void ejercicio3()
	{
		
	}

}
