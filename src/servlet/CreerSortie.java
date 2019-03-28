package servlet;

import java.io.IOException;
import java.sql.SQLException;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Lieu;
import bean.Participant;
import bean.Sortie;
import bean.Ville;
import bean.Sortie.Etats;
import dao.DaoLieu;
import dao.DaoSortie;
import dao.DaoVille;



/**
 * Servlet implementation class Sortie.
 */
@WebServlet("/membre/Sortie")
public class CreerSortie extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	//validation git

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		System.out.println("Créer sortie");
		HttpSession session = request.getSession();
		session.removeAttribute("sortie");
		
		List<Ville> villes = null;
		List<Lieu> lieux = null;	
		
		// Recupération de tout les lieux et Villes
		try {
			villes = DaoVille.getVilles("");
		} catch (SQLException e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("/erreur").forward(request, response);
		}
		
		try {
			lieux = DaoLieu.getLieux("");
		} catch (SQLException e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("/erreur").forward(request, response);
		}

		request.getServletContext().setAttribute("villes",villes );
		request.getServletContext().setAttribute("lieux", lieux);
		
		request.getRequestDispatcher("/WEB-INF/sortie.jsp").forward(request, response); 
	}
 
	/**
	 * Do post.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		//Création d'une hashmap de message d'erreur Si le formulaire préente des erreurs
		Map<String, String> erreurs = new HashMap<String, String>();

				
		HttpSession session = request.getSession();
		
		Lieu lieuChoisi = new Lieu();
		Participant user = null;

		List<Lieu> lieux = null;
		int duree = 0;
		int nbInscriptionMax = 0;
		Date dateLimiteInscription = null;
		Date dateHeureDebut = null;		
		
		//Récupération des données du formulaire / session sauf dates et Int
		String nom = request.getParameter("nom");
		String infoSortie = request.getParameter("infoSortie");
		int idLieu = Integer.parseInt(request.getParameter("idLieu"));
		user = (Participant)session.getAttribute("utilisateur");
		String etat = request.getParameter("etat");
		
		
		//date du jour instant T pour vérification
		Date dateEtHeureActuel = new Date(new java.util.Date().getTime());
		
		
		/**
		 * Parse + vérification de validité des dates saisies
		 */
        try {
        	dateLimiteInscription = new Date (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(request.getParameter("dateLimiteInscription")).getTime() );
        } catch (Exception e) {
        	erreurs.put( "dateLimiteInscriptionErreur", "La Date limite d'inscription est invalide" );
		}
        try {
        	dateHeureDebut = new Date (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(request.getParameter("dateHeureDebut")).getTime() );
        } catch (Exception e) {
        	erreurs.put( "dateHeureDebutErreur", "La Date/heure de début est invalide" );
		}
        
	    /**
	     * SI les date sont valide alors check 
	     * si date debut est suppérieur à la date fin inscription
	     */
        if (dateHeureDebut != null && dateLimiteInscription != null)
        {
        	if (dateHeureDebut.compareTo(dateLimiteInscription) < 0)
        	{
        		erreurs.put( "dateErreur1", "Veuillez inscrire des dates valides" );
        	}
        	if (dateHeureDebut.compareTo(dateEtHeureActuel) < 0  || dateLimiteInscription.compareTo(dateEtHeureActuel) < 0 )
        	{
        		erreurs.put( "dateErreur2", "Veuillez choisir des dates supérieur à la date du jour" );
        	}
        }
        
        /**
         * Vérifie si le nom est correct
         */
        if (nom.length() < 0 || nom.length() >50 || nom == "" || nom == null)
        {
        	erreurs.put( "nomErreur", "Nom invalide" );
        }
        if (infoSortie.length() < 0 || infoSortie.length() >500 || infoSortie == "" || infoSortie == null)
        {
        	erreurs.put( "infoSortieErreur", "Les informations supplémentaire de sortie doivent etre d'une longueur comprise entre 0 et 500 charactere" );
        }
        
        
        /**
         * Vérification si duree est un chiffre correct
         */
        try {
        	duree = Integer.parseInt(request.getParameter("duree"));
        	if (duree < 10 ) 
            {
            	erreurs.put( "dureeErreur2", "La durée doit etre d'une durée de 10 minutes minimum" );
            }
        } catch (Exception e) {
        	erreurs.put( "dureeErreur", "Veuillez inscrire une durée valide (en minute)" );
		}
        
        /**
         * Vérification si nombre max participant est un chiffre correct
         */
        try {
        	nbInscriptionMax = Integer.parseInt(request.getParameter("nbInscriptionMax"));
        	if (nbInscriptionMax < 2 ) 
            {
            	erreurs.put( "nbInscriptionMaxErreur2", "Le nombre d'inscription max doit etre d'un minimum de 2 " );
            }
        } catch (Exception e) {
        	erreurs.put( "nbInscriptionMaxErreur", "Veuillez inscrire un nombre valide" );
		}
        
        
        /**
         * hydratation Objet lieu
         */
        lieux = (List<Lieu>)request.getServletContext().getAttribute("lieux");
        for ( Lieu lieuTemp : lieux)
        {
        	if (lieuTemp.getId() == idLieu )
        	{
        		lieuChoisi = lieuTemp;
        	}
        }
        
        
        /**
         * Hydratation de l'objet a envoyer à la dao addSortie Si aucune erreur
         */
        if (erreurs.isEmpty() ) {
        	Participant organisateur = (Participant) session.getAttribute("utilisateur");
        
        	Sortie sortie = new Sortie(nom, dateHeureDebut, duree, dateLimiteInscription,nbInscriptionMax,
        								infoSortie, etat.equals("publier")?Etats.OUVERTE:Etats.CREEE,organisateur,
	        								new ArrayList<Participant>(), lieuChoisi,user.getSite(),"");
	        /**
			 * Envoie de la sortie en base de donnée
			 */
			int resultat = 0;
			try {
				resultat = DaoSortie.addSortie(sortie, user.getIdParticipant());
			} catch (SQLException e) {
				request.setAttribute("exception", e);
				request.getRequestDispatcher("/erreur").forward(request, response);
			}
			
			if (resultat == -1)
			{
				erreurs.put( "dao", "Echec de l'envoie en base de donnée" );
				request.setAttribute("erreurs", erreurs);
				request.getRequestDispatcher("/WEB-INF/sortie.jsp").forward(request, response); 
			}
			else
			{
				response.sendRedirect("/ENI_Sortie_Com/membre/detailSortie?id=" + resultat );
			}
			
        }
        else
        {
        	request.setAttribute("erreurs", erreurs);
			request.getRequestDispatcher("/WEB-INF/sortie.jsp").forward(request, response);
        }
		
	}

}
