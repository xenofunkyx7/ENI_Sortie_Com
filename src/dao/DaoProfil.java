package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Participant;
import bean.Site;
import bean.Sortie;

public class DaoProfil {
	
	// false pour l'admin, on ne peut pas créer un membre qui soit directement admin)
//	 private static final String ADD_PARTICIPANT = "INSERT INTO participants VALUES (?,?, ?,?,?, ?,'false',?,?)";
	 
	 private static final String MODIFY_PARTICIPANT =  "UPDATE PARTICIPANTS "			 
	 		+ "SET pseudo = ?, nom = ?, prenom = ?, telephone = ?, mail = ?,  sites_no_site = ? " 
			+ "WHERE no_participant = ?";
	 
	 private static final String MODIFY_PARTICIPANTMDP = "UPDATE participants SET mot_de_passe=? WHERE no_participant =?";
	 
//	 private static final String DELETE_PARTICIPANT = "DELETE FROM participants WHERE id=? ";
	 
	 private static final String GET_PARTICIPANT = "SELECT * FROM PARTICIPANTS"
				+ "WHERE pseudo=? "; 
	 
	 private static final String GET_ALL_PARTICIPANT = "SELECT * FROM participants" ;
	
	
	// Singkleton !
	
	 private DaoProfil() {} 
	 
	 private static DaoProfil INSTANCE = new DaoProfil();
	 

	 
	 public static DaoProfil getInstance() {   
		 return INSTANCE; 
     }
	 
	 
	 
		 
	 /*
     * Méthode permettant de modifier un article via un objet article en paramétre.
     * @param article
     */
    public static void modifyParticipant(Participant participant) {

        String sql = MODIFY_PARTICIPANT;

        try ( Connection connection = DbConnexion.getConnection() ;
    		PreparedStatement pStat = connection.prepareStatement(sql) ){


            pStat.setString(1, participant.getPseudo() );
            pStat.setString(2, participant.getNom() );
            pStat.setString(3, participant.getPrenom() );
            pStat.setString(4, participant.getTelephone() );
            pStat.setString(5, participant.getMail() );            
//            pStat.setBoolean(X, participant.isActif() );
            pStat.setString(6, participant.getSite().getNom() );
            pStat.setInt(7, participant.getIdParticipant() );
//            pStat.setString(parameterIndex, image);

            pStat.executeUpdate() ;

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    //Modification du mdp
    public static void  modifyParticipantMDP( String mdp, int no_partcipant) {
    	
    	String sql = MODIFY_PARTICIPANTMDP; 
    	
    	try ( Connection connection = DbConnexion.getConnection() ;
        		PreparedStatement pStat = connection.prepareStatement(sql)){
        		
    		pStat.setString(1, mdp);
    		pStat.setInt(2, no_partcipant);
    		
    		pStat.executeUpdate();
    		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
		
		Participant participant = null;
		
		try( Connection connection = DbConnexion.getConnection() ;
	    		PreparedStatement pStat = connection.prepareStatement(sql) ){
			
			pStat.setString(1, pseudo);
			
			rs = pStat.executeQuery();
			
			
			
			if( rs != null) {
				
				participant = mappageParticipant(rs);
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
	
	public static List<Participant> getParticipants (String nom, String prenom, String pseudo) throws SQLException {
		ResultSet rs = null;
		
		List<Participant> participants = new ArrayList<>();
		
		String sql = GET_ALL_PARTICIPANT ;
		
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
