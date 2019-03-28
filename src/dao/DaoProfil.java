package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Participant;
import bll.Mappage;



// TODO: Auto-generated Javadoc
/**
 * The Class DaoProfil.
 */
public class DaoProfil {
	
	// false pour l'admin, on ne peut pas créer un membre qui soit directement admin)
//	 private static final String ADD_PARTICIPANT = "INSERT INTO participants VALUES (?,?, ?,?,?, ?,'false',?,?)";
	 
	 /** The Constant MODIFY_PARTICIPANT. */
	private static final String MODIFY_PARTICIPANT =  "UPDATE PARTICIPANTS "			 
	 		+ " SET pseudo=?, nom=?, prenom=?, telephone=?, mail=?,  sites_no_site=? " 
			+ " WHERE no_participant=? ";
	 
	 /** The Constant MODIFY_PARTICIPANTMDP. */
 	private static final String MODIFY_PARTICIPANTMDP = "UPDATE participants SET mot_de_passe=? WHERE no_participant=? ";
	 
	 /** The Constant DELETE_PARTICIPANT. */
	 private static final String DELETE_PARTICIPANT = "DELETE FROM participants WHERE no_participant =? ";
	 
	 /** The Constant GET_PARTICIPANT. */
 	private static final String GET_PARTICIPANT = "SELECT * FROM PARTICIPANTS inner join SITES on no_site = sites_no_site "
				+ " WHERE pseudo = ? "; 
	 
	 /** The Constant GET_PARTICIPANT_BY_ID. */
 	private static final String GET_PARTICIPANT_BY_ID = "SELECT * FROM PARTICIPANTS inner join SITES on no_site = sites_no_site  "
				+ " WHERE no_participant = ? "; 
	 
	 
	 /** The Constant GET_ALL_PARTICIPANT. */
 	private static final String GET_ALL_PARTICIPANT = "SELECT * FROM participants inner join SITES on no_site = sites_no_site " ;
	
	 /** The Constant GET_ALL_PARTICIPANT_BY_IDSORTIE. */
 	private static final String GET_ALL_PARTICIPANT_BY_IDSORTIE = "SELECT * FROM INSCRIPTIONS " + 
	 		"inner join PARTICIPANTS on participants_no_participant = no_participant " + 
	 		"inner join SITES on sites_no_site = no_site " + 
	 		"WHERE sorties_no_sortie = ? ";
	 
	 /** The Constant MODIFY_AVATAR. */
 	private static final String MODIFY_AVATAR = "UPDATE participants SET urlAvatar= ? WHERE no_participant= ? ";
	
	 /** The Constant ADD_PARTICIPANT. */
 	private static final String ADD_PARTICIPANT =  "insert into PARTICIPANTS " +
	 		" (pseudo, nom, prenom, telephone, mail, mot_de_passe, " +
	 		" administrateur, actif, sites_no_site) " +
	 		" VALUES (?, ?, ? ,? ,? ,? ,0 ,0 , ?)"	;
	 
	 /** The Constant SET_ADMIN. */
 	private static final String SET_ADMIN = "UPDATE participants SET administrateur = ? WHERE no_participant= ? ";
		
	
	// Singkleton !
	
	 /**
	 * Instantiates a new dao profil.
	 */
	private DaoProfil() {} 
	 
	 /** The instance. */
 	private static DaoProfil INSTANCE = new DaoProfil();
	 

	 
	 /**
 	 * Gets the single instance of DaoProfil.
 	 *
 	 * @return single instance of DaoProfil
 	 */
 	public static DaoProfil getInstance() {   
		 return INSTANCE; 
     }
	 
