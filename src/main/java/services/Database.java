package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class Database {
    private static String driverClassName = "";
    private static String url = "";
    private static String username = "";
    private static String userpass = "";
    
    private static Connection connection = null;

	public static void main(String... args) throws Exception {
		Connection conn = getConnection();
		
		PreparedStatement ps = conn.prepareStatement("SELECT * FROM Messages");
		ResultSet rs = ps.executeQuery();
		System.out.println(rs);
		
	}
	
	/**
	 * Ferme et valorise à null l'objet de connexion
	 * 
	 * @throws SQLException
	 */
	public static void close() throws SQLException {
		if (Objects.nonNull(connection)) {
			connection.close();
			connection = null;
		}
	}

	/**
	 * Retourne la connexion à la base de données ou provoque une erreur le cas échéant
	 * 
	 * @return
	 * @throws Exception
	 */
    public static Connection getConnection() throws Exception {

    	if (Objects.isNull(connection)) {

            try {
                Class.forName(driverClassName);

            } catch (ClassNotFoundException e) {
                throw new Exception(e);
            }

            try {
                connection = DriverManager.getConnection(
                        url,
                        username,
                        userpass);
            } catch (SQLException e) {
                throw new Exception(e);
            }
    	}

        return connection;
    }
}
