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
		
		String sql = "INSERT INTO VILLES "
				+ "( no_ville, nom_ville, code_postal ) "
				+ "VALUES ( ?,?,? )";
		
		try ( Connection connection = DbConnexion.getConnection() ; PreparedStatement pStat = connection.prepareStatement(sql) ){
				
			pStat.setInt(1, ville.getIdVille());
			pStat.setString(2, ville.getNom() );
			pStat.setString(3, ville.getCodePostal() );
			
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
		
		String sql = "INSERT INTO LIEUX "
				+ "( no_lieu, nom_lieu, rue, latitude, longitude, villes_no_ville ) "
				+ "VALUES ( ?,?,? , ?,?,? )";
		
		try ( Connection connection = DbConnexion.getConnection() ; PreparedStatement pStat = connection.prepareStatement(sql) ){
				
			pStat.setInt(1, lieu.getId() );
			pStat.setString(2, lieu.getNom() );
			pStat.setString(3, lieu.getRue() );
			pStat.setFloat(4, lieu.getLatitude() );
			pStat.setFloat(5, lieu.getLongitude() );
			pStat.setInt(6, lieu.getVille().getIdVille() );
			
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
		
		String sql = "INSERT INTO PARTICIPANTS "
				+ "( no_participant, pseudo, nom, prenom, telephone, mail,"
				+ " mot_de_passe, administrateur, actif, sites_no_site ) "
				+ "VALUES ( ?,?,? , ?,?,? , ?,?,? )";
		
		try ( Connection connection = DbConnexion.getConnection() ; PreparedStatement pStat = connection.prepareStatement(sql) ){
				
			pStat.setInt(1, participant.getIdParticipant() );
			pStat.setString(2, participant.getPseudo() );
			pStat.setString(3, participant.getNom() );
			pStat.setString(4, participant.getPrenom() );
			pStat.setString(5, participant.getTelephone() );
			pStat.setString(6, participant.getMail() );
			pStat.setString(7, motDePasse );
			pStat.setBoolean(8, participant.isAdministrateur() );
			pStat.setBoolean(9, participant.isActif() );
			pStat.setInt(10, participant.getSite().getIdSite() );
			
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
		String sql = "UPDATE VILLES " + 
				"SET nom_ville = ?, code_postal = ? " + 
				"WHERE no_ville = ?";
		
		try ( Connection connection = DbConnexion.getConnection() ; PreparedStatement pStat = connection.prepareStatement(sql) ){
				
			pStat.setString(1, ville.getNom() );
			pStat.setString(2, ville.getCodePostal() );
			pStat.setInt(3, ville.getIdVille() );
			
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
		
		String sql = "UPDATE LIEUX " + 
				"SET nom_lieu = ?, rue = ?, latitude = ?, " +
				"longitude = ?, villes_no_ville = ? " + 
				"WHERE no_lieu = ?";
		
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
	 * Méthode permettant de modifier un article via un objet article en paramétre.
	 * @param article
	 */
	public static void modifyParticipant(Participant participant) {
		
		String sql = "UPDATE PARTICIPANTS " + 
				"SET pseudo = ?, nom = ?, prenom = ?, telephone = ?" +
				", mail = ?, administrateur = ?, actif = ?, sites_no_site = ? " + 
				"WHERE no_participant = ?";
		
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
	
	//===================
	// Update
	//===================
	
	/**
	 * Méthode permettant de supprimer une Ville via un objet Ville en paramétre.
	 * @param ville
	 */
	public static void deleteVille(Ville ville) {
		String sql = "DELETE FROM VILLES " + 
				"WHERE no_ville = ?";
		try ( Connection connection = DbConnexion.getConnection() ; PreparedStatement pStat = connection.prepareStatement(sql) ){
			
			pStat.setInt(1, ville.getIdVille() );
			
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
		String sql = "DELETE FROM LIEUX " + 
				"WHERE no_lieu = ?";
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
		delete(participant.getIdParticipant());
	}
	
	/**
	 * Surcharge de méthode permettant de supprimer un Participant via l'id du Participant en paramétre.
	 * @param idParticipant
	 */
	public static void delete(int idParticipant) {
		String sql = "DELETE FROM PARTICIPANTS " + 
				"WHERE no_participant = ?";
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
		
		String sql = "select * FROM VILLES "
					+ " where nom_ville like ?";
		
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
	
	/**
	 * Méthode permettant de faire des recherches en fonction du nom rentré dans la barre de recherche
	 * @param nom
	 * @return liste de lieu
	 * @throws SQLException
	 */
	
	public static List<Lieu> getLieux (String nom) throws SQLException {
		
		ResultSet rs = null;
		
		List<Lieu> lieux = new ArrayList<>();
		
		String sql = "select * FROM LIEUX"
				+ " inner join VILLES on villes_no_ville = no_ville "
					+ " where nom_ville like ?";
		
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
		
		String sql = "select * FROM PARTICIPANTS"
				+ " inner join SITES on no_site = sites_no_site "
					+ " where nom like ? and prenom like ? and pseudo like ? ";
		
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
