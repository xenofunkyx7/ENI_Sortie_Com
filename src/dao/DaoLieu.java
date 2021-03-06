package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Lieu;
import bll.Mappage;


/**
 * The Class DaoLieu.
 */
public class DaoLieu {
	
	// constantes de requetes sql
	
	/** The Constant ADD_LIEU. */
	private static final String ADD_LIEU = 
			"INSERT INTO LIEUX "
			+ "( nom_lieu, rue, latitude, longitude, villes_no_ville ) "
			+ "VALUES ( ?,? , ?,?,? )";
	
	/** The Constant MODIFY_LIEU. */
	private static final String MODIFY_LIEU = 
			"UPDATE LIEUX " + 
			"SET nom_lieu = ?, rue = ?, latitude = ?, " +
			"longitude = ?, villes_no_ville = ? " + 
			"WHERE no_lieu = ?";
	
	/** The Constant DELETE_LIEU. */
	private static final String DELETE_LIEU = "DELETE FROM LIEUX " + 
			"WHERE no_lieu = ?";
	
	/** The Constant GET_LIEU. */
	private static final String GET_LIEU = "select * FROM LIEUX"
			+ " inner join VILLES on villes_no_ville = no_ville "
			+ " where nom_ville like ?";
	
	// Singkleton !
	
	 /**
	 * Instantiates a new dao lieu.
	 */
	private DaoLieu() {} 
	 
	 /** The instance. */
 	private static DaoLieu INSTANCE = new DaoLieu();
	 
	 /**
 	 * Gets the single instance of DaoLieu.
 	 *
 	 * @return single instance of DaoLieu
 	 */
 	public static DaoLieu getInstance() {   
		 return INSTANCE;
    }

	//===================
	// Add
	//===================
	 
	/**
	 * Méthode permettant de rajouter un Lieu via un objet Lieu en paramétre.
	 *
	 * @param lieu the lieu
	 * @throws SQLException the SQL exception
	 */
	public static void addLieu (Lieu lieu) throws SQLException {
		
		String sql = ADD_LIEU;
		
		DbConnexion dbConnexion = new DbConnexion();
		
		Connection connection = dbConnexion.getConnection() ;
		PreparedStatement pStat = connection.prepareStatement(sql);
				
			pStat.setString(1, lieu.getNom() );
			pStat.setString(2, lieu.getRue() );
			pStat.setFloat(3, lieu.getLatitude() );
			pStat.setFloat(4, lieu.getLongitude() );
			pStat.setInt(5, lieu.getVille().getIdVille() );
			
			pStat.executeUpdate() ;

	}
	 
	//===================
	// Update
	//===================
	
	/**
	 *  
	 * Méthode permettant de modifier un article via un objet article en paramétre. 
	 *
	 * @param lieu the lieu
	 * @throws SQLException the SQL exception
	 */ 
	public static void modifyLieu(Lieu lieu) throws SQLException { 
		 
		String sql = MODIFY_LIEU;
		 
		DbConnexion dbConnexion = new DbConnexion();
		
		Connection connection = dbConnexion.getConnection() ;
		PreparedStatement pStat = connection.prepareStatement(sql); 
			 
			pStat.setString(1, lieu.getNom() ); 
			pStat.setString(2, lieu.getRue() ); 
			pStat.setFloat(3, lieu.getLatitude() ); 
			pStat.setFloat(4, lieu.getLongitude() ); 
			pStat.setInt(5, lieu.getVille().getIdVille() ); 
			pStat.setInt(6, lieu.getId() ); 
			 
			pStat.executeUpdate() ; 
	} 
	 
	//===================
	// Delete
	//===================
	
	/**
	 * Méthode permettant de supprimer un Lieu via un objet Lieu en paramétre.
	 *
	 * @param lieu the lieu
	 * @throws SQLException the SQL exception
	 */
	public static void deleteLieu(Lieu lieu) throws SQLException {
		String sql = DELETE_LIEU;
		DbConnexion dbConnexion = new DbConnexion();
		
		Connection connection = dbConnexion.getConnection() ;
		PreparedStatement pStat = connection.prepareStatement(sql);
			
			pStat.setInt(1, lieu.getId() );
			
			pStat.executeUpdate() ;		
	}
	 
	//===================
	// Select & search
	//===================
	
	/**
	 * Méthode permettant de faire des recherches en fonction du nom rentré dans la barre de recherche.
	 *
	 * @param nom the nom
	 * @return liste de lieu
	 * @throws SQLException the SQL exception
	 */
	public static List<Lieu> getLieux (String nom) throws SQLException {
		
		ResultSet rs = null;
		
		List<Lieu> lieux = new ArrayList<>();
		
		String sql = GET_LIEU;
		
		DbConnexion dbConnexion = new DbConnexion();
		
		Connection connection = dbConnexion.getConnection() ;
		PreparedStatement pStat = connection.prepareStatement(sql);
			
			pStat.setString(1, "%"+nom+"%" );
			
			rs = pStat.executeQuery();
				
			if (rs != null) {
				
				while (rs.next()) {
					Lieu lieu = Mappage.mappageLieu(rs);
					lieux.add(lieu);
				}
			}		
		return lieux;
	}
	
	/**
	 * Gets the lieu.
	 *
	 * @param id the id
	 * @return the lieu
	 * @throws SQLException the SQL exception
	 */
	public static Lieu getlieu( int id ) throws SQLException
	{
		ResultSet rs = null;
		
		String sql = GET_LIEU;
		Lieu lieuChoisis = null;
		
		DbConnexion dbConnexion = new DbConnexion();
		
		Connection connection = dbConnexion.getConnection();
		PreparedStatement pStat = connection.prepareStatement(sql);
					
			pStat.setString(1, "" );
					
			rs = pStat.executeQuery();
						
			if (rs != null && rs.next() ) 
			{		
				lieuChoisis = Mappage.mappageLieu(rs);
			}
		return lieuChoisis;
	}
	
}
