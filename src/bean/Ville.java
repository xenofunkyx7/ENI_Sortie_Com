package bean;

public class Ville {
	
	//attributs
	private int idVille;
	private String nom;
	private int codePostal;
	
	
	
	//Constructeurs
	public Ville() {

	}

	public Ville(String nom, int codePostal) {

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
	public int getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}
	public int getIdVille() {
		return idVille;
	}

}
