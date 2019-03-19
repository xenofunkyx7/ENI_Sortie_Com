package dao;

import java.sql.SQLException;
import java.util.List;

import bean.Participant;
import bean.Sortie;

public class DaoProfil {
	
	// false pour l'admin, on ne peut pas créer un membre qui soit directement admin)
	 private static final String ADD_PARTICIPANT = "INSERT INTO participants VALUES (?,?,?,?,?,?,'false',?,?)";
	 private static final String MODIFY_PARTICIPANT =  "UPDATE PARTICIPANTS "
	 		+ "SET pseudo = ?, nom = ?, prenom = ?, telephone = ?, mail = ?, actif = ?, sites_no_site = ? " 
			+ "WHERE no_participant = ?";	 
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
	 
	 /*
     * Méthode permettant de modifier un article via un objet article en paramétre.
     * @param article
     */
    public static void modifyParticipant(Participant participant) {

        String sql = MODIFY_PARTICIPANT;

        try ( Connection connection = DbConnexion.getConnection() ; PreparedStatement pStat = connection.prepareStatement(sql) ){


            pStat.setString(1, participant.getPseudo() );
            pStat.setString(2, participant.getNom() );
            pStat.setString(3, participant.getPrenom() );
            pStat.setString(4, participant.getTelephone() );
            pStat.setString(5, participant.getMail() );
            pStat.setBoolean(6, participant.isAdministrateur() );
            pStat.setBoolean(7, participant.isActif() );
            pStat.setInt(8, participant.getSite().getIdSite() );
            pStat.setInt(9, participant.getIdParticipant() );

            pStat.executeUpdate() ;

        } catch (SQLException e) {
            e.printStackTrace();
        }
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
