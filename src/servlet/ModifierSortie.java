package servlet;

import java.io.IOException;
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
import dao.DaoSortie;

/**
 * Servlet implementation class ModifierSortie
 */
@WebServlet("/ModifierSortie")
public class ModifierSortie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		Participant participant = (Participant) session.getAttribute("utilisateur");
		
		Sortie sortie = null;
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		//On récupère la sorti correspondante 
		try { sortie = DaoSortie.getSortie(id);} catch (SQLException e) {
			//TODO Redirection page erreurs
			System.out.println("erreur dao sortie dans annulerSortie GET");
		}
		
		if (	participant.getIdParticipant() 	== 	sortie.getOrganisateur().getIdParticipant()
			&&	sortie.getEtat() 				== 	Etats.CREEE)
		{
			request.setAttribute( "sortie", sortie);		
			request.getRequestDispatcher("/WEB-INF/sortie.jsp").forward(request, response);
		}
		else
		{
			//TODO envoyer vers page erreur acces interdit.
			System.out.println("acces interdit ModifierSortie");
		}

		request.getRequestDispatcher("/WEB-INF/sortie.jsp").forward(request, response);		
	}
 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
	}

}