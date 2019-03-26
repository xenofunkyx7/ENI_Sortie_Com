package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bean.Lieu;

public class DaoInscription {
	
	private static final String 
		ADD_INSCRIPTION = 
			"INSERT INTO INSCRIPTIONS " + 
			" VALUES ( ?,?,? ) " ,
			
		DELETE_INSCRIPTION = 
			"delete from INSCRIPTIONS " + 
			" where sorties_no_sortie = ? and participants_no_participant = ? ";
	
	public static void addInscription (Date date, int idSortie, int idParticipant) {
		
		deleteInscription(idSortie, idParticipant);
		
		String sql = ADD_INSCRIPTION;
				
		DbConnexion dbConnexion = new DbConnexion();
		try ( Connection connection = dbConnexion.getConnection() ; PreparedStatement pStat = connection.prepareStatement(sql) ){
			
			pStat.setDate(1,date );
			pStat.setInt(2, idSortie );
			pStat.setInt(3, idParticipant );
			
			pStat.executeUpdate() ;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void deleteInscription(int idSortie, int idParticipant) {
		String sql = DELETE_INSCRIPTION;
		DbConnexion dbConnexion = new DbConnexion();
		try ( Connection connection = dbConnexion.getConnection() ; PreparedStatement pStat = connection.prepareStatement(sql) ){
			
			pStat.setInt(1, idSortie );
			pStat.setInt(2, idParticipant );
			
			pStat.executeUpdate() ;
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
