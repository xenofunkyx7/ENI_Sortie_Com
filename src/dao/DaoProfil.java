package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Participant;
import bll.Mappage;


public class DaoProfil {
	
	// false pour l'admin, on ne peut pas créer un membre qui soit directement admin)
//	 private static final String ADD_PARTICIPANT = "INSERT INTO participants VALUES (?,?, ?,?,?, ?,'false',?,?)";
	 
	 private static final String MODIFY_PARTICIPANT =  "UPDATE PARTICIPANTS "			 
	 		+ " SET pseudo=?, nom=?, prenom=?, telephone=?, mail=?,  sites_no_site=? " 
			+ " WHERE no_participant=? ";
	 
	 private static final String MODIFY_PARTICIPANTMDP = "UPDATE participants SET mot_de_passe=? WHERE no_participant=? ";
	 
	 private static final String DELETE_PARTICIPANT = "DELETE FROM participants WHERE no_participant =? ";
	 
	 private static final String GET_PARTICIPANT = "SELECT * FROM PARTICIPANTS inner join SITES on no_site = sites_no_site "
				+ " WHERE pseudo = ? "; 
	 
	 private static final String GET_PARTICIPANT_BY_ID = "SELECT * FROM PARTICIPANTS inner join SITES on no_site = sites_no_site  "
				+ " WHERE no_participant = ? "; 
	 
	 
	 private static final String GET_ALL_PARTICIPANT = "SELECT * FROM participants inner join SITES on no_site = sites_no_site " ;
	
	 private static final String GET_ALL_PARTICIPANT_BY_IDSORTIE = "SELECT * FROM INSCRIPTIONS " + 
	 		"inner join PARTICIPANTS on participants_no_participant = no_participant " + 
	 		"inner join SITES on sites_no_site = no_site " + 
	 		"WHERE sorties_no_sortie = ? ";
	 
	 private static final String MODIFY_AVATAR = "UPDATE participants SET urlAvatar= ? WHERE no_participant= ? ";
	
	 private static final String ADD_PARTICIPANT =  "insert into PARTICIPANTS " +
	 		" (pseudo, nom, prenom, telephone, mail, mot_de_passe, " +
	 		" administrateur, actif, sites_no_site) " +
	 		" VALUES (?, ?, ? ,? ,? ,? ,0 ,0 , ?)"	;
	 
	 private static final String SET_ADMIN = "UPDATE participants SET administrateur = ? WHERE no_participant= ? ";
		
	
	// Singkleton !
	
	 private DaoProfil() {} 
	 
	 private static DaoProfil INSTANCE = new DaoProfil();
	 

	 
	 public static DaoProfil getInstance() {   
		 return INSTANCE; 
     }
	 
