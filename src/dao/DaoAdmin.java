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
	

	

	

	
	private static final String ADD_PARTICIPANT = 
			"INSERT INTO PARTICIPANTS "
			+ "( pseudo, nom, prenom, telephone, mail,"
			+ " mot_de_passe, actif, sites_no_site ) "
			+ "VALUES ( ?,? , ?,?,? , ?,?,? )";
	
	//-------------------
	

	

	

	
	private static final String MODIFY_ADMIN = 
			"UPDATE PARTICIPANTS " + 
            "SET administrateur = ? " + 
            "WHERE no_participant = ?";
	
	//-------------------
	

	

	

	
	private static final String DELETE_PARTICIPANT = "DELETE FROM PARTICIPANTS " + 
			"WHERE no_participant = ?";




	


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
	 * Méthode permettant de rajouter un Participant via un objet Participant en paramétre.
	 * @param participant
	 * @param motDePasse
	 */
	public static void addParticipant (Participant participant, String motDePasse) {
		
		String sql = ADD_PARTICIPANT;
		
		DbConnexion dbConnexion = new DbConnexion();
		try ( Connection connection = dbConnexion.getConnection() ; PreparedStatement pStat = connection.prepareStatement(sql) ){
				
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
	 * Méthode permettant de modifier l'état admin d'un participant avec un boolean en paramétre.
	 * @param participant
	 * @param isAdmin
	 */
	
	 public static void modifyAdmin(Participant participant, boolean isAdmin) {

	        String sql = MODIFY_ADMIN;

	        DbConnexion dbConnexion = new DbConnexion();
			try ( Connection connection = dbConnexion.getConnection() ; PreparedStatement pStat = connection.prepareStatement(sql) ){

	            pStat.setBoolean(1, participant.isAdministrateur() );
	            pStat.setInt(2, participant.getIdParticipant() );

	            pStat.executeUpdate() ;

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	
	
	
	//===================
	// Delete
	//===================
	

	

	
	

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
		DbConnexion dbConnexion = new DbConnexion();
		try ( Connection connection = dbConnexion.getConnection() ; PreparedStatement pStat = connection.prepareStatement(sql) ){
			
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
	 * @param prenom
	 * @param pseudo
	 * @return liste de Participants
	 * @throws SQLException
	 */
	
	public static List<Participant> getParticipants (String nom, String prenom, String pseudo) throws SQLException {
		ResultSet rs = null;
		
		List<Participant> participants = new ArrayList<>();
		
		String sql = GET_PARTICIPANTS;
		
		DbConnexion dbConnexion = new DbConnexion();
		try ( Connection connection = dbConnexion.getConnection() ; PreparedStatement pStat = connection.prepareStatement(sql) ){
			
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
	 * map un participant avec un result set (évite la duplication de code, possiblement)
	 * @param rs
	 * @return participant
	 */
	private static Participant mappageParticipant(ResultSet rs) {
		
		Participant participant = null;
		
		try {
			if (rs.next()) {
				
				Site site = new Site(rs.getInt("sites_no_site"), rs.getString("nom_site"));
				
				
				int id = rs.getInt("no_participant");
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
