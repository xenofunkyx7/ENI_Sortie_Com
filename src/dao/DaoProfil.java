package dao;

import java.sql.SQLException;
import java.util.List;

import bean.Participant;
import bean.Sortie;

public class DaoProfil {
	// Singkleton !
	
	 private DaoProfil() {} 
	 
	 private static DaoProfil INSTANCE = new DaoProfil();
	 
	 public static DaoProfil getInstance() {   
		 return INSTANCE;
     }
		 
	 /**
	 * M�thode permettant de rajouter un Participant via un objet Participant en param�tre.
	 * @param participant
	 */
	 public static void addParticipant (Participant participant) {
		
		
	 }
	 
	 /**
	 * M�thode permettant de modifier un participant via un objet participant en param�tre.
	 * @param participant
	 */
	 public static void modifyParticipant(Participant participant) {
		 
		 
	 }

	 /**
	 * M�thode permettant de supprimer un participant via un objet participant en param�tre.
	 * @param participant
	 */
	 public static void deleteParticipant(Participant participant) {
		 
		 
	 }
	 
	/**
	* M�thode permettant de faire des recherches en fonction du pseudo
	* @param pseudo
	* @return Participant
	* @throws SQLException
	*/
	
	public static Participant getParticipant (String pseudo) throws SQLException {
	
		// utiliser %like% et non "="
		
		return null;
	}
	
	/**
	* M�thode permettant de faire des recherches en fonction d'une sortie
	* @return liste de Participant
	* @throws SQLException
	*/
	
	public static List<Participant> getParticipants (Sortie sortie) throws SQLException {
		return getParticipants(sortie.getId() );
	}
	 
	/**
	* M�thode permettant de faire des recherches en fonction d'un id de sortie
	* @return liste de Participant
	* @throws SQLException
	*/
	
	public static List<Participant> getParticipants (int idSortie) throws SQLException {
	
		return null;
	}
	
}
