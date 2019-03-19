package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Lieu;
import bean.Participant;
import bean.Site;
import bean.Ville;

public class DaoAdmin {
	// constantes de requetes sql
	
	private static final String ADD_VILLE = 
			"INSERT INTO VILLES "
			+ "( nom_ville, code_postal ) "
			+ "VALUES ( ?,? )";
	
	private static final String ADD_SITE = 
			"INSERT INTO SITES "
			+ "( nom_site ) "
			+ "VALUES ( ? )";
	
	private static final String ADD_LIEU = 
			"INSERT INTO LIEUX "
			+ "( nom_lieu, rue, latitude, longitude, villes_no_ville ) "
			+ "VALUES ( ?,? , ?,?,? )";
	
	private static final String ADD_PARTICIPANT = 
			"INSERT INTO PARTICIPANTS "
			+ "( pseudo, nom, prenom, telephone, mail,"
			+ " mot_de_passe, actif, sites_no_site ) "
			+ "VALUES ( ?,? , ?,?,? , ?,?,? )";
	
	//-------------------
	
	private static final String MODIFY_VILLE = 
			"UPDATE VILLES " + 
			"SET nom_ville = ?, code_postal = ? " + 
			"WHERE no_ville = ?";
	
	private static final String MODIFY_SITE = 
			"UPDATE SITES " + 
			"SET nom_site = ?" +
			"WHERE no_site = ?";
	
	private static final String MODIFY_LIEU = 
			"UPDATE LIEUX " + 
			"SET nom_lieu = ?, rue = ?, latitude = ?, " +
			"longitude = ?, villes_no_ville = ? " + 
			"WHERE no_lieu = ?";
	
	private static final String MODIFY_ADMIN = 
			"UPDATE PARTICIPANTS " + 
            "SET administrateur = ? " + 
            "WHERE no_participant = ?";
	
	//-------------------
	
	private static final String DELETE_VILLE  = "DELETE FROM VILLES " + 
				"WHERE no_ville = ?";
	
	private static final String DELETE_SITE  = "DELETE FROM SITES " + 
			"WHERE no_site = ?";
	
	private static final String DELETE_LIEU = "DELETE FROM LIEUX " + 
			"WHERE no_lieu = ?";
	
	private static final String DELETE_PARTICIPANT = "DELETE FROM PARTICIPANTS " + 
			"WHERE no_participant = ?";

	private static final String GET_VILLE = "select * FROM VILLES "
					+ " where nom_ville like ?";

	private static final String GET_SITE = "select * FROM SITES "
			+ " where nom_site like ?";
	
	private static final String GET_LIEU = "select * FROM LIEUX"
				+ " inner join VILLES on villes_no_ville = no_ville "
				+ " where nom_ville like ?";

