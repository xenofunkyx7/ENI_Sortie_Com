package dao;

import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bean.Participant;
import bean.Site;

/**
 * @author DWWM
 *
 */
public class TestJava {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		
		
		String mdp = DaoHelper.hash("bob");
		System.out.println(mdp);
		
		
//		DaoAdmin.addParticipant(perso, mdp);
		
		// envoyer en bdd
	}
}	
		
		
		
		
		
		

