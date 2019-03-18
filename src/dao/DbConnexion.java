package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

public class DbConnexion {

	private static final String URL ="jdbc:sqlserver://10.118.89.149:1433;instanceName=SQLEXPRESS;databaseName=SORTIES";
	private static final String USER ="sa";
	private static final String PWD ="route";

	/**
	 * Méthode permet d'obtenir une nouvelle connexion
	 * @return un objet de type connexion
	 * @throws SQLException
	 */
	
	public Connection seConnecter()throws SQLException
	{
		Connection cnx = null;
		
		try{
			DriverManager.registerDriver(new SQLServerDriver());
		}
		catch(SQLException e){
			throw new SQLException("Impossible de charger le driver JDBC : "+e.getMessage());
		}
		
		try{
		cnx = DriverManager.getConnection(URL, USER, PWD);
		}
		catch(SQLException e){
			throw new SQLException("Impossible d'obtenir une connexion : "+e.getMessage());
		}
		return cnx;
	}
}
