package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;


/**
 * The Class DbConnexion.
 */
public class DbConnexion {

	/** The Constant URL. */
	private static final String URL ="jdbc:sqlserver://10.118.89.149:1433;instanceName=SQLEXPRESS;databaseName=SORTIES";
	
	/** The Constant USER. */
	private static final String USER ="sa";
	
	/** The Constant PWD. */
	private static final String PWD ="route";

	/**
	 * M�thode permet d'obtenir une nouvelle connexion.
	 *
	 * @return un objet de type connexion
	 * @throws SQLException the SQL exception
	 */
	
	public Connection getConnection()throws SQLException
	{
		Connection cnx = null;
		
		DriverManager.registerDriver(new SQLServerDriver());
		cnx = DriverManager.getConnection(URL, USER, PWD);

		return cnx;
	}
}
