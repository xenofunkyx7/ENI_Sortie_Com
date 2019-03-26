package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class Sortie
 */
@WebServlet("/membre/Sortie")
public class CreerSortie extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//validation git

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Ville> villes = null;
		List<Lieu> lieux = null;
		
		
		// Recupération de tout les lieux et Villes
		try {
			villes = DaoVille.getVilles("");
		} catch (SQLException e) {
			//TODO
			e.printStackTrace();
		}
		try {
			lieux = DaoLieu.getLieux("");
		} catch (SQLException e) {
			// TODO 
			e.printStackTrace();
		}
		
		
		request.getServletContext().setAttribute("villes",villes );
		request.getServletContext().setAttribute("lieux", lieux);
		
		//TODO CHECK if utilisateur = createur de la 
		request.getRequestDispatcher("/WEB-INF/sortie.jsp").forward(request, response); 
	}
 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		HttpSession session = request.getSession();
		
		Sortie newSortie = new Sortie();
		Lieu lieuChoisi = new Lieu();
		Participant user = null;
		
		List<Ville> villes = null;
		List<Lieu> lieux = null;
		int duree = 0;
		int nbInscriptionMax = 0;
		Date dateLimiteInscription = null;
		Date dateHeureDebut = null;
		
		
		
		//Récupération des données du formulaire / session sauf dates et Int
		String nom = request.getParameter("nom");
		String infoSortie = request.getParameter("infoSortie");
		int idVille = Integer.parseInt(request.getParameter("idVille"));
		int idLieu = Integer.parseInt(request.getParameter("idLieu"));
		user = (Participant)session.getAttribute("utilisateur");
		String etat = request.getParameter("etat");
			
		
		
		//date du jour instant T pour vérification
		System.out.println("BUG 01" + request.getParameter("dateLimiteInscription"));

		Date dateEtHeureActuel = new Date(new java.util.Date().getTime());
	
		
		
		/**
		 * Parse + vérification de validité des dates saisies
		 */
        try {
        	dateLimiteInscription = new Date (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(request.getParameter("dateLimiteInscription")).getTime() );
        } catch (Exception e) {
			// TODO: renvoyer erreur date invalide
        	System.out.println("DATE 1 ");
		}
        try {
        	dateHeureDebut = new Date (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm").parse(request.getParameter("dateHeureDebut")).getTime() );
        } catch (Exception e) {
			// TODO: renvoyer erreur date invalide
        	System.out.println("DATE 2 ");
		}
        
	    /**
	     * SI les date sont valide alors check si date debut est suppérieur à la date fin inscription
	     */
        if (dateHeureDebut != null && dateLimiteInscription != null)
        {
        	System.out.println("DATE 3 ");
        	if (dateHeureDebut.before(dateLimiteInscription))
        	{
        		// TODO : erreur date debut avant la date limite d'inscription
        		System.out.println("DATE 4 ");
        	}
        }
        //TODO VERIF si date debut < date jour = bugg
        /**
         * Vérifie si le nom est correct
         */
        if (nom.length() < 0 || nom.length() >50 || nom == "" || nom == null)
        {
        	// TODO : erreur nom invalide
        }
        if (infoSortie.length() < 0 || infoSortie.length() >500 || infoSortie == "" || infoSortie == null)
        {
        	// TODO : erreur infoSortie invalide
        }
        
        
        /**
         * Vérification si duree est un chiffre correct
         */
        try {
        	duree = Integer.parseInt(request.getParameter("duree"));
        } catch (Exception e) {
			// TODO: Durée invalide
		}
        if (duree < 10 ) 
        {
        	//TODO : durée doit etre suppérieur à 10
        }
        
        /**
         * Vérification si nombre max participant est un chiffre correct
         */
        try {
        	nbInscriptionMax = Integer.parseInt(request.getParameter("nbInscriptionMax"));
        } catch (Exception e) {
			// TODO: nb Inscription Max invalide
		}
        if (nbInscriptionMax < 10 ) 
        {
        	//TODO : nbInscriptionMax doit etre suppérieur à 10
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
        
        System.out.println("Avant hydratation ");
        /**
         * Hydratation de l'objet a envoyer à la dao addSortie
         */
        Participant organisateur = (Participant) session.getAttribute("utilisateur");
        		
        Sortie sortie = new Sortie(nom, dateHeureDebut, duree, dateLimiteInscription, nbInscriptionMax, 
        		infoSortie, etat.equals("publier")? Etats.OUVERTE : Etats.CREEE, organisateur, new ArrayList<Participant>(), lieuChoisi, user.getSite(), "" );
	
		
		/**
		 * Envoie de la sortie en base de donnée
		 */
		int resultat = DaoSortie.addSortie(sortie, user.getIdParticipant());
		if (resultat == -1)
		{
			//TODO : echec de l'envoie en BDD
		}
		System.out.println("FIN");
		
		request.getRequestDispatcher("/membre/detailSortie?id=" + resultat );
		
		
	
		
	}

}
