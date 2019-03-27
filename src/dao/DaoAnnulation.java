package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DaoAnnulation {
	
	private static final String 
	ADD_ANNULATION = 
			"INSERT INTO Annulations " + 
			" VALUES ( ?,?,? ) " ;
			
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
