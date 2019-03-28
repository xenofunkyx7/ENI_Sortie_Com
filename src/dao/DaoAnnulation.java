package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * The Class DaoAnnulation.
 */
public class DaoAnnulation {
	
	/** The Constant ADD_ANNULATION. */
	private static final String 
	ADD_ANNULATION = 
			"INSERT INTO Annulations " + 
			" VALUES ( ?,?,? ) " ;
			
	/**
	 * Adds the annulation.
	 *
	 *Annul un evenement dans la BDD
	 *
	 * @param id the id
	 * @param motif the motif
	 * @param date the date
	 * @throws SQLException the SQL exception
	 */
	public static void addAnnulation (int id, String motif, Date date) throws SQLException {
		
		String sql = ADD_ANNULATION;
				
		DbConnexion dbConnexion = new DbConnexion();
		Connection connection = dbConnexion.getConnection() ; PreparedStatement pStat = connection.prepareStatement(sql);
			
			pStat.setInt(1, id );
			pStat.setString(2, motif );
			pStat.setDate(3, date );
			
			pStat.executeUpdate() ;
		
	}
}
