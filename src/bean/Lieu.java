package bean; 
 
public class Lieu { 
	 
	// Attributs 
	 
	private int id; 
	private String nom; 
	private String rue; 
	 
	private Ville ville; 
	 
	private float latitude; 
	private float longitude; 
	 
	// Get Set radio 
	 
	public int getId() { 
		return id; 
	} 
	public void setId(int id) { 
		this.id = id; 
	} 
	public String getNom() { 
		return nom; 
	} 
	public void setNom(String nom) { 
		this.nom = nom; 
	} 
	public String getRue() { 
		return rue; 
	} 
	public void setRue(String rue) { 
		this.rue = rue; 
	} 
	public Ville getVille() { 
		return ville; 
	} 
	public void setVille(Ville ville) { 
		this.ville = ville; 
	} 
	public float getLatitude() { 
		return latitude; 
	} 
	public void setLatitude(float latitude) { 
		this.latitude = latitude; 
	} 
	public float getLongitude() { 
		return longitude; 
	} 
	public void setLongitude(float longitude) { 
		this.longitude = longitude; 
	} 
} 
