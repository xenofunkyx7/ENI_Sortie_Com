package dao;

import java.sql.SQLException;
import java.util.List;

import bean.Participant;
import bean.Sortie;

public class DaoProfil {
	
	 private static final String ADD_PARTICIPANT = "";
	 private static final String MODIFY_PARTICIPANT = "";
	 private static final String MODIFY_PARTICIPANTMDP = "";
	 private static final String DELETE_PARTICIPANT = "";
	 private static final String GET_PARTICIPANT = "";
	 private static final String GET_ALL_PARTICIPANT = "";
	
	
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
	 * M�thode permettant de modifier le mot de passe d'un participant via un objet participant et une string en param�tre.
	 * @param participant
	 * @param motDePasse
	 */
	 public static void modifyParticipantMDP(Participant participant, String motDePasse) {
		 
		 
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
	 * @param sortie
	 * @return liste de Participant
	 * @throws SQLException
	 */
	public static List<Participant> getParticipants (Sortie sortie) throws SQLException {
		return getParticipants(sortie.getId() );
	}
	 
	/**
	* M�thode permettant de faire des recherches en fonction d'un id de sortie
	* @param idSortie
	* @return liste de Participant
	* @throws SQLException
	*/
	
	public static List<Participant> getParticipants (int idSortie) throws SQLException {
	
		return null;
	}
	
}
