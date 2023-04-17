/**
 * 
 */
package db_connection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

/**
 * @author ashna
 *
 */
public class DB_Connect {
	SQLServerDataSource ds;
	Connection conn;

	/**
	 * 
	 */
	public DB_Connect() {
		ds = new SQLServerDataSource();
		ds.setServerName("localhost");
		ds.setPortNumber(1433);
		ds.setDatabaseName("CS349Proj");
		ds.setTrustServerCertificate(true);
		ds.setIntegratedSecurity(true);
		try {
			conn = ds.getConnection();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public CallableStatement query(String q) throws SQLException {
		return conn.prepareCall(q);
	}
	public Statement createQuery() throws SQLException {
		return conn.createStatement();
	}

}
