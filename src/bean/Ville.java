package bean;

public class Ville {
	
	//attributs
	private int idVille;
	private String nom;
	private String codePostal;
	
	
	
	//Constructeurs
	public Ville() {
 
	}

	public Ville(String nom, String codePostal) {

		this.nom = nom;
		this.codePostal = codePostal;
	}

	public Ville(int idVille, String nom, String codePostal) {

		this.idVille = idVille;
		this.nom = nom;
		this.codePostal = codePostal;
	}
	
	//ascesseur, mutateurs
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public int getIdVille() {
		return idVille;
	}

}
