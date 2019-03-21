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
		
		
		String mdp = DaoHelper.hash("15343");
		System.out.println(mdp);
		Participant perso = new Participant(0, "Golem de sel", "Funky", "Xeno", "02354", "0102030405", true, true, new Site(11, "le mans"), "");
		
		
		
		
//		DaoAdmin.addParticipant(perso, mdp);
		
		// envoyer en bdd
	}
}	
		
		
		
		
		
		

