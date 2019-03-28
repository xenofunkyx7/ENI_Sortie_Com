/*
 * 
 */
package bean; 
 

/**
 * The Class Lieu.
 */
public class Lieu { 
	 
	// Attributs 
	
	/** The id. */
	private int id; 
	
	/** The nom. */
	private String nom; 
	
	/** The rue. */
	private String rue; 
	 
	/** The ville. */
	private Ville ville; 
	 
	/** The latitude. */
	private float latitude; 
	
	/** The longitude. */
	private float longitude; 
	 
	// constructeur
	
	/**
	 * Instantiates a new lieu.
	 */
	public Lieu () {}
	
	/**
	 * Instantiates a new lieu.
	 *
	 * @param nom the nom
	 * @param rue the rue
	 * @param ville the ville
	 * @param latitude the latitude
	 * @param longitude the longitude
	 */
	public Lieu (String nom, String rue, Ville ville, float latitude, float longitude) {
		this.nom = nom;
		this.rue = rue;
		this.ville = ville;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	/**
	 * Instantiates a new lieu.
	 *
	 * @param id the id
	 * @param nom the nom
	 * @param rue the rue
	 * @param ville the ville
	 * @param latitude the latitude
	 * @param longitude the longitude
	 */
	public Lieu (int id, String nom, String rue, Ville ville, float latitude, float longitude) {
		this.id = id;
		this.nom = nom;
		this.rue = rue;
		this.ville = ville;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	// Get Set radio 
	 
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public int getId() { 
		return id; 
	} 
	
	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(int id) { 
		this.id = id; 
	} 
	
	/**
	 * Gets the nom.
	 *
	 * @return the nom
	 */
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
	 * Gets the rue.
	 *
	 * @return the rue
	 */
	public String getRue() { 
		return rue; 
	} 
	
	/**
	 * Sets the rue.
	 *
	 * @param rue the new rue
	 */
	public void setRue(String rue) { 
		this.rue = rue; 
	} 
	
	/**
	 * Gets the ville.
	 *
	 * @return the ville
	 */
	public Ville getVille() { 
		return ville; 
	} 
	
	/**
	 * Sets the ville.
	 *
	 * @param ville the new ville
	 */
	public void setVille(Ville ville) { 
		this.ville = ville; 
	} 
	
	/**
	 * Gets the latitude.
	 *
	 * @return the latitude
	 */
	public float getLatitude() { 
		return latitude; 
	} 
	
	/**
	 * Sets the latitude.
	 *
	 * @param latitude the new latitude
	 */
	public void setLatitude(float latitude) { 
		this.latitude = latitude; 
	} 
	
	/**
	 * Gets the longitude.
	 *
	 * @return the longitude
	 */
	public float getLongitude() { 
		return longitude; 
	} 
	
	/**
	 * Sets the longitude.
	 *
	 * @param longitude the new longitude
	 */
	public void setLongitude(float longitude) { 
		this.longitude = longitude; 
	} 
} 
