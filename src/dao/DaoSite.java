package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Site;
import bll.Mappage;

// TODO: Auto-generated Javadoc
/**
 * The Class DaoSite.
 */
public class DaoSite {

	// constantes de requetes sql
	
	/** The Constant ADD_SITE. */
	private static final String ADD_SITE = 
			"INSERT INTO SITES "
			+ "( nom_site ) "
			+ " VALUES ( ? )";
	
	/** The Constant MODIFY_SITE. */
	private static final String MODIFY_SITE = 
			" UPDATE SITES " + 
			" SET nom_site = ? " +
			" WHERE no_site = ? ";
	
	/** The Constant DELETE_SITE. */
	private static final String DELETE_SITE  = "DELETE FROM SITES " + 
			"WHERE no_site = ?";
	
	/** The Constant GET_SITE. */
	private static final String GET_SITE = "select * FROM SITES "
			+ " where nom_site like ?";
	
	// Singkleton !
	
	 /**
	 * Instantiates a new dao site.
	 */
	private DaoSite() {} 
	 
	 /** The instance. */
 	private static DaoSite INSTANCE = new DaoSite();
	 
	 /**
 	 * Gets the single instance of DaoSite.
 	 *
 	 * @return single instance of DaoSite
 	 */
 	public static DaoSite getInstance() {   
		 return INSTANCE;
    }
	 
	//===================
	// Add
	//===================
	 
	 /**
	 * Adds the site.
	 *
	 * @param site the site
	 * @throws SQLException the SQL exception
	 */
	public static void addSite (Site site) throws SQLException {
		
		String sql = ADD_SITE;
		
		DbConnexion dbConnexion = new DbConnexion();
		
		Connection connection = dbConnexion.getConnection() ;
		PreparedStatement pStat = connection.prepareStatement(sql);
				
			pStat.setString(1, site.getNom() );
			
			pStat.executeUpdate() ;	
	}
	
		
	//===================
	// Update
	//===================
	 
	/**
	 * Modify site.
	 *
	 * @param site the site
	 * @throws SQLException the SQL exception
	 */
	public static void modifySite(Site site) throws SQLException {
		String sql = MODIFY_SITE;
		
		DbConnexion dbConnexion = new DbConnexion();
		
		Connection connection = dbConnexion.getConnection();
		PreparedStatement pStat = connection.prepareStatement(sql);
				
			pStat.setString(1, site.getNom() );
			pStat.setInt(2, site.getIdSite() );
			
			pStat.executeUpdate();
		
	}
	
	 
	//===================
	// Delete
	//===================
	 
	/**
	 * Delete site.
	 *
	 * @param site the site
	 * @throws SQLException the SQL exception
	 */
	public static void deleteSite(Site site) throws SQLException {
		String sql = DELETE_SITE;
		DbConnexion dbConnexion = new DbConnexion();
		Connection connection = dbConnexion.getConnection();
		PreparedStatement pStat = connection.prepareStatement(sql);
			
			pStat.setInt(1, site.getIdSite() );
			
			pStat.executeUpdate() ;

	}
		
	 
	//===================
	// Select & search
	//===================
		
	/**
	 * Gets the sites.
	 *
	 * @param nom the nom
	 * @return the sites
	 * @throws SQLException the SQL exception
	 */
	public static List<Site> getSites (String nom) throws SQLException {
		
		ResultSet rs = null;
		
		List<Site> sites = new ArrayList<>();
		
		String sql = GET_SITE;
		
		DbConnexion dbConnexion = new DbConnexion();
		
		Connection connection = dbConnexion.getConnection();
		PreparedStatement pStat = connection.prepareStatement(sql);
			
			pStat.setString(1, "%"+nom+"%" );
			
			rs = pStat.executeQuery();
				
			if (rs != null) {
				
				while (rs.next()) {
					Site site = Mappage.mappageSite(rs);
					sites.add(site);
				}
				
			}

		return sites;
	}
}
