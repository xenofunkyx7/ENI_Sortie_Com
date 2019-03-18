package bean; 
 
import java.sql.Date; 
import java.util.List; 
 
public class Sortie { 
	 
	// Attribut 
	
	private int id; 
	private String nom; 
	private Date dateHeureDebut; 
	private int duree; 
	private Date dateLimiteInscription; 
	private int nbInscriptionMax; 
	private String infoSortie; 
	private Etats etat; 
	 
	private Participant organisateur; 
	private List<Participant> participants; 
	 
	private Lieu lieu; 
	private Site site; 
	 
	private String image; 
	 
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
 
	public Date getDateHeureDebut() { 
		return dateHeureDebut; 
	} 
 
	public void setDateHeureDebut(Date dateHeureDebut) { 
		this.dateHeureDebut = dateHeureDebut; 
	} 
 
	public int getDuree() { 
		return duree; 
	} 
 
	public void setDuree(int duree) { 
		this.duree = duree; 
	} 
 
	public Date getDateLimiteInscription() { 
		return dateLimiteInscription; 
	} 
 
	public void setDateLimiteInscription(Date dateLimiteInscription) { 
		this.dateLimiteInscription = dateLimiteInscription; 
	} 
 
	public int getNbInscriptionMax() { 
		return nbInscriptionMax; 
	} 
 
	public void setNbInscriptionMax(int nbInscriptionMax) { 
		this.nbInscriptionMax = nbInscriptionMax; 
	} 
 
	public String getInfoSortie() { 
		return infoSortie; 
	} 
 
	public void setInfoSortie(String infoSortie) { 
		this.infoSortie = infoSortie; 
	} 
 
	public Etats getEtat() { 
		return etat; 
	} 
 
	public void setEtat(Etats etat) { 
		this.etat = etat; 
	} 
 
	public Participant getOrganisateur() { 
		return organisateur; 
	} 
	 
	public void setOrganisateur(Participant organisateur) { 
		this.organisateur = organisateur; 
	} 
 
	public Lieu getLieu() { 
		return lieu; 
	} 
	 
	public void setLieu(Lieu lieu) { 
		this.lieu = lieu; 
	} 
 
	public String getImage() { 
		return image; 
	} 
 
	public void setImage(String image) { 
		this.image = image; 
	} 
 
	public List<Participant> getParticipants() { 
		return participants; 
	} 
 
	public void setParticipants(List<Participant> participants) { 
		this.participants = participants; 
	} 
	
	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}
	// Enum 
	 
	

	public enum Etats { 
		  Creee ("Créée"), 
		  Ouverte ("Ouverte"), 
		  Cloturee ("Cloturée"), 
		  ActiviteEnCours ("Activité en cours"), 
		  Passee ("Passée"), 
		  Annulee ("Annulée");	 
		 
		private String name = ""; 
		 
		Etats(String name){ 
			this.name = name; 
		} 
			    
		public String toString(){ 
			return name; 
		} 
	} 
	 
} 