	 /**
 	 * Modify avatar.
 	 *
 	 * @param photoFileName the photo file name
 	 * @param id_participant the id participant
 	 * @throws SQLException the SQL exception
 	 */
 	public static void modifyAvatar(String photoFileName , int id_participant) throws SQLException {
		 
		 String sql = MODIFY_AVATAR;
		 System.out.println(photoFileName);
		 
		 DbConnexion dbConnexion = new DbConnexion();
		 
	       	Connection connection = dbConnexion.getConnection() ;
	    	PreparedStatement pStat = connection.prepareStatement(sql);
	        	
	            pStat.setString(1, photoFileName);
	            pStat.setInt(2, id_participant );	        	
	        	
	            pStat.executeUpdate(); 
	 }
	 
	 
		 
	 /**
 	 * Modify participant.
 	 *
 	 * @param participant the participant
 	 * @return the int
 	 * @throws SQLException the SQL exception
 	 */
 	/*
     * Méthode permettant de modifier un article via un objet article en paramétre.
     * @param article
     */
    public static int modifyParticipant(Participant participant) throws SQLException {

        String sql = MODIFY_PARTICIPANT;       
        
        DbConnexion dbConnexion = new DbConnexion();

        Connection connection = dbConnexion.getConnection() ;
    	PreparedStatement pStat = connection.prepareStatement(sql);

            pStat.setString(1, participant.getPseudo() );
            pStat.setString(2, participant.getNom() );
            pStat.setString(3, participant.getPrenom() );
            pStat.setString(4, participant.getTelephone() );
            pStat.setString(5, participant.getMail() );   
            pStat.setInt(6, participant.getSite().getIdSite() );
//            pStat.setString(7, participant.getImage());
            pStat.setInt(7, participant.getIdParticipant() );

            return pStat.executeUpdate() ;
    }
    
    /**
     * Modify participant MDP.
     *
     * @param mdp the mdp
     * @param no_partcipant the no partcipant
     * @return the int
     * @throws SQLException the SQL exception
     */
    //Modification du mdp
    public static  int modifyParticipantMDP( String mdp, int no_partcipant) throws SQLException {
    	
    	
    	String sql = MODIFY_PARTICIPANTMDP; 
    	
    	DbConnexion dbConnexion = new DbConnexion();
    	
    	Connection connection = dbConnexion.getConnection() ;
        PreparedStatement pStat = connection.prepareStatement(sql);
        		
    		pStat.setString(1, mdp);
    		pStat.setInt(2, no_partcipant);
    		
    		return pStat.executeUpdate();
    }


	/**
	 * Méthode permettant de supprimer un Participant via un objet Participant en paramétre.
	 *
	 * @param participant the participant
	 * @throws SQLException the SQL exception
	 */
	public static void deleteParticipant(Participant participant) throws SQLException {
		deleteParticipant(participant.getIdParticipant());
	}
	
	/**
	 * Surcharge de méthode permettant de supprimer un Participant via l'id du Participant en paramétre.
	 *
	 * @param idParticipant the id participant
	 * @throws SQLException the SQL exception
	 */
	public static void deleteParticipant(int idParticipant) throws SQLException {
		String sql = DELETE_PARTICIPANT;
		DbConnexion dbConnexion = new DbConnexion();
		Connection connection = dbConnexion.getConnection() ;
		PreparedStatement pStat = connection.prepareStatement(sql);
			
			pStat.setInt(1, idParticipant );
			
			pStat.executeUpdate() ;	
	}
	
    
    
	/**
	 * M�thode permettant de faire des recherches en fonction du pseudo.
	 *
	 * @param pseudo the pseudo
	 * @return Participant
	 * @throws SQLException the SQL exception
	 */
	public static Participant getParticipant (String pseudo) throws SQLException {
		
		ResultSet rs = null;
		String sql = GET_PARTICIPANT;
		
		DbConnexion dbConnexion = new DbConnexion();
		
		Participant participant = null;
		
		Connection connection = dbConnexion.getConnection();
	    PreparedStatement pStat = connection.prepareStatement(sql);
			
			pStat.setString(1, pseudo);
			
			rs = pStat.executeQuery();
			
			if( rs != null && rs.next()) {
				participant = Mappage.mappageParticipant(rs);
			}		
		return participant;
	}
	
