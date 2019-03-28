package bean;

// TODO: Auto-generated Javadoc
/**
 * The Class Site.
 */
public class Site {

		
	/** The id site. */
	private int idSite;
	
	/** The nom. */
	private String nom;
	
	
	//Constructeurs
	
	/**
	 * Instantiates a new site.
	 */
	public Site() {}
	
	/**
	 * Instantiates a new site.
	 *
	 * @param nom the nom
	 */
	public Site(String nom) {
		this.nom = nom;
	}
	
	/**
	 * Instantiates a new site.
	 *
	 * @param idSite the id site
	 * @param nom the nom
	 */
	public Site(int idSite, String nom) {
		this.idSite = idSite;
		this.nom = nom;
	}
	
	/**
	 * Gets the nom.
	 *
	 * @return the nom
	 */
	//Ascesseur, Mutateurs
	public String getNom() {
		return nom;
	}
	
	/**
	 * Sets the nom.
	 *
	 * @param nom the new nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * Gets the id site.
	 *
	 * @return the id site
	 */
	public int getIdSite() {
		return idSite;
	}
	
	
}
