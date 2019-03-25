package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import bean.Lieu;
import bean.Participant;
import bean.Site;
import bean.Sortie;
import bean.Sortie.Etats;

public class DaoSortie {

	private static final String ADD_SORTIE = 
			"INSERT INTO SORTIES "
			+ "( nom, datedebut , duree, datecloture, nbinscriptionsmax, descriptioninfos, "
			+ " organisateur, lieux_no_lieu, etats_no_etat) "
			+ "VALUES ( ?,?,?,?,?,?,?,?,?,? )";
	
	 private static final String GET_SORTIE = "SELECT * FROM SORTIES "
				+ " WHERE no_sortie = ? "; 
	
	
	 /**
	 * M�thode permettant de rajouter un sortie via un objet sortie + utilisateur en param�tre.
	 * @param sortie
	 */
	 public static int addSortie (Sortie sortie, int idCreateur) {
		 	
			String sql = ADD_SORTIE;
			int generatedId = -1;
			
			DbConnexion dbConnexion = new DbConnexion();
			
			try ( Connection connection = dbConnexion.getConnection() ; PreparedStatement pStat = connection.prepareStatement(sql) ){
				
				pStat.setString(1, sortie.getNom() );
				pStat.setDate(2, sortie.getDateHeureDebut() );
				pStat.setInt(3, sortie.getDuree() );
				pStat.setDate(4, sortie.getDateLimiteInscription() );
				pStat.setInt(5, sortie.getNbInscriptionMax() );
				pStat.setString(6, sortie.getInfoSortie() );
				pStat.setInt(7, idCreateur );
				pStat.setInt(8, sortie.getLieu().getId() );
				pStat.setInt(9, sortie.getEtat().ordinal() + 1 );
 
				pStat.executeUpdate() ;
				ResultSet rs = pStat.getGeneratedKeys();
				
				if (rs != null && rs.first() ) 
				{
					generatedId = rs.getInt(1);
				}
					
			} catch (SQLException e) {
				//TODO erreur d'envoie à la base de donné
			}
			
			return generatedId; 
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
	
		ResultSet rs = null;
		Sortie sortie = null;
		String sql = GET_SORTIE;
		
		DbConnexion dbConnexion = new DbConnexion();
		
		try( Connection connection = dbConnexion.getConnection() ;
	    		PreparedStatement pStat = connection.prepareStatement(sql) ){
			
			pStat.setInt(1, idSortie);
			
			rs = pStat.executeQuery();

			if( rs != null  ) {
				
				sortie = mappageSortie(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace(); //TODO 
		}
		
		return sortie;
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
	
	private static Sortie mappageSortie(ResultSet rs) {
		
		Sortie sortie = null;
		Site site = null;
		List<Participant> participants = new ArrayList<>();
		
		try {
			if (rs.next()) {
				
				int no_sortie = rs.getInt("no_sortie");
				
				String nom = rs.getString("nom");
				Date datedebut = rs.getDate("datedebut");
				int duree = rs.getInt("duree");
				Date datecloture = rs.getDate("datecloture");
				int nbinscriptionsmax = rs.getInt("nbinscriptionsmax");
				String descriptioninfos = rs.getString("descriptioninfos");
				
				/*
				 * Récupération de l'etat (enum) par sa value
				 */
				int etats_no_etat = rs.getInt("etats_no_etat");
				Etats etat = Etats.values()[etats_no_etat - 1];

				/*
				 * Récupération Objet Participant par l'id de l'organisateur
				 */
				int idOrganisateur = rs.getInt("organisateur");
				Participant orga = DaoProfil.getParticipantById(idOrganisateur);
				
				/*
				 * Récupération de la list des participant par l'id de la Sortie
				 */
				participants = DaoProfil.getParticipantsBySortie(no_sortie);
				
				/*
				 * Récupération de l'objet Lieu par sont id
				 */
				int lieux_no_lieu = rs.getInt("lieux_no_lieu");
				Lieu lieu = DaoLieu.getlieu(lieux_no_lieu);
				
				site = orga.getSite();
				
				/*
				 * TODO
				 */
				String urlPhoto = null ; //rs.getString("urlPhoto");
				
				
				
				
				sortie = new Sortie(no_sortie, nom, datedebut, duree, 
						datecloture, nbinscriptionsmax, descriptioninfos, etat, orga, participants , lieu, site , urlPhoto );	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return sortie;
	}
}
