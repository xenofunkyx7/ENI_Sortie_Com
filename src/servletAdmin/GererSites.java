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

import bean.Site;
import dao.DaoSite;

/**
 * Servlet implementation class GererSites
 */
@WebServlet("/admin/gererSites")
public class GererSites extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GererSites() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		List<Site> sites = new ArrayList<>();
		try {
			sites = DaoSite.getSites("");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		session.setAttribute("sites", sites);
		request.getRequestDispatcher("/WEB-INF/admin/gererSites.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String btn = request.getParameter("btn");
		
		String nom = request.getParameter("nom");
		String idStr = request.getParameter("id");
		int id = -1;
		Site site = null;
		
		if (idStr != null) {
			id = Integer.parseInt(idStr);
			site = new Site(id, nom);
		}else {
			site = new Site(nom);
		}
		
		switch (btn) {
		case "Ajouter":
			DaoSite.addSite(site);
			break;
		case "Modifier":
			DaoSite.modifySite(site);
			break;
		case "Supprimer":
			DaoSite.deleteSite(site);
			break;
		default:
			break;
		}
		
		doGet(request, response);
	}

}
