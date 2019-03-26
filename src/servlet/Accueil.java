package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Participant;
import bean.Site;
import bean.Sortie;
import bean.Sortie.Etats;
import dao.DaoInscription;
import dao.DaoProfil;
import dao.DaoSite;
import dao.DaoSortie;

/**
 * Servlet implementation class Accueil
 */
@WebServlet("/membre/accueil")
public class Accueil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Accueil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		
		/*
		List<Site> sites = new ArrayList<>();
		sites.add(new Site(11, "le mans"));
		sites.add(new Site(1, "nantes"));
		sites.add(new Site(2, "la roche sur yon"));
		sites.add(new Site(3, "Paris"));
		
		Participant perso = null;
		try {
			perso = DaoProfil.getParticipant("LeGolemDeSel");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Participant gogol = new Participant(1, "gogol", "gogol", "gogol", "02454", "15143",
				false, true, sites.get(1), "");
		
		
		List<Sortie> sorties = new ArrayList<>();
		
		List<Participant> parti = new ArrayList<Participant>();
		parti.add(perso);
		
		sorties.add(new Sortie(0, "pokemon go au bois de boulogne", new Date( new java.util.Date().getTime() )
				, 30, new Date( new java.util.Date().getTime() ), 10, "attrapez toutes les mst", 
				Etats.ACTIVITE_EN_COURS, gogol, parti, null, sites.get(3), ""));
		
		sorties.add(new Sortie(1, "pokemon go au bois de boulogne V2", new Date( new java.util.Date().getTime() )
				, 30, new Date( new java.util.Date().getTime() ), 10, "attrapez toutes les mst", 
				Etats.ACTIVITE_EN_COURS, gogol, new ArrayList<Participant>(), null, sites.get(2), ""));
		
		sorties.add(new Sortie(1, "jdr", new Date( new java.util.Date().getTime() )
				, 30, new Date( new java.util.Date().getTime() ), 10, "jdr", 
				Etats.ACTIVITE_EN_COURS, perso, new ArrayList<Participant>(), null, sites.get(2), ""));
		*/
		
		
		List<Sortie> sorties = new ArrayList<>();
		List<Site> sites = new ArrayList<>();
		try {
			sites = DaoSite.getSites("");
			//sorties = DaoSortie.getSorties("", null, null, null, null, true, true, true, true);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		session.setAttribute("sorties", sorties);
		session.setAttribute("sites", sites);
		
		
		request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String btn = request.getParameter("btnAction");
		
		switch (btn) {
		case "S'inscrire":
			inscription(request, response);
			break;
		case "Se désister":
			seDesiste(request, response);
			break;
		case "Publier":
			publier(request, response);
			break;
		default:
			request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
			break;
		}
		
		request.getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
		
	}
	
	private void inscription (HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		Participant user = (Participant) session.getAttribute("utilisateur");
		int idSortie = Integer.parseInt( request.getParameter("idSortie") );
		Date date = new Date(new java.util.Date().getTime() );
		
		DaoInscription.addInscription(date, idSortie, user.getIdParticipant());
	}
	
	private void seDesiste (HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		Participant user = (Participant) session.getAttribute("utilisateur");
		int idSortie = Integer.parseInt( request.getParameter("idSortie") );
		
		DaoInscription.deleteInscription(idSortie, user.getIdParticipant());
	}

	private void publier (HttpServletRequest request, HttpServletResponse response) {
		// changer l'attribut de la sortie
		
	}

}
