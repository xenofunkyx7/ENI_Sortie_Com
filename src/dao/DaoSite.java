package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Site;

public class DaoSite {

	// constantes de requetes sql
	
	private static final String ADD_SITE = 
			"INSERT INTO SITES "
			+ "( nom_site ) "
			+ "VALUES ( ? )";
	
	private static final String MODIFY_SITE = 
			"UPDATE SITES " + 
			"SET nom_site = ?" +
			"WHERE no_site = ?";
	
	private static final String DELETE_SITE  = "DELETE FROM SITES " + 
			"WHERE no_site = ?";
	
	private static final String GET_SITE = "select * FROM SITES "
			+ " where nom_site like ?";
	
	// Singkleton !
	
	 private DaoSite() {} 
	 
	 private static DaoSite INSTANCE = new DaoSite();
	 
	 public static DaoSite getInstance() {   
		 return INSTANCE;
    }
	 
	//===================
	// Add
	//===================
	 
	 /**
	  * 
	  * @param site
	  */
	public static void addSite (Site site) {
		
		String sql = ADD_SITE;
		
		DbConnexion dbConnexion = new DbConnexion();
		try ( Connection connection = dbConnexion.getConnection() ; PreparedStatement pStat = connection.prepareStatement(sql) ){
				
			pStat.setString(1, site.getNom() );
			
			pStat.executeUpdate() ;
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
		
	//===================
	// Update
	//===================
	 
	/**
	 * 
	 * @param site
	 */
	public static void modifySite(Site site) {
		String sql = MODIFY_SITE;
		
		DbConnexion dbConnexion = new DbConnexion();
		try ( Connection connection = dbConnexion.getConnection() ; PreparedStatement pStat = connection.prepareStatement(sql) ){
				
			pStat.setString(1, site.getNom() );
			pStat.setInt(2, site.getIdSite() );
			
			pStat.executeUpdate() ;
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	 
	//===================
	// Delete
	//===================
	 
	/**
	 * 
	 * @param site
	 */
	public static void deleteSite(Site site) {
		String sql = DELETE_SITE;
		DbConnexion dbConnexion = new DbConnexion();
		try ( Connection connection = dbConnexion.getConnection() ; PreparedStatement pStat = connection.prepareStatement(sql) ){
			
			pStat.setInt(1, site.getIdSite() );
			
			pStat.executeUpdate() ;
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		
	 
	//===================
	// Select & search
	//===================
		
	/**
	 * 
	 * @param nom
	 * @return
	 * @throws SQLException
	 */
	public static List<Site> getSites (String nom) throws SQLException {
		
		ResultSet rs = null;
		
		List<Site> sites = new ArrayList<>();
		
		String sql = GET_SITE;
		
		DbConnexion dbConnexion = new DbConnexion();
		try ( Connection connection = dbConnexion.getConnection() ; PreparedStatement pStat = connection.prepareStatement(sql) ){
			
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
	
		
	//===================
	// Mappage
	//===================
	
	/**
	 * map une ville avec un result set (évite la duplication de code, possiblement)
	 * @param rs
	 * @return ville
	 */
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
	
	
}
