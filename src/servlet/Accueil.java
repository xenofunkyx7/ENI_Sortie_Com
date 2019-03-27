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
    
    private String erreurs = "" ;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		
		List<Sortie> sorties = new ArrayList<>();
		List<Site> sites = new ArrayList<>();
		try {
			sites = DaoSite.getSites("");
			sorties = DaoSortie.getSorties();
			
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
		
		erreurs = "" ;
		String btn = request.getParameter("btnAction");
		
		switch (btn) {
		case "S'inscrire":
			inscription(request, response);
			
			break;
		case "Se désister":
			seDesiste(request, response);
			if (erreurs.length() > 0) {request.setAttribute("erreurs", erreurs);}
			doGet(request, response);
			break;
		case "Publier":
			publier(request, response);
			if (erreurs.length() > 0) {request.setAttribute("erreurs", erreurs);}
			doGet(request, response);
			break;
		default:
			doGet(request, response);
			break;
		}
		
		
		
		doGet(request, response);
		
	}
	
	private void inscription (HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(true);
		Participant user = (Participant) session.getAttribute("utilisateur");
		int idSortie = Integer.parseInt( request.getParameter("idSortie") );
		Date date = new Date(new java.util.Date().getTime() );
		
		Sortie sortie = null;
		List<Participant> participants = null;
		try {
			sortie = DaoSortie.getSortie(idSortie);
			participants = DaoProfil.getParticipantsBySortie(idSortie);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String type = request.getParameter("type");
		
		if (type.equals("Ouverte") && sortie != null && participants != null && participants.size() < sortie.getNbInscriptionMax() ) {
			DaoInscription.addInscription(date, idSortie, user.getIdParticipant());
		}else {
			erreurs += "Impossible de s'inscrire: la sortie séléctionnée ne semble pas ouverte... ";
		}
		if (erreurs.length() > 0) {request.setAttribute("erreurs", erreurs);}
		response.sendRedirect("detailSortie");
		
	}
	
	private void seDesiste (HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		Participant user = (Participant) session.getAttribute("utilisateur");
		int idSortie = Integer.parseInt( request.getParameter("idSortie") );
		
		String type = request.getParameter("type");
		
		if (type.equals("Ouverte")  ) {
			DaoInscription.deleteInscription(idSortie, user.getIdParticipant());
		}else {
			erreurs += "Impossible de désister: la sortie séléctionnée ne semble pas ouverte... ";
		}
		
	}

	private void publier (HttpServletRequest request, HttpServletResponse response) {
		int idSortie = Integer.parseInt( request.getParameter("idSortie") );
		DaoSortie.setPublier(idSortie);
	}

}
