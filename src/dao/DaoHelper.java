package dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DaoHelper {

	public static byte[] hash(String mdp) throws NoSuchAlgorithmException {
		
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		return digest.digest(mdp.getBytes());
	}
	
}
