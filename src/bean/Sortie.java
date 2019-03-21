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
	 
	// constructeur
	
	public Sortie () {}
	
	public Sortie (String nom, Date dateHeureDebut, int duree, Date dateLimiteInscription
					, int nbInscriptionMax, String infoSortie, Etats etat,
					Participant organisateur, List<Participant> participants, Lieu lieu,
					Site site, String image) {
		this.nom = nom; 
		this.dateHeureDebut = dateHeureDebut; 
		this.duree = duree; 
		this.dateLimiteInscription = dateLimiteInscription; 
		this.nbInscriptionMax = nbInscriptionMax; 
		this.infoSortie = infoSortie; 
		this.etat = etat; 
		this.organisateur = organisateur; 
		this.participants = participants; 
		this.lieu = lieu; 
		this.site = site; 
		this.image = image; 
	}
	
	public Sortie (int id, String nom, Date dateHeureDebut, int duree, Date dateLimiteInscription
				, int nbInscriptionMax, String infoSortie, Etats etat,
				Participant organisateur, List<Participant> participants, Lieu lieu,
				Site site, String image) {
		this.id = id;
		this.nom = nom; 
		this.dateHeureDebut = dateHeureDebut; 
		this.duree = duree; 
		this.dateLimiteInscription = dateLimiteInscription; 
		this.nbInscriptionMax = nbInscriptionMax; 
		this.infoSortie = infoSortie; 
		this.etat = etat; 
		this.organisateur = organisateur; 
		this.participants = participants; 
		this.lieu = lieu; 
		this.site = site; 
		this.image = image; 
	}
	
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
		  CREEE ("Créée"), 
		  OUVERTE ("Ouverte"), 
		  CLOTUREE ("Cloturée"), 
		  ACTIVITE_EN_COURS ("Activité en cours"), 
		  PASSEE ("Passée"), 
		  ANNULEE ("Annulée");	 
		 
		private String name = ""; 
		 
		Etats(String name){ 
			this.name = name; 
		} 
		
		public String getName() {
			return name;
		}
		
		public String toString(){ 
			return name; 
		} 
	} 
	 
} 
