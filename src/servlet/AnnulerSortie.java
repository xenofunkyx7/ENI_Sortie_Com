package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Participant;
import bean.Sortie;
import bean.Sortie.Etats;
import dao.DaoAnnulation;
import dao.DaoSortie;


/**
 * Servlet implementation class AnnulerSortie.
 */
@WebServlet("/membre/annulerSortie")
public class AnnulerSortie extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
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
		
		HttpSession session = request.getSession();
		Participant user = (Participant) session.getAttribute("utilisateur");
		
		Sortie sortie = null;
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		//On récupère la sorti correspondante 
		try { 
			sortie = DaoSortie.getSortie(id);
		} catch (SQLException e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("/erreur").forward(request, response);
		}
		
		if (	( user.getIdParticipant() 	== 	sortie.getOrganisateur().getIdParticipant() || user.isAdministrateur() )
			&&	( sortie.getEtat() 			== 	Etats.CREEE									|| 	sortie.getEtat() 		== 	Etats.OUVERTE) )
		{
			request.setAttribute( "sortie", sortie);		
			request.getRequestDispatcher("/WEB-INF/annulerSortie.jsp").forward(request, response);
		}
		else
		{
			request.setAttribute("erreur", "Accès Interdit (Annulation Sortie");
			request.getRequestDispatcher("/erreur").forward(request, response);
		}
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String motif = request.getParameter("motif");
		int idSortie = Integer.parseInt(request.getParameter("id"));
		
		Date dateEtHeureActuel = new Date(new java.util.Date().getTime());
		
		
		try {
			DaoSortie.setAnnulation(idSortie);
		} catch (SQLException e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("/erreur").forward(request, response);
		}
		try {
			DaoAnnulation.addAnnulation(idSortie, motif, dateEtHeureActuel);
		} catch (SQLException e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("/erreur").forward(request, response);
		}
		response.sendRedirect("/ENI_Sortie_Com/membre/accueil");
		
		
	}

}
