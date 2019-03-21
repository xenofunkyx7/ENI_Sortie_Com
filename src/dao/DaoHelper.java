package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoHelper {
	
	private static final String VERIF_UTILISATEUR = " SELECT mot_de_passe FROM participants WHERE no_participant = ? ";

	
	public static String hash(String mdp) {
		
		String passwordHashExa = "";		
		MessageDigest digest;		
		byte[] hashPsw;
		
		try {
			
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
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean verifMdp(String inputPsw, int idUtilisateur ) {
		
		String sql = VERIF_UTILISATEUR;
		ResultSet rs = null;
		String pswSQL = null;
		
		boolean isVerif = false;
		
		DbConnexion dbConnexion = new DbConnexion();
		try( Connection connection = dbConnexion.getConnection() ; PreparedStatement pStat = connection.prepareStatement(sql)){
			
			pStat.setInt(1, idUtilisateur);
			
			rs = pStat.executeQuery();
			
			if(rs != null){
				pswSQL = rs.getString("mot_de_passe");
				
				if(pswSQL == inputPsw) {					
					 isVerif = true;
				}
			}			
			return isVerif;	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
			return isVerif;
		}
	}
}
