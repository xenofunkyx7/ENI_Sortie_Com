package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Ville;
import bll.Mappage;


/**
 * The Class DaoVille.
 */
public class DaoVille {
	// constantes de requetes sql
	
	/** The Constant ADD_VILLE. */
	private static final String ADD_VILLE = 
			"INSERT INTO VILLES "
			+ "( nom_ville, code_postal ) "
			+ "VALUES ( ?,? )";
	
	/** The Constant MODIFY_VILLE. */
	private static final String MODIFY_VILLE = 
			"UPDATE VILLES " + 
			"SET nom_ville = ?, code_postal = ? " + 
			"WHERE no_ville = ?";

	/** The Constant DELETE_VILLE. */
	private static final String DELETE_VILLE  = "DELETE FROM VILLES " + 
			"WHERE no_ville = ?";
	
	/** The Constant GET_VILLE. */
	private static final String GET_VILLE = "select * FROM VILLES "
			+ " where nom_ville like ?";
	
	
	//===================
	// Add
	//===================
		 
	/**
	 * Méthode permettant de rajouter une Ville via un objet Ville en paramétre.
	 *
	 * @param ville the ville
	 * @throws SQLException the SQL exception
	 */
	public static void addVille (Ville ville) throws SQLException {
		
		String sql = ADD_VILLE;
		
		DbConnexion dbConnexion = new DbConnexion();
		Connection connection = dbConnexion.getConnection();
		PreparedStatement pStat = connection.prepareStatement(sql);
				
			pStat.setString(1, ville.getNom() );
			pStat.setString(2, ville.getCodePostal() );
			
			pStat.executeUpdate() ;
    }

	//===================
	// Update
	//===================	
	
	/**
	 * Méthode permettant de modifier un article via un objet article en paramétre.
	 *
	 * @param ville the ville
	 * @throws SQLException the SQL exception
	 */
	public static void modifyVille(Ville ville) throws SQLException {
		String sql = MODIFY_VILLE;

		DbConnexion dbConnexion = new DbConnexion();
		Connection connection = dbConnexion.getConnection();
		PreparedStatement pStat = connection.prepareStatement(sql);
				
			pStat.setString(1, ville.getNom() );
			pStat.setString(2, ville.getCodePostal() );
			pStat.setInt(3, ville.getIdVille() );
			
			pStat.executeUpdate() ;	
	}

	
	//===================
	// Delete
	//===================
	
	/**
	 * Méthode permettant de supprimer une Ville via un objet Ville en paramétre.
	 *
	 * @param ville the ville
	 * @throws SQLException the SQL exception
	 */
	public static void deleteVille(Ville ville) throws SQLException {
		String sql = DELETE_VILLE;
		DbConnexion dbConnexion = new DbConnexion();
		Connection connection = dbConnexion.getConnection();
		PreparedStatement pStat = connection.prepareStatement(sql);
			
			pStat.setInt(1, ville.getIdVille() );
			
			pStat.executeUpdate() ;
	}
	
	
	//===================
	// Select & search
	//===================
	
	/**
	 * Méthode permettant de faire des recherches en fonction du nom rentré dans la barre de recherche.
	 *
	 * @param nom the nom
	 * @return liste de ville
	 * @throws SQLException the SQL exception
	 */
	public static List<Ville> getVilles (String nom) throws SQLException {
		
		ResultSet rs = null;
		
		List<Ville> villes = new ArrayList<>();
		
		String sql = GET_VILLE;
		
		DbConnexion dbConnexion = new DbConnexion();
		
		Connection connection = dbConnexion.getConnection();
		PreparedStatement pStat = connection.prepareStatement(sql);
				
			
			pStat.setString(1, "%"+nom+"%" );
			
			rs = pStat.executeQuery();
				
			if (rs != null) {
				
				while (rs.next()) {
					Ville ville = Mappage.mappageVille(rs);
					villes.add(ville);
				}
				
			}		
		return villes;
	}
	
}
