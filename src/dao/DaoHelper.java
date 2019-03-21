package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DaoHelper {

	
	public static String hash(String mdp) throws NoSuchAlgorithmException {
		
		String passwordHashExa = "";
		
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] hashPsw = digest.digest(mdp.getBytes());
        
        
		// transformation en hexadecimal
        StringBuilder sb = new StringBuilder();
        
        for(int i=0; i< hashPsw.length ;i++)
        {
            sb.append(Integer.toString((hashPsw[i] & 0xff) + 0x100, 16).substring(1));
        }
        
        //  string builder mis dans un string. 
        passwordHashExa = sb.toString();	
		
		return passwordHashExa;		
	}
	
}
