package bean;

// TODO: Auto-generated Javadoc
/**
 * The Class Participant.
 */
public class Participant {
	
	/** The id participant. */
	// attributs
	private int idParticipant;
	
	/** The pseudo. */
	private String pseudo;
	
	/** The nom. */
	private String nom;
	
	/** The prenom. */
	private String prenom;
	
	/** The telephone. */
	private String telephone;
	
	/** The mail. */
	private String mail;	
	
	/** The administrateur. */
	private boolean  administrateur;
	
	/** The actif. */
	private boolean actif;
	
	/** The site. */
	private Site site;
	
	/** The image. */
	private String image;
	
	
	
	/**
	 * Instantiates a new participant.
	 */
	// constructeur
	public Participant() { }

	/**
	 * Instantiates a new participant.
	 *
	 * @param pseudo the pseudo
	 * @param nom the nom
	 * @param prenom the prenom
	 * @param telephone the telephone
	 * @param mail the mail
	 * @param administrateur the administrateur
	 * @param actif the actif
	 * @param site the site
	 * @param image the image
	 */
	public Participant(String pseudo, String nom, String prenom, String telephone, String mail, boolean administrateur,
			boolean actif, Site site, String image) {
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.mail = mail;
		this.administrateur = administrateur;
		this.actif = actif;
		this.site = site;
		this.image = image;
	}
	
	/**
	 * Instantiates a new participant.
	 *
	 * @param id the id
	 * @param pseudo the pseudo
	 * @param nom the nom
	 * @param prenom the prenom
	 * @param telephone the telephone
	 * @param mail the mail
	 * @param administrateur the administrateur
	 * @param actif the actif
	 * @param site the site
	 * @param image the image
	 */
	public Participant(int id, String pseudo, String nom, String prenom, String telephone, String mail, boolean administrateur,
			boolean actif, Site site, String image) {
		this.idParticipant = id;
		this.pseudo = pseudo;
		this.nom = nom;
		this.prenom = prenom;
		this.telephone = telephone;
		this.mail = mail;
		this.administrateur = administrateur;
		this.actif = actif;
		this.site = site;
		this.image = image;
	}
	
	// Get Set Radio
	
	/**
	 * Gets the pseudo.
	 *
	 * @return the pseudo
	 */
	public String getPseudo() {
		return pseudo;
	}
	
	/**
	 * Sets the pseudo.
	 *
	 * @param pseudo the new pseudo
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
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
	 * Gets the prenom.
	 *
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}
	
	/**
	 * Sets the prenom.
	 *
	 * @param prenom the new prenom
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	/**
	 * Gets the telephone.
	 *
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}
	
	/**
	 * Sets the telephone.
	 *
	 * @param telephone the new telephone
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	/**
	 * Gets the mail.
	 *
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}
	
	/**
	 * Sets the mail.
	 *
	 * @param mail the new mail
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	/**
	 * Checks if is administrateur.
	 *
	 * @return true, if is administrateur
	 */
	public boolean isAdministrateur() {
		return administrateur;
	}
	
	/**
	 * Sets the administrateur.
	 *
	 * @param administrateur the new administrateur
	 */
	public void setAdministrateur(boolean administrateur) {
		this.administrateur = administrateur;
	}
	
	/**
	 * Checks if is actif.
	 *
	 * @return true, if is actif
	 */
	public boolean isActif() {
		return actif;
	}
	
	/**
	 * Sets the actif.
	 *
	 * @param actif the new actif
	 */
	public void setActif(boolean actif) {
		this.actif = actif;
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
	 * Gets the id participant.
	 *
	 * @return the id participant
	 */
	public int getIdParticipant() {
		return idParticipant;
	}
	

}
