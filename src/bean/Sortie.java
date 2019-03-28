package bean; 
 
import java.sql.Date; 
import java.util.List; 
 
// TODO: Auto-generated Javadoc
/**
 * The Class Sortie.
 */
public class Sortie { 
	 
	// Attribut 
	
	/** The id. */
	private int id; 
	
	/** The nom. */
	private String nom; 
	
	/** The date heure debut. */
	private Date dateHeureDebut; 
	
	/** The duree. */
	private int duree; 
	
	/** The date limite inscription. */
	private Date dateLimiteInscription; 
	
	/** The nb inscription max. */
	private int nbInscriptionMax; 
	
	/** The info sortie. */
	private String infoSortie; 
	
	/** The etat. */
	private Etats etat; 
	 
	/** The organisateur. */
	private Participant organisateur; 
	
	/** The participants. */
	private List<Participant> participants; 
	 
	/** The lieu. */
	private Lieu lieu; 
	
	/** The site. */
	private Site site; 
	 
	/** The image. */
	private String image; 
	 
	// constructeur
	
	/**
	 * Instantiates a new sortie.
	 */
	public Sortie () {}
	
	/**
	 * Instantiates a new sortie.
	 *
	 * @param nom the nom
	 * @param dateHeureDebut the date heure debut
	 * @param duree the duree
	 * @param dateLimiteInscription the date limite inscription
	 * @param nbInscriptionMax the nb inscription max
	 * @param infoSortie the info sortie
	 * @param etat the etat
	 * @param organisateur the organisateur
	 * @param participants the participants
	 * @param lieu the lieu
	 * @param site the site
	 * @param image the image
	 */
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
	
	/**
	 * Instantiates a new sortie.
	 *
	 * @param id the id
	 * @param nom the nom
	 * @param dateHeureDebut the date heure debut
	 * @param duree the duree
	 * @param dateLimiteInscription the date limite inscription
	 * @param nbInscriptionMax the nb inscription max
	 * @param infoSortie the info sortie
	 * @param etat the etat
	 * @param organisateur the organisateur
	 * @param participants the participants
	 * @param lieu the lieu
	 * @param site the site
	 * @param image the image
	 */
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
	 * Gets the date heure debut.
	 *
	 * @return the date heure debut
	 */
	public Date getDateHeureDebut() { 
		return dateHeureDebut; 
	} 
 
	/**
	 * Sets the date heure debut.
	 *
	 * @param dateHeureDebut the new date heure debut
	 */
	public void setDateHeureDebut(Date dateHeureDebut) { 
		this.dateHeureDebut = dateHeureDebut; 
	} 
 
	/**
	 * Gets the duree.
	 *
	 * @return the duree
	 */
	public int getDuree() { 
		return duree; 
	} 
 
	/**
	 * Sets the duree.
	 *
	 * @param duree the new duree
	 */
	public void setDuree(int duree) { 
		this.duree = duree; 
	} 
 
	/**
	 * Gets the date limite inscription.
	 *
	 * @return the date limite inscription
	 */
	public Date getDateLimiteInscription() { 
		return dateLimiteInscription; 
	} 
 
	/**
	 * Sets the date limite inscription.
	 *
	 * @param dateLimiteInscription the new date limite inscription
	 */
	public void setDateLimiteInscription(Date dateLimiteInscription) { 
		this.dateLimiteInscription = dateLimiteInscription; 
	} 
 
	/**
	 * Gets the nb inscription max.
	 *
	 * @return the nb inscription max
	 */
	public int getNbInscriptionMax() { 
		return nbInscriptionMax; 
	} 
 
	/**
	 * Sets the nb inscription max.
	 *
	 * @param nbInscriptionMax the new nb inscription max
	 */
	public void setNbInscriptionMax(int nbInscriptionMax) { 
		this.nbInscriptionMax = nbInscriptionMax; 
	} 
 
	/**
	 * Gets the info sortie.
	 *
	 * @return the info sortie
	 */
	public String getInfoSortie() { 
		return infoSortie; 
	} 
 
	/**
	 * Sets the info sortie.
	 *
	 * @param infoSortie the new info sortie
	 */
	public void setInfoSortie(String infoSortie) { 
		this.infoSortie = infoSortie; 
	} 
 
	/**
	 * Gets the etat.
	 *
	 * @return the etat
	 */
	public Etats getEtat() { 
		return etat; 
	} 
 
	/**
	 * Sets the etat.
	 *
	 * @param etat the new etat
	 */
	public void setEtat(Etats etat) { 
		this.etat = etat; 
	} 
 
	/**
	 * Gets the organisateur.
	 *
	 * @return the organisateur
	 */
	public Participant getOrganisateur() { 
		return organisateur; 
	} 
	 
	/**
	 * Sets the organisateur.
	 *
	 * @param organisateur the new organisateur
	 */
	public void setOrganisateur(Participant organisateur) { 
		this.organisateur = organisateur; 
	} 
 
	/**
	 * Gets the lieu.
	 *
	 * @return the lieu
	 */
	public Lieu getLieu() { 
		return lieu; 
	} 
	 
	/**
	 * Sets the lieu.
	 *
	 * @param lieu the new lieu
	 */
	public void setLieu(Lieu lieu) { 
		this.lieu = lieu; 
	} 
 
	/**
	 * Gets the image.
	 *
	 * @return the image
	 */
	public String getImage() { 
		return image; 
	} 
 
	/**
	 * Sets the image.
	 *
	 * @param image the new image
	 */
	public void setImage(String image) { 
		this.image = image; 
	} 
 
	/**
	 * Gets the participants.
	 *
	 * @return the participants
	 */
	public List<Participant> getParticipants() { 
		return participants; 
	} 
 
	/**
	 * Sets the participants.
	 *
	 * @param participants the new participants
	 */
	public void setParticipants(List<Participant> participants) { 
		this.participants = participants; 
	} 
	
	/**
	 * Gets the site.
	 *
	 * @return the site
	 */
	public Site getSite() {
		return site;
	}

	/**
	 * Sets the site.
	 *
	 * @param site the new site
	 */
	public void setSite(Site site) {
		this.site = site;
	}
	// Enum 
	 
	

	/**
	 * The Enum Etats.
	 */
	public enum Etats { 
		  
  		/** The creee. */
  		CREEE ("Créée"), 
		  
  		/** The ouverte. */
  		OUVERTE ("Ouverte"), 
		  
  		/** The cloturee. */
  		CLOTUREE ("Cloturée"), 
		  
  		/** The activite en cours. */
  		ACTIVITE_EN_COURS ("Activité en cours"), 
		  
  		/** The passee. */
  		PASSEE ("Passée"), 
		  
  		/** The annulee. */
  		ANNULEE ("Annulée");	 
		 
		/** The name. */
		private String name = ""; 
		 
		/**
		 * Instantiates a new etats.
		 *
		 * @param name the name
		 */
		Etats(String name){ 
			this.name = name; 
		} 
		
		/**
		 * Gets the name.
		 *
		 * @return the name
		 */
		public String getName() {
			return name;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Enum#toString()
		 */
		public String toString(){ 
			return name; 
		} 
	} 
	 
} 
