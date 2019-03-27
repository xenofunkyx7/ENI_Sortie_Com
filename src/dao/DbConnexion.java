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
	 * M�thode permet d'obtenir une nouvelle connexion
	 * @return un objet de type connexion
	 * @throws SQLException
	 */
	
	public Connection getConnection()throws SQLException
	{
		Connection cnx = null;
		
		DriverManager.registerDriver(new SQLServerDriver());
		cnx = DriverManager.getConnection(URL, USER, PWD);

		return cnx;
	}
}
