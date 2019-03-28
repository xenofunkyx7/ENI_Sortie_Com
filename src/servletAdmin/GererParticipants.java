package servletAdmin;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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
import dao.DaoHelper;
import dao.DaoProfil;
import dao.DaoSite;

/**
 * Servlet implementation class GererParticipants
 */
@WebServlet("/admin/gererParticipants")
public class GererParticipants extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GererParticipants() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		List<Participant> participants = new ArrayList<>();
		List<Site> sites = new ArrayList<>();
		try {
			participants = DaoProfil.getParticipants();
			sites = DaoSite.getSites("");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		session.setAttribute("participants", participants);
		session.setAttribute("sites", sites);
		
		request.getRequestDispatcher("/WEB-INF/admin/gererParticipants.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String btn = request.getParameter("btn");
		
		switch (btn) {
		case "Supprimer":
			try {
				supprimer(request, response);
			} catch (SQLException e) {
				request.setAttribute("exception", e);
				request.getRequestDispatcher("/erreur").forward(request, response);
			}
			break;
		case "Ajouter":
			try {
				ajouter(request, response);
			} catch (NoSuchAlgorithmException e) {
				request.setAttribute("exception", e);
				request.getRequestDispatcher("/erreur").forward(request, response);
			}
			break;
		default:
			break;
		}
		
		
		doGet(request, response);
	}

	private void ajouter(HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException {
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String mail = request.getParameter("mail");
		String telephone = request.getParameter("telephone");
		String mdp = request.getParameter("mdp");
		int idSite = Integer.parseInt(request.getParameter("site"));
		
		Site site = new Site(idSite, "");
		
		Participant participant = new Participant(pseudo, nom, prenom, telephone, mail, false, true, site, "");
		
		DaoProfil.addParticipant (participant, DaoHelper.hash(mdp));
	}

	private void supprimer(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		DaoProfil.deleteParticipant(id);
	}

}
