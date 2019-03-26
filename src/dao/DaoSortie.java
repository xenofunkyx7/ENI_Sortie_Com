package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BLL.Mappage;
import bean.Sortie;

public class DaoSortie {

	private static final String ADD_SORTIE = 
			"INSERT INTO SORTIES "
			+ "VALUES ( ?,?,?,?,?, ?,?,?,?,?, ? )";
	
	 private static final String GET_SORTIE = "SELECT * FROM SORTIES "
				+ " WHERE no_sortie = ? "; 
	 
	 private static final String GET_SORTIES = 
			 "select " + 
			 "	no_sortie, Sorties.nom as 'nom_sortie', datedebut, duree, datecloture, " + 
			 "	nbinscriptionsmax, descriptioninfos, urlPhoto, organisateur, etats_no_etat, " + 
			 
			 "	no_lieu ,nom_lieu, rue, latitude, longitude, " + 

			 "	no_ville, nom_ville, code_postal, " + 
			 
			 "	no_participant, pseudo, PARTICIPANTS.nom, prenom, telephone, mail, "
			 + " mot_de_passe, administrateur, actif, urlAvatar, " + 

			 "	siteSortie.no_site as 'sortie_no_site' , siteSortie.nom_site as 'sortie_nom_site', " + 

			 "	siteOrga.no_site, siteOrga.nom_site " + 

			 "	from SORTIES " + 
			 "		inner join LIEUX on lieux_no_lieu = no_lieu " + 
			 "		inner join VILLES on lieux.villes_no_ville = villes.no_ville " + 
			 "		inner join SITES siteSortie on sites_no_site = siteSortie.no_site " + 
			 "		inner join PARTICIPANTS on organisateur = PARTICIPANTS.no_participant " + 
			 "		inner join SITES siteOrga on PARTICIPANTS.sites_no_site = siteOrga.no_site;"; 
	
	
	 /**
	 * M�thode permettant de rajouter un sortie via un objet sortie + utilisateur en param�tre.
	 * @param sortie
	 */
	 public static int addSortie (Sortie sortie, int idCreateur) {
		 	
			String sql = ADD_SORTIE;
			int generatedId = -1;
			
			DbConnexion dbConnexion = new DbConnexion();
			
			try ( Connection connection = dbConnexion.getConnection() ; PreparedStatement pStat = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS) ){
				
				pStat.setString(1, sortie.getNom() );
				pStat.setDate(2, sortie.getDateHeureDebut() );
				pStat.setInt(3, sortie.getDuree() );
				pStat.setDate(4, sortie.getDateLimiteInscription() );
				pStat.setInt(5, sortie.getNbInscriptionMax() );
				pStat.setString(6, sortie.getInfoSortie() );
				pStat.setString(7, sortie.getImage());
				pStat.setInt(8, idCreateur );
				pStat.setInt(9, sortie.getLieu().getId() );
				pStat.setInt(10, sortie.getEtat().ordinal() + 1 );
				pStat.setInt(11, sortie.getSite().getIdSite());
 
				pStat.executeUpdate() ;
				
				ResultSet rs = pStat.getGeneratedKeys();
				
				if (rs != null && rs.next() ) 
				{
					generatedId = rs.getInt(1);
				}
					
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("bug1");
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

			if( rs != null && rs.next() ) {
				
				sortie = Mappage.mappageSortie(rs);
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
	public static List<Sortie> getSorties () throws SQLException {
		ResultSet rs = null;
		
		List<Sortie> sorties = new ArrayList<>();
		
		String sql = GET_SORTIES;
		
		DbConnexion dbConnexion = new DbConnexion();
		try ( Connection connection = dbConnexion.getConnection() ; PreparedStatement pStat = connection.prepareStatement(sql) ){
			
			rs = pStat.executeQuery();
				
			if (rs != null) {
				
				while (rs.next()) {
					Sortie sortie = Mappage.mappageSortie(rs);
					sorties.add(sortie);
				}
				
			}
			
		}catch ( Exception exception )  {
			throw new RuntimeException( exception );
		}
		
		return sorties;
	}
	
}
