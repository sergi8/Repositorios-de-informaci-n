import java.beans.FeatureDescriptor;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.text.SimpleDateFormat;

public class Calendario 
{
	public static void main(String[] arg)
	{
		//imprimeFechas();
		System.out.println(obtenerUltimoDiaMes(2014, 12));
		System.out.println("fecha actual: "+ fehaActual());
	}
	public static void imprimeFechas()
	{
		Calendar cal=Calendar.getInstance();
		
		System.out.println("GregorianCalendar:"+ cal.get(Calendar.DAY_OF_WEEK)+"-"+cal.get(Calendar.DAY_OF_MONTH)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.YEAR));
		System.out.println("Calendar (fechaActual)"+cal.get(Calendar.DAY_OF_WEEK)+"-"+cal.get(Calendar.DAY_OF_MONTH)+"/"+(cal.get(Calendar.MONTH)+1)+"/"+cal.get(Calendar.YEAR));
		
		System.out.println(cal.getTime());
		System.out.println(new SimpleDateFormat("YYYY-MM-dd").format(cal.getTime()));
		
		System.out.println( "último día del mes actual "+cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		fechaDelHaceUnAño();
		
		try {
			StringToDate("20-nov-2010");
			
			resta();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static int obtenerUltimoDiaMes (int anio, int mes) 
	{

		Calendar cal=Calendar.getInstance();
		//el -1 es porque java entiende enero como el més 0
		cal.set(anio, mes-1, 1);
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH);

	}
	
	public Date sumarRestarDiasFecha(Date fecha, int dias)
	{
		   Calendar calendar = Calendar.getInstance();	
		   calendar.setTime(fecha); // Configuramos la fecha que se recibe
		   calendar.add(Calendar.DAY_OF_YEAR, dias);  // numero de días a añadir, o restar en caso de días<0
		   return calendar.getTime(); // Devuelve el objeto Date con los nuevos días añadidos
	
	}
	public static void fechaDelHaceUnAño()
	{
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -1);
		System.out.println("\n");
		System.out.println(new SimpleDateFormat("dd-MMM-YYYY").format(cal.getTime()));
	}
	
	public static Date StringToDate(String fechaS) throws ParseException
	{
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		
		try {
			 
			Date date = formatter.parse(fechaS);
			System.out.println(date);
			System.out.println(formatter.format(date));
			return date;
	 
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
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
	
	public static void resta() throws ParseException
	{
		String fecha1 = "2-ene-2014";
		String fecha2 = "5-dic-2014";
		
		int diferencia = restarFechas(StringToDate(fecha1), StringToDate(fecha2));
		
		System.out.println("Diferencia de las deflas = "+ diferencia);
	}
	
	public static String fehaActual()
	{
		Calendar cal = Calendar.getInstance();
		return new SimpleDateFormat("YYYY-MM-dd").format(cal.getTime());
	}

}
