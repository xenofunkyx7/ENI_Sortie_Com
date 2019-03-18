package bean;

public class Site {

		
	private int idSite;
	private String nom;
	
	
	//Constructeurs
	public Site(String nom) {
		this.nom = nom;
	}

	public Site() {
	}
	
	
	//Ascesseur, Mutateurs
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public int getIdSite() {
		return idSite;
	}
	
	
}