	private static final String GET_PARTICIPANTS = "select * FROM PARTICIPANTS"
				+ " inner join SITES on no_site = sites_no_site "
				+ " where nom like ? and prenom like ? and pseudo like ? ";
	
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
	 * Méthode permettant de rajouter une Ville via un objet Ville en paramétre.
	 * @param ville
	 */
	public static void addVille (Ville ville) {
		
		String sql = ADD_VILLE;
		
		try ( Connection connection = DbConnexion.getConnection() ; PreparedStatement pStat = connection.prepareStatement(sql) ){
				
			pStat.setString(1, ville.getNom() );
			pStat.setString(2, ville.getCodePostal() );
			
			pStat.executeUpdate() ;
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void addSite (Site site) {
		
		String sql = ADD_SITE;
		
		try ( Connection connection = DbConnexion.getConnection() ; PreparedStatement pStat = connection.prepareStatement(sql) ){
				
			pStat.setString(1, site.getNom() );
			
			pStat.executeUpdate() ;
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Méthode permettant de rajouter un Lieu via un objet Lieu en paramétre.
	 * @param lieu
	 */
	public static void addLieu (Lieu lieu) {
		
		String sql = ADD_LIEU;
		
		try ( Connection connection = DbConnexion.getConnection() ; PreparedStatement pStat = connection.prepareStatement(sql) ){
				
			pStat.setString(1, lieu.getNom() );
			pStat.setString(2, lieu.getRue() );
			pStat.setFloat(3, lieu.getLatitude() );
			pStat.setFloat(4, lieu.getLongitude() );
			pStat.setInt(5, lieu.getVille().getIdVille() );
			
			pStat.executeUpdate() ;
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Méthode permettant de rajouter un Participant via un objet Participant en paramétre.
	 * @param participant
	 * @param motDePasse
	 */
	public static void addParticipant (Participant participant, String motDePasse) {
		
		String sql = ADD_PARTICIPANT;
		
		try ( Connection connection = DbConnexion.getConnection() ; PreparedStatement pStat = connection.prepareStatement(sql) ){
				
			pStat.setString(1, participant.getPseudo() );
			pStat.setString(2, participant.getNom() );
			pStat.setString(3, participant.getPrenom() );
			pStat.setString(4, participant.getTelephone() );
			pStat.setString(5, participant.getMail() );
			pStat.setString(6, motDePasse );
			pStat.setBoolean(7, participant.isActif() );
			pStat.setInt(8, participant.getSite().getIdSite() );
			
			pStat.executeUpdate() ;
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//===================
	// Update
	//===================
	
	/**
	 * Méthode permettant de modifier un article via un objet article en paramétre.
	 * @param article
	 */
	public static void modifyVille(Ville ville) {
		String sql = MODIFY_VILLE;
		
		try ( Connection connection = DbConnexion.getConnection() ; PreparedStatement pStat = connection.prepareStatement(sql) ){
				
			pStat.setString(1, ville.getNom() );
			pStat.setString(2, ville.getCodePostal() );
			pStat.setInt(3, ville.getIdVille() );
			
			pStat.executeUpdate() ;
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void modifySite(Site site) {
		String sql = MODIFY_SITE;
		
		try ( Connection connection = DbConnexion.getConnection() ; PreparedStatement pStat = connection.prepareStatement(sql) ){
				
			pStat.setString(1, site.getNom() );
			pStat.setInt(2, site.getIdSite() );
			
			pStat.executeUpdate() ;
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/** 
	 * Méthode permettant de modifier un article via un objet article en paramétre. 
	 * @param article 
	 */ 
	public static void modifyLieu(Lieu lieu) { 
		 
		String sql = MODIFY_LIEU;
		 
		try ( Connection connection = DbConnexion.getConnection() ; PreparedStatement pStat = connection.prepareStatement(sql) ){ 
			 
			pStat.setString(1, lieu.getNom() ); 
			pStat.setString(2, lieu.getRue() ); 
			pStat.setFloat(3, lieu.getLatitude() ); 
			pStat.setFloat(4, lieu.getLongitude() ); 
			pStat.setInt(5, lieu.getVille().getIdVille() ); 
			pStat.setInt(6, lieu.getId() ); 
			 
			pStat.executeUpdate() ; 
				 
		} catch (SQLException e) { 
			e.printStackTrace(); 
		} 
	} 
	
	/**
	 * Méthode permettant de modifier l'état admin d'un participant avec un boolean en paramétre.
	 * @param participant
	 * @param isAdmin
	 */
	
	 public static void modifyAdmin(Participant participant, boolean isAdmin) {

	        String sql = MODIFY_ADMIN;

	        try ( Connection connection = DbConnexion.getConnection() ; PreparedStatement pStat = connection.prepareStatement(sql) ){

	            pStat.setBoolean(1, participant.isAdministrateur() );
	            pStat.setInt(2, participant.getIdParticipant() );

	            pStat.executeUpdate() ;

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	
	
	
	//===================
	// Update
	//===================
	
	/**
	 * Méthode permettant de supprimer une Ville via un objet Ville en paramétre.
	 * @param ville
	 */
	public static void deleteVille(Ville ville) {
		String sql = DELETE_VILLE;
		try ( Connection connection = DbConnexion.getConnection() ; PreparedStatement pStat = connection.prepareStatement(sql) ){
			
			pStat.setInt(1, ville.getIdVille() );
			
			pStat.executeUpdate() ;
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public static void deleteSite(Site site) {
		String sql = DELETE_SITE;
		try ( Connection connection = DbConnexion.getConnection() ; PreparedStatement pStat = connection.prepareStatement(sql) ){
			
			pStat.setInt(1, site.getIdSite() );
			
			pStat.executeUpdate() ;
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Méthode permettant de supprimer un Lieu via un objet Lieu en paramétre.
	 * @param lieu
	 */
	public static void deleteLieu(Lieu lieu) {
		String sql = DELETE_LIEU;
		try ( Connection connection = DbConnexion.getConnection() ; PreparedStatement pStat = connection.prepareStatement(sql) ){
			
			pStat.setInt(1, lieu.getId() );
			
			pStat.executeUpdate() ;
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Méthode permettant de supprimer un Participant via un objet Participant en paramétre.
	 * @param participant
	 */
	public static void deleteParticipant(Participant participant) {
		deleteParticipant(participant.getIdParticipant());
	}
	
	/**
	 * Surcharge de méthode permettant de supprimer un Participant via l'id du Participant en paramétre.
	 * @param idParticipant
	 */
	public static void deleteParticipant(int idParticipant) {
		String sql = DELETE_PARTICIPANT;
		try ( Connection connection = DbConnexion.getConnection() ; PreparedStatement pStat = connection.prepareStatement(sql) ){
			
			pStat.setInt(1, idParticipant );
			
			pStat.executeUpdate() ;
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
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
		
		ResultSet rs = null;
		
		List<Ville> villes = new ArrayList<>();
		
		String sql = GET_VILLE;
		
		try ( Connection connection = DbConnexion.getConnection() ; PreparedStatement pStat = connection.prepareStatement(sql) ){
			
			pStat.setString(1, "%"+nom+"%" );
			
			rs = pStat.executeQuery();
				
			if (rs != null) {
				
				boolean bool = true;
				while (bool) {
					Ville ville = mappageVille(rs);
					
					if (ville == null) { // = null si il n'y a plus de ligne dans le rs, on ne peut pas test rs.next() car il y en a un aussi dans le mappage et on sauterai donc une ligne sur 2
						bool = false;
					} else {
						villes.add(ville);
					}
				}
				
			}
			
		}catch ( Exception exception )  {
			throw new RuntimeException( exception );
		}
		
		
		return villes;
	}
	
	
	public static List<Site> getSites (String nom) throws SQLException {
		
		ResultSet rs = null;
		
		List<Site> sites = new ArrayList<>();
		
		String sql = GET_SITE;
		
		try ( Connection connection = DbConnexion.getConnection() ; PreparedStatement pStat = connection.prepareStatement(sql) ){
			
			pStat.setString(1, "%"+nom+"%" );
			
			rs = pStat.executeQuery();
				
			if (rs != null) {
				
				boolean bool = true;
				while (bool) {
					Site site = mappageSite(rs);
					
					if (site == null) { // = null si il n'y a plus de ligne dans le rs, on ne peut pas test rs.next() car il y en a un aussi dans le mappage et on sauterai donc une ligne sur 2
						bool = false;
					} else {
						sites.add(site);
					}
				}
				
			}
			
		}catch ( Exception exception )  {
			throw new RuntimeException( exception );
		}
		
		
		return sites;
	}
	
	

	/**
	 * Méthode permettant de faire des recherches en fonction du nom rentré dans la barre de recherche
	 * @param nom
	 * @return liste de lieu
	 * @throws SQLException
	 */
	
	public static List<Lieu> getLieux (String nom) throws SQLException {
		
		ResultSet rs = null;
		
		List<Lieu> lieux = new ArrayList<>();
		
		String sql = GET_LIEU;
		
		try ( Connection connection = DbConnexion.getConnection() ; PreparedStatement pStat = connection.prepareStatement(sql) ){
			
			pStat.setString(1, "%"+nom+"%" );
			
			rs = pStat.executeQuery();
				
			if (rs != null) {
				
				boolean bool = true;
				while (bool) {
					Lieu lieu = mappageLieu(rs);
					
					if (lieu == null) { // = null si il n'y a plus de ligne dans le rs, on ne peut pas test rs.next() car il y en a un aussi dans le mappage et on sauterai donc une ligne sur 2
						bool = false;
					} else {
						lieux.add(lieu);
					}
				}
				
			}
			
		}catch ( Exception exception )  {
			throw new RuntimeException( exception );
		}
		
		
		return lieux;
	}
	
	/**
	 * Méthode permettant de faire des recherches en fonction du nom rentré dans la barre de recherche
	 * @param nom
	 * @param prenom
	 * @param pseudo
	 * @return liste de Participants
	 * @throws SQLException
	 */
	
	public static List<Participant> getParticipants (String nom, String prenom, String pseudo) throws SQLException {
		ResultSet rs = null;
		
		List<Participant> participants = new ArrayList<>();
		
		String sql = GET_PARTICIPANTS;
		
		try ( Connection connection = DbConnexion.getConnection() ; PreparedStatement pStat = connection.prepareStatement(sql) ){
			
			pStat.setString(1, "%"+nom+"%" );
			pStat.setString(2, "%"+prenom+"%" );
			pStat.setString(3, "%"+pseudo+"%" );
			
			rs = pStat.executeQuery();
				
			if (rs != null) {
				
				boolean bool = true;
				while (bool) {
					Participant participant = mappageParticipant(rs);
					
					if (participant == null) { // = null si il n'y a plus de ligne dans le rs, on ne peut pas test rs.next() car il y en a un aussi dans le mappage et on sauterai donc une ligne sur 2
						bool = false;
					} else {
						participants.add(participant);
					}
				}
				
			}
			
		}catch ( Exception exception )  {
			throw new RuntimeException( exception );
		}
		
		
		return participants;
	}
	
	/**
	 * map une ville avec un result set (évite la duplication de code, possiblement)
	 * @param rs
	 * @return ville
	 */
	
	// mappages
	
	private static Ville mappageVille(ResultSet rs) {
		
		Ville ville = null;
		
		try {
			if (rs.next()) {

				int id = rs.getInt("no_ville");
				String nom = rs.getString("nom_ville");
				String codePostal = rs.getString("code_postal");
				
				ville = new Ville(id, nom, codePostal);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ville;
	}
	
	
	private static Site mappageSite(ResultSet rs) {
		Site site = null;
		
		try {
			if (rs.next()) {

				int id = rs.getInt("no_site");
				String nom = rs.getString("nom_site");
				
				site = new Site(id, nom);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return site;
	}
	
	/**
	 * map un lieu avec un result set (évite la duplication de code, possiblement)
	 * @param rs
	 * @return lieu
	 */
	
	private static Lieu mappageLieu(ResultSet rs) {
		
		Lieu lieu = null;
		
		try {
			if (rs.next()) {
				
				int id = rs.getInt("no_ville");
				String nom = rs.getString("nom_ville");
				String codePostal = rs.getString("code_postal");
				
				Ville ville = new Ville(id, nom, codePostal);

				id = rs.getInt("no_lieu");
				nom = rs.getString("nom_lieu");
				String rue = rs.getString("rue");
				float latitude = rs.getFloat("latitude");
				float longitude = rs.getFloat("longitude");
				
				lieu = new Lieu(id, nom, rue, ville, latitude, longitude);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lieu;
	}
	
	/**
	 * map un participant avec un result set (évite la duplication de code, possiblement)
	 * @param rs
	 * @return participant
	 */
	
	private static Participant mappageParticipant(ResultSet rs) {
		
		Participant participant = null;
		
		try {
			if (rs.next()) {
				
				Site site = new Site(rs.getInt("sites_no_site"), rs.getString("nom_site"));
				
				
				int id = rs.getInt("no_ville");
				String pseudo = rs.getString("pseudo");
				String nom = rs.getString("nom");
				String prenom = rs.getString("prenom");
				String telephone = rs.getString("telephone");
				String mail = rs.getString("mail");
				boolean administrateur = rs.getBoolean("administrateur");
				boolean actif = rs.getBoolean("actif");
				//String image = rs.getString("image");
				String image = "";
				
				participant = new Participant(id, pseudo, nom, prenom, 
								telephone, mail, administrateur, actif, site, image);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return participant;
	}
	
}
