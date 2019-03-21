package dao;

import java.security.NoSuchAlgorithmException;
import java.security.Provider;

public class TestJava {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		
		String mp1 = DaoHelper.hash("antoine");
		String mp2 = DaoHelper.hash("eniotna");    
		
		System.out.println(mp1);
		System.out.println(mp2);
	}
}	
		
		
		
		
		
		

