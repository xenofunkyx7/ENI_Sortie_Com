package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/**
 * The Class DaoInscription.
 */
public class DaoInscription {
	
	/** The Constant DELETE_INSCRIPTION. */
	private static final String 
		ADD_INSCRIPTION = 
			"INSERT INTO INSCRIPTIONS " + 
			" VALUES ( ?,?,? ) " ,
			
		DELETE_INSCRIPTION = 
			"delete from INSCRIPTIONS " + 
			" where sorties_no_sortie = ? and participants_no_participant = ? ";
	
	/**
	 * Adds the inscription.
	 *
	 * @param date the date
	 * @param idSortie the id sortie
	 * @param idParticipant the id participant
	 * @throws SQLException the SQL exception
	 */
	public static void addInscription (Date date, int idSortie, int idParticipant) throws SQLException {
		
		deleteInscription(idSortie, idParticipant);
		
		String sql = ADD_INSCRIPTION;
				
		DbConnexion dbConnexion = new DbConnexion();
		
		Connection connection = dbConnexion.getConnection() ;
		PreparedStatement pStat = connection.prepareStatement(sql);
			
			pStat.setDate(1,date );
			pStat.setInt(2, idSortie );
			pStat.setInt(3, idParticipant );
			
			pStat.executeUpdate() ;		
	}
	
	/**
	 * Delete inscription.
	 *
	 * @param idSortie the id sortie
	 * @param idParticipant the id participant
	 * @throws SQLException the SQL exception
	 */
	public static void deleteInscription(int idSortie, int idParticipant) throws SQLException {
		String sql = DELETE_INSCRIPTION;
		DbConnexion dbConnexion = new DbConnexion();
		
		Connection connection = dbConnexion.getConnection() ;
		PreparedStatement pStat = connection.prepareStatement(sql);
			
			pStat.setInt(1, idSortie );
			pStat.setInt(2, idParticipant );
			
			pStat.executeUpdate() ;	
	}
	
}
