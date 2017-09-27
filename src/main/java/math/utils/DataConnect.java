package math.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataConnect {
	/**
	 * Méthode pour récupérer la connexion à la BDD humanbooster
	 * @return Connection
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws SQLException, ClassNotFoundException{
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection(
										"jdbc:mysql://localhost:3306/mathlove",
										"root", "");
		return connection;
		
	}
	
	/**
	 * Méthode pour fermer la connection
	 * @param c connection à fermer
	 */
	public static void closeConnection(Connection c){
		try{
			c.close();
		}catch(SQLException sqle){
			sqle.printStackTrace();
		}
	}

}
