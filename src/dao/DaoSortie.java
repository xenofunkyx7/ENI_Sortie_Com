package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import bean.Participant;
import bean.Site;
import bean.Sortie;

public class DaoSortie {
	// Singkleton !
	
	 private DaoSortie() {} 
	 
	 private static DaoSortie INSTANCE = new DaoSortie();
	 
	 public static DaoSortie getInstance() {   
		 return INSTANCE;
     }
	
	 /**
	 * M�thode permettant de rajouter un sortie via un objet sortie en param�tre.
	 * @param sortie
	 */
	 public static void addSortie (Sortie sortie) {
		
		
	 }
	 
	 /**
	 * M�thode permettant de modifier un sortie via un objet sortie en param�tre.
	 * @param sortie
	 */
	 public static void modifySortie(Sortie sortie) {
		 
		 
	 }

	 /**
	 * M�thode permettant de supprimer un sortie via un objet sortie en param�tre.
	 * @param sortie
	 */
	 public static void deleteSortie(Sortie sortie) {
		 
		 
	 }
		 
	 /**
	* M�thode permettant de faire des recherches en fonction d'un id de sortie
	* @return Sortie
	* @throws SQLException
	*/
	
	public static Sortie getSortie (int idSortie) throws SQLException {
	
		return null;
	}
		 
	
	/**
	* M�thode permettant de faire des recherches en fonction de plusieurs param
	* @param nom
	 * @param site
	 * @param dateDebut
	 * @param dateFin
	 * @param participant
	 * @param isOrganisateur
	 * @param isInscrit
	 * @param notInscrit
	 * @param isPasse
	* @return liste de Sortie
	* @throws SQLException
	*/
	
	public static List<Sortie> getSorties (String nom, Site site, 
											Date dateDebut, Date dateFin,
											Participant participant, boolean isOrganisateur,
											boolean isInscrit, boolean notInscrit, 
											boolean isPasse) throws SQLException {
		// check si null alors mettre =1 � la place
		
		return null;
	}
}
