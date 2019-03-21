package dao;

import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author DWWM
 *
 */
public class TestJava {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		
		String mp1 = "012345678910123456789";
		String mp2;
		
		Pattern p = Pattern.compile("\\d{8,15}+");
		Matcher m = p.matcher(mp1);

		boolean bool = m.matches();
		
		System.out.println(bool);
	}
}	
		
		
		
		
		
		