	 public static void modifyAvatar(String photoFileName , int id_participant) {
		 
		 String sql = MODIFY_AVATAR;
		 System.out.println(photoFileName);
		 
		 DbConnexion dbConnexion = new DbConnexion();
		 
	        try ( Connection connection = dbConnexion.getConnection() ;
	    		PreparedStatement pStat = connection.prepareStatement(sql) ){
	        	
	            pStat.setString(1, photoFileName);
	            pStat.setInt(2, id_participant );	        	
	        	
	            pStat.executeUpdate(); 
	            
	        } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
	 
	 
		 
	 /*
     * Méthode permettant de modifier un article via un objet article en paramétre.
     * @param article
     */
    public static int modifyParticipant(Participant participant) {

        String sql = MODIFY_PARTICIPANT;
        int resultModif = 0;
        
        DbConnexion dbConnexion = new DbConnexion();
        
        

        try ( Connection connection = dbConnexion.getConnection() ;
    		PreparedStatement pStat = connection.prepareStatement(sql) ){


            pStat.setString(1, participant.getPseudo() );
            pStat.setString(2, participant.getNom() );
            pStat.setString(3, participant.getPrenom() );
            pStat.setString(4, participant.getTelephone() );
            pStat.setString(5, participant.getMail() );   
            pStat.setInt(6, participant.getSite().getIdSite() );
//            pStat.setString(7, participant.getImage());
            pStat.setInt(7, participant.getIdParticipant() );

            return resultModif = pStat.executeUpdate() ;

        } catch (SQLException e) {
            e.printStackTrace();
            
            return resultModif;
        }
    }
    
    //Modification du mdp
    public static  int modifyParticipantMDP( String mdp, int no_partcipant) {
    	
    	int resultModif = 0;
    	String sql = MODIFY_PARTICIPANTMDP; 
    	
    	DbConnexion dbConnexion = new DbConnexion();
    	
    	try ( Connection connection = dbConnexion.getConnection() ;
        		PreparedStatement pStat = connection.prepareStatement(sql)){
        		
    		pStat.setString(1, mdp);
    		pStat.setInt(2, no_partcipant);
    		
    		return resultModif = pStat.executeUpdate();
    		
    		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return resultModif;
			
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
		DbConnexion dbConnexion = new DbConnexion();
		try ( Connection connection = dbConnexion.getConnection() ; PreparedStatement pStat = connection.prepareStatement(sql) ){
			
			pStat.setInt(1, idParticipant );
			
			pStat.executeUpdate() ;
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
    
    
	/**
	* M�thode permettant de faire des recherches en fonction du pseudo
	* @param pseudo
	* @return Participant
	* @throws SQLException
	*/
	public static Participant getParticipant (String pseudo) throws SQLException {
		
		ResultSet rs = null;
		String sql = GET_PARTICIPANT;
		
		DbConnexion dbConnexion = new DbConnexion();
		
		Participant participant = null;
		
		try( Connection connection = dbConnexion.getConnection() ;
	    		PreparedStatement pStat = connection.prepareStatement(sql) ){
			
			pStat.setString(1, pseudo);
			
			rs = pStat.executeQuery();
			
			if( rs != null && rs.next()) {
				
				participant = Mappage.mappageParticipant(rs);
			}
		}
		
		return participant;
	}
	
	public static Participant getParticipantById (int id) throws SQLException {
		
		ResultSet rs = null;
		String sql = GET_PARTICIPANT_BY_ID;
		
		DbConnexion dbConnexion = new DbConnexion();
		
		Participant participant = null;
		
		try( Connection connection = dbConnexion.getConnection() ;
	    		PreparedStatement pStat = connection.prepareStatement(sql) ){
			
			pStat.setInt(1, id);
			
			rs = pStat.executeQuery();
			
			if( rs != null && rs.next() ) {
				
				participant = Mappage.mappageParticipant(rs);
			}
		}
		
		return participant;
	}
	



	 
	/**
	* M�thode permettant de faire des recherches en fonction d'un id de sortie
	* @param idSortie
	* @return liste de Participant
	* @throws SQLException
	*/
	public static List<Participant> getParticipants () throws SQLException {
		ResultSet rs = null;
		
		List<Participant> participants = new ArrayList<>();
		DbConnexion dbConnexion = new DbConnexion();
		
		String sql = GET_ALL_PARTICIPANT ;
		
		try ( Connection connection = dbConnexion.getConnection() ; PreparedStatement pStat = connection.prepareStatement(sql) ){
			
			rs = pStat.executeQuery();
				
			if (rs != null) {
				
				while (rs.next()) {
					Participant participant = Mappage.mappageParticipant(rs);
					participants.add(participant);
				}
				
			}
			
		}catch ( Exception exception )  {
			throw new RuntimeException( exception );
		}
		
		
		return participants;
	}
	
	public static List<Participant> getParticipantsBySortie (int idSortie)
	{
		ResultSet rs = null;
		
		String sql = GET_ALL_PARTICIPANT_BY_IDSORTIE;
		List<Participant> participants = new ArrayList<>();
		
		DbConnexion dbConnexion = new DbConnexion();
		
			try ( Connection connection = dbConnexion.getConnection() ; PreparedStatement pStat = connection.prepareStatement(sql) )
			{
				pStat.setInt(1, idSortie );
		
				rs = pStat.executeQuery();
					
				if (rs != null) {
					
					while (rs.next()) {
						Participant participant = Mappage.mappageParticipant(rs);
						participants.add(participant);
					}
				}
			}catch ( Exception exception )  {
				throw new RuntimeException( exception );
			}		
			
			return participants;
	}

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
