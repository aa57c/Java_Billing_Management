package connectDataSource;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

public class ConnectDataSource {
	public static void main(String args[]) {
		SQLServerDataSource ds = new SQLServerDataSource();
		ds.setServerName("localhost");
		ds.setPortNumber(1433);
		ds.setDatabaseName("CS349Proj");
		ds.setTrustServerCertificate(true);
		ds.setIntegratedSecurity(true);
		try(Connection con = ds.getConnection()){
			Statement stmt = con.createStatement();
			String sqlStr = "Select firstName, lastName from dbo.Customers";
			ResultSet rs = stmt.executeQuery(sqlStr);
			while(rs.next()) {
				System.out.print(rs.getString("firstName") + " " + rs.getString("lastName") + "\n");
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
}
