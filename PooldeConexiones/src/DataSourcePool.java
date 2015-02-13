import javax.sql.DataSource;


public class DataSourcePool {

	public static void main(String[] args) 
	{
		DataSource unpooled =
				DataSources.unpooledDataSource("jdbc:oracle:thin:@156.35.94.99:22:DESA",“XXXXXX",“YYYYY");
//				DataSource unpooled =
//				DataSources.unpooledDataSource("jdbc:mysql://156.35.94.99:3306/test",
//				“XXXXXXX",“YYYYYYY");
				Map overrides = new HashMap();
				overrides.put("maxStatements", "200");
				overrides.put("maxPoolSize", new Integer(50));
				overrides.put("minPoolSize","6");
				DataSource pooled = DataSources.pooledDataSource( unpooled,overrides );
				con = pooled.getConnection();

	}

}
