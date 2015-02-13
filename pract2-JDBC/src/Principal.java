
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class Principal 
{
	
	static String[][] cadenasConexion;
	static Connection con;
	
	public static void main(String[] arg)
	{
		cadenasConexion = new String[2][3];
		cadenasConexion[0][0]="jdbc:oracle:thin:@156.35.94.99:5850:DESA";
		cadenasConexion[0][1]="UO227806";
		cadenasConexion[0][2]="prueba";
		cadenasConexion[1][0]="jdbc:hsqldb:hsql://localhost";
		cadenasConexion[1][1]="sa";
		cadenasConexion[1][2]="";
		
		System.out.println("Escoja una base de datos:");
		System.out.println("Oracle--->[0]");
		System.out.println("HSQLDB--->[1]");
		int entradaTeclado;
        Scanner scaner = new Scanner (System.in);
        entradaTeclado = Integer.parseInt(scaner.nextLine ());
        createCon(entradaTeclado);
        
        ejercicio1Stm();
//        ejercicio1Pstm();
//        ejercicio2();
//        ejercicio3();
        System.out.println("fin");
	}
	
	private static void createCon(int cad)
	{
		
			try {
				con = DriverManager.getConnection(cadenasConexion[cad][0], cadenasConexion[cad][1], cadenasConexion[cad][2]);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("----conexión establecida----\n");
		
		
	}
	
	public static void ejercicio1Stm()
	{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader (isr);
		
		System.out.println("Introduzca el número de averías(statement):");
		try {
			String averias = br.readLine();
		
			String query="select count(*)from tvehiculos where num_averias ='"+ averias +"'";
			
			 	Statement stm = con.createStatement();
				ResultSet rs =  stm.executeQuery(query);
				
//				while(rs.next())
//				{
//					int cont=1;
					System.out.print("\n->");
//					try
//					{
//						while(true)
//						{
							rs.next();
							System.out.print(rs.getString(1)+"\t");
//							cont++;
//						}
//					}catch(Exception e)
//					{
//						
//					}

//				} 
			} catch (SQLException e) {
				e.printStackTrace();
			}catch(IOException e)
			{
				System.err.println("Error al leer de teclado");
				e.printStackTrace();
			}
	}
	
	public static void ejercicio1Pstm()
	{
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader (isr);
		System.out.println("Introduzca el número de averías(preparedStatement):");
		
		try {
			int averias = Integer.parseInt(br.readLine());
			
			PreparedStatement ps = con.prepareStatement("select count(*)from tvehiculos where num_averias = ?");
			ps.setInt(1, averias);
			ResultSet rs = ps.executeQuery();
			
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
				{
					
				}

			} 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	public static void ejercicio2()
	{
		//contar el número de vehículos cuyo identificador coincide con el número de iteración.
		try {
			double t1;
			t1= System.currentTimeMillis();
			Statement stm;
			for(int i=0; i<100;i++)
			{
				
					String query="select count(id) from tvehiculos where id="+ i;
					stm = con.createStatement();
					
					ResultSet rs =  stm.executeQuery(query);
			}
			System.out.println("usando statement tarda"+(System.currentTimeMillis()-t1) +"ms.");
			
			t1= System.currentTimeMillis();
			for(int i=0;i<100;i++)
			{
				PreparedStatement ps = con.prepareStatement("select count(id) from tvehiculos where id= ?");
				ps.setInt(1, 4);
				ResultSet rs = ps.executeQuery();
			}
			System.out.println("usando preparedStatement tarda"+(System.currentTimeMillis()-t1) +"ms.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void ejercicio3()
	{
		try {
			
		double t1 = System.currentTimeMillis();
		for(int i=0;i<100;i++)
		{
			
				String consulta = "select count(id)from tvehiculos";
				Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@156.35.94.99:5850:DESA", "UO227806", "prueba");
				Statement statement = conn.createStatement();
				ResultSet rs = statement.executeQuery(consulta);
		}
		System.out.println("tiempo empleado creando la conexión= "+(System.currentTimeMillis()-t1));
		
		
		t1=System.currentTimeMillis();
		Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@156.35.94.99:5850:DESA", "UO227806", "prueba");
		for(int i=0;i<100;i++)
		{
			
				String consulta = "select count(id)from tvehiculos";
				Statement statement = conn.createStatement();
				ResultSet rs = statement.executeQuery(consulta);
		}
		System.out.println("tiempo empleado sin crear conexión= "+(System.currentTimeMillis()-t1));
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
