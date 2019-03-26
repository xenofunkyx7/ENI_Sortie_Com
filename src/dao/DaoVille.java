package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import BLL.Mappage;
import bean.Ville;

public class DaoVille {
	// constantes de requetes sql
	
	private static final String ADD_VILLE = 
			"INSERT INTO VILLES "
			+ "( nom_ville, code_postal ) "
			+ "VALUES ( ?,? )";
	
	private static final String MODIFY_VILLE = 
			"UPDATE VILLES " + 
			"SET nom_ville = ?, code_postal = ? " + 
			"WHERE no_ville = ?";

	private static final String DELETE_VILLE  = "DELETE FROM VILLES " + 
			"WHERE no_ville = ?";
	
	private static final String GET_VILLE = "select * FROM VILLES "
			+ " where nom_ville like ?";
	
	
	// Singkleton !
	
	 private DaoVille() {} 
	 
	 private static DaoVille INSTANCE = new DaoVille();
	 
	 public static DaoVille getInstance() {   
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
		
		DbConnexion dbConnexion = new DbConnexion();
		try ( Connection connection = dbConnexion.getConnection() ; PreparedStatement pStat = connection.prepareStatement(sql) ){
				
			pStat.setString(1, ville.getNom() );
			pStat.setString(2, ville.getCodePostal() );
			
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

		DbConnexion dbConnexion = new DbConnexion();
		try ( Connection connection = dbConnexion.getConnection() ; PreparedStatement pStat = connection.prepareStatement(sql) ){
				
			pStat.setString(1, ville.getNom() );
			pStat.setString(2, ville.getCodePostal() );
			pStat.setInt(3, ville.getIdVille() );
			
			pStat.executeUpdate() ;
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	
	//===================
	// Delete
	//===================
	
	/**
	 * Méthode permettant de supprimer une Ville via un objet Ville en paramétre.
	 * @param ville
	 */
	public static void deleteVille(Ville ville) {
		String sql = DELETE_VILLE;
		DbConnexion dbConnexion = new DbConnexion();
		try ( Connection connection = dbConnexion.getConnection() ; PreparedStatement pStat = connection.prepareStatement(sql) ){
			
			pStat.setInt(1, ville.getIdVille() );
			
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
		
		DbConnexion dbConnexion = new DbConnexion();
		try ( Connection connection = dbConnexion.getConnection() ; PreparedStatement pStat = connection.prepareStatement(sql) ){
			
			pStat.setString(1, "%"+nom+"%" );
			
			rs = pStat.executeQuery();
				
			if (rs != null) {
				
				while (rs.next()) {
					Ville ville = Mappage.mappageVille(rs);
					villes.add(ville);
				}
				
			}
			
		}catch ( Exception exception )  {
			throw new RuntimeException( exception );
		}
		
		return villes;
	}
	
}