	/**
	 * Gets the participant by id.
	 *
	 * @param id the id
	 * @return the participant by id
	 * @throws SQLException the SQL exception
	 */
	public static Participant getParticipantById (int id) throws SQLException {
		
		ResultSet rs = null;
		String sql = GET_PARTICIPANT_BY_ID;
		
		DbConnexion dbConnexion = new DbConnexion();
		
		Participant participant = null;
		
		Connection connection = dbConnexion.getConnection();
	    PreparedStatement pStat = connection.prepareStatement(sql);
			
			pStat.setInt(1, id);
			
			rs = pStat.executeQuery();
			
			if( rs != null && rs.next() ) {
				
				participant = Mappage.mappageParticipant(rs);
			}		
		return participant;
	}
	



	 
	/**
	 * M�thode permettant de faire des recherches en fonction d'un id de sortie.
	 *
	 * @return liste de Participant
	 * @throws SQLException the SQL exception
	 */
	public static List<Participant> getParticipants () throws SQLException {
		ResultSet rs = null;
		
		List<Participant> participants = new ArrayList<>();
		DbConnexion dbConnexion = new DbConnexion();
		
		String sql = GET_ALL_PARTICIPANT ;
		
		Connection connection = dbConnexion.getConnection();
		PreparedStatement pStat = connection.prepareStatement(sql);
			
			rs = pStat.executeQuery();
				
			if (rs != null) {
				while (rs.next()) {
					Participant participant = Mappage.mappageParticipant(rs);
					participants.add(participant);
				}
			}
		return participants;
	}
	
	/**
	 * Gets the participants by sortie.
	 *
	 * @param idSortie the id sortie
	 * @return the participants by sortie
	 * @throws SQLException the SQL exception
	 */
	public static List<Participant> getParticipantsBySortie (int idSortie) throws SQLException
	{
		ResultSet rs = null;
		
		String sql = GET_ALL_PARTICIPANT_BY_IDSORTIE;
		List<Participant> participants = new ArrayList<>();
		
		DbConnexion dbConnexion = new DbConnexion();
		
			Connection connection = dbConnexion.getConnection();
			PreparedStatement pStat = connection.prepareStatement(sql);
				pStat.setInt(1, idSortie );
		
				rs = pStat.executeQuery();
					
				if (rs != null) {
					
					while (rs.next()) {
						Participant participant = Mappage.mappageParticipant(rs);
						participants.add(participant);
					}
				}
			return participants;
	}

	/**
	 * Adds the participant.
	 *
	 * @param participant the participant
	 * @param mdp the mdp
	 */
	public static void addParticipant(Participant participant, String mdp) {
		String sql = ADD_PARTICIPANT;
		
		DbConnexion dbConnexion = new DbConnexion();
		try ( Connection connection = dbConnexion.getConnection() ; PreparedStatement pStat = connection.prepareStatement(sql) ){
			
			pStat.setString(1, participant.getPseudo() );
			pStat.setString(2, participant.getNom() );
			pStat.setString(3, participant.getPrenom() );
			
			String tel = participant.getTelephone();
			if (tel.equals("")) {
				pStat.setNull(4, java.sql.Types.VARCHAR);
			} else {
				pStat.setString(4, tel );
			}
			
			pStat.setString(5, participant.getMail() );
			pStat.setString(6, mdp );
			pStat.setInt(7, participant.getSite().getIdSite() );
			
			pStat.executeUpdate() ;
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Change admin.
	 *
	 * @param participant the participant
	 */
	public static void changeAdmin(Participant participant) {
		String sql = SET_ADMIN;
		
		DbConnexion dbConnexion = new DbConnexion();
		try ( Connection connection = dbConnexion.getConnection() ; PreparedStatement pStat = connection.prepareStatement(sql) ){
			
			pStat.setBoolean(1, participant.isAdministrateur() );
			pStat.setInt(2, participant.getIdParticipant() );
			
			pStat.executeUpdate() ;
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
	}
}
