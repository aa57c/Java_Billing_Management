/**
 * 
 */
package db_connection;


import java.sql.*;


/**
 * @author ashna
 *
 */
public class DBConnection {

	public static void main(String[] args) {
		Connection conn = null;
		try {
			String dbURL = 
					"jdbc:sqlserver://localhost:1433;databaseName=CS349Proj;integratedSecurity=true;trustServerCertificate=true";
			conn = DriverManager.getConnection(dbURL);
			if(conn != null) {
				DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
				System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());
                
            }
 
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
				
			}
			
					
	}

}


