package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * The Class DaoHelper.
 */
public class DaoHelper {
	
	/** The Constant VERIF_UTILISATEUR_ID. */
	private static final String VERIF_UTILISATEUR_ID = " SELECT mot_de_passe FROM participants WHERE no_participant = ? ";
	
	/** The Constant VERIF_UTILISATEUR_PSEUDO. */
	private static final String VERIF_UTILISATEUR_PSEUDO = " SELECT mot_de_passe FROM participants WHERE pseudo = ? ";
	
	/**
	 * Hash.
	 * 
	 * Fonction qui HASH le MDP
	 *
	 * @param mdp the mdp Le mot de passe en clair. 
	 * @return the string Le mot de passe cripté
	 * @throws NoSuchAlgorithmException the no such algorithm exception
	 */
	public static String hash(String mdp) throws NoSuchAlgorithmException {
		
		String passwordHashExa = "";		
		MessageDigest digest;		
		byte[] hashPsw;
					
			digest = MessageDigest.getInstance("SHA-256");
			hashPsw = digest.digest(mdp.getBytes());
		
			// transformation en hexadecimal
	        StringBuilder sb = new StringBuilder();
	        for(int i=0; i< hashPsw.length ;i++)
	        {
	            sb.append(Integer.toString((hashPsw[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        
	        // string builder mis dans un string. 
	        passwordHashExa = sb.toString();	
			
			return passwordHashExa;		
	}
	
	/**
	 * Verif mdp.
	 * 
	 * Verifie  que le MDDP passé  en parametre esst bien celui de l'id Utilisateur
	 *
	 * @param inputPsw the input psw
	 * @param idUtilisateur the id utilisateur
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	public static boolean verifMdp(String inputPsw, int idUtilisateur ) throws SQLException {
		
		String sql = VERIF_UTILISATEUR_ID;
		ResultSet rs = null;
		String pswSQL = "";
		
		boolean isVerif = false;
		
		DbConnexion dbConnexion = new DbConnexion();
		Connection connection = dbConnexion.getConnection() ; 
		PreparedStatement pStat = connection.prepareStatement(sql);
			
			pStat.setInt(1, idUtilisateur);
			
			rs = pStat.executeQuery();
			
			
			if(rs != null && rs.next()){				
				
				pswSQL = rs.getString("mot_de_passe");	
				isVerif = pswSQL.equals(inputPsw);
					 
				
			}
			return isVerif;	

	}
	
	/**
	 * Verif mdp.
	 * 
	 * Verifie  que le MDDP passé  en parametre esst bien celui du pseudo Utilisateur
	 *
	 * @param inputPsw the input psw
	 * @param PseudoUtilisateur the pseudo utilisateur
	 * @return true, if successful
	 * @throws SQLException the SQL exception
	 */
	// la c'est le pseudo !
	public static boolean verifMdp(String inputPsw, String PseudoUtilisateur) throws SQLException {
		
		String sql = VERIF_UTILISATEUR_PSEUDO;
		ResultSet rs = null;
		String pswSQL = null;
		
		boolean isVerif = false;
		
		DbConnexion dbConnexion = new DbConnexion();
		Connection connection = dbConnexion.getConnection() ;
		PreparedStatement pStat = connection.prepareStatement(sql);
			
			pStat.setString(1, PseudoUtilisateur);
			
			rs = pStat.executeQuery();
			
			if(rs != null && rs.next()){
				pswSQL = rs.getString("mot_de_passe");

				if(pswSQL.equals(inputPsw) ) {					
					 isVerif = true;
				}
			}			
			return isVerif;	

	}
}
