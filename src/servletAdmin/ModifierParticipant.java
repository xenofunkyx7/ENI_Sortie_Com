package servletAdmin;

import java.io.IOException;
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
import dao.DaoProfil;
import dao.DaoSite;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class GererParticipants.
 */
@WebServlet("/admin/modifierParticipant")
public class ModifierParticipant extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       
    /**
     * Instantiates a new modifier participant.
     *
     * @see HttpServlet#HttpServlet()
     */
    public ModifierParticipant() {
        super();
        // TODO Auto-generated constructor stub
    }

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
		HttpSession session = request.getSession(true);
		Participant participant = null;
		List<Site> sites = new ArrayList<>();
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		try {
			participant = DaoProfil.getParticipantById(id);
			sites = DaoSite.getSites("");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		session.setAttribute("participant", participant);
		session.setAttribute("sites", sites);
		
		request.getRequestDispatcher("/WEB-INF/admin/modifierParticipant.jsp").forward(request, response);
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
		int id = Integer.parseInt(request.getParameter("id"));
		String pseudo = request.getParameter("pseudo");
		String nom = request.getParameter("nom");
		String prenom = request.getParameter("prenom");
		String mail = request.getParameter("mail");
		String telephone = request.getParameter("telephone");
		int idSite = Integer.parseInt(request.getParameter("site"));
		boolean isAdmin = request.getParameter("admin")!= null;
		
		Site site = new Site(idSite, "");
		
		Participant participant = new Participant(id, pseudo, nom, prenom, telephone, mail, isAdmin, true, site, "");
		
		try {
			DaoProfil.modifyParticipant(participant);
		} catch (SQLException e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("/erreur").forward(request, response);
		}
		DaoProfil.changeAdmin(participant);
		
		doGet(request, response);
	}

}
