package bean;

// TODO: Auto-generated Javadoc
/**
 * The Class Ville.
 */
public class Ville {
	
	/** The id ville. */
	//attributs
	private int idVille;
	
	/** The nom. */
	private String nom;
	
	/** The code postal. */
	private String codePostal;
	
	
	
	/**
	 * Instantiates a new ville.
	 */
	//Constructeurs
	public Ville() {
 
	}

	/**
	 * Instantiates a new ville.
	 *
	 * @param nom the nom
	 * @param codePostal the code postal
	 */
	public Ville(String nom, String codePostal) {

		this.nom = nom;
		this.codePostal = codePostal;
	}

	/**
	 * Instantiates a new ville.
	 *
	 * @param idVille the id ville
	 * @param nom the nom
	 * @param codePostal the code postal
	 */
	public Ville(int idVille, String nom, String codePostal) {

		this.idVille = idVille;
		this.nom = nom;
		this.codePostal = codePostal;
	}
	
	/**
	 * Gets the nom.
	 *
	 * @return the nom
	 */
	//ascesseur, mutateurs
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
	 * Gets the code postal.
	 *
	 * @return the code postal
	 */
	public String getCodePostal() {
		return codePostal;
	}
	
	/**
	 * Sets the code postal.
	 *
	 * @param codePostal the new code postal
	 */
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	
	/**
	 * Gets the id ville.
	 *
	 * @return the id ville
	 */
	public int getIdVille() {
		return idVille;
	}

}
