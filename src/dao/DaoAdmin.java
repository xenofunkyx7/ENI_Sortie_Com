package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import bean.Lieu;
import bean.Participant;
import bean.Ville;

public class DaoAdmin {
	 // Singkleton !
	
	 private DaoAdmin() {} 
	 
	 private static DaoAdmin INSTANCE = new DaoAdmin();
	 
	 public static DaoAdmin getInstance() {   
		 return INSTANCE;
     }
	
	//===================
	// Add
	//===================
	
	/**
	 * Méthode permettant de rajouter une Ville via un objet Ville en paramètre.
	 * @param ville
	 */
	public static void addVille (Ville ville) {
		
		String sql = "INSERT INTO VILLES "
				+ "( no_ville, nom_ville, code_postal ) "
				+ "VALUES ( ?,?,? )";
		
		try ( Connection connection = DbConnexion.getConnection() ; PreparedStatement pStat = connection.prepareStatement(sql) ){
				
			pStat.setInt(1, ville.getIdVille());
			pStat.setString(2, ville.getNom() );
			pStat.setInt(3, ville.getCodePostal() );
			
			pStat.executeUpdate() ;
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Méthode permettant de rajouter un Lieu via un objet Lieu en paramètre.
	 * @param lieu
	 */
	public static void addLieu (Lieu lieu) {
		
		
	}
	
	/**
	 * Méthode permettant de rajouter un Participant via un objet Participant en paramètre.
	 * @param participant
	 */
	public static void addParticipant (Participant participant) {
		
		
	}
	
	//===================
	// Update
	//===================
	
	/**
	 * Méthode permettant de modifier un article via un objet article en paramètre.
	 * @param article
	 */
	public static void modifyVille(Ville ville) {
		
		
	}
	
	/**
	 * Méthode permettant de modifier un article via un objet article en paramètre.
	 * @param article
	 */
	public static void modifyLieu(Lieu lieu) {
		
		
	}
	
	/**
	 * Méthode permettant de modifier un article via un objet article en paramètre.
	 * @param article
	 */
	public static void modifyParticipant(Participant participant) {
		
		
	}
	
	//===================
	// Update
	//===================
	
	/**
	 * Méthode permettant de supprimer une Ville via un objet Ville en paramètre.
	 * @param ville
	 */
	public static void deleteVille(Ville ville) {
		
		
	}

	/**
	 * Méthode permettant de supprimer un Lieu via un objet Lieu en paramètre.
	 * @param lieu
	 */
	public static void deleteLieu(Lieu lieu) {
		
		
	}

	/**
	 * Méthode permettant de supprimer un Participant via un objet Participant en paramètre.
	 * @param participant
	 */
	public static void deleteParticipant(Participant participant) {
		delete(participant.getIdParticipant());
	}
	
	/**
	 * Surcharge de méthode permettant de supprimer un Participant via l'id du Participant en paramètre.
	 * @param idParticipant
	 */
	public static void delete(int idParticipant) {
		
		
	}
	
	//===================
	// Select & search
	//===================
	
	/**
	 * Méthode permettant de faire des recherches en fonction du nom rentré dans la barre de recherche
	 * @param nom
	 * @return liste de ville
	 * @throws SQLException
	 */
	
	public static List<Ville> getVilles (String nom) throws SQLException {
		
		// utiliser %like% et non "="
		
		return null;
	}
	
	/**
	 * Méthode permettant de faire des recherches en fonction du nom rentré dans la barre de recherche
	 * @param nom
	 * @return liste de lieu
	 * @throws SQLException
	 */
	
	public static List<Ville> getLieux (String nom) throws SQLException {
		
		// utiliser %like% et non "="
		
		return null;
	}
	
	/**
	 * Méthode permettant de faire des recherches en fonction du nom rentré dans la barre de recherche
	 * @param nom
	 * @param prenom
	 * @param pseudo
	 * @return liste de Participants
	 * @throws SQLException
	 */
	
	public static List<Ville> getParticipants (String nom, String prenom, String pseudo) throws SQLException {
		
		// utiliser %like% et non "="
		
		return null;
	}
}
