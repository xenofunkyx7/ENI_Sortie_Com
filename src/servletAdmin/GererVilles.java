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

import bean.Ville;
import dao.DaoVille;

/**
 * Servlet implementation class GererVilles
 */
@WebServlet("/admin/gererVilles")
public class GererVilles extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GererVilles() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		List<Ville> villes = new ArrayList<>();
		try {
			villes = DaoVille.getVilles("");
		} catch (SQLException e) {

			e.printStackTrace();
		}
		session.setAttribute("villes", villes);
		request.getRequestDispatcher("/WEB-INF/admin/gererVilles.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String btn = request.getParameter("btn");
		
		String nom = request.getParameter("nom");
		String codePostal = request.getParameter("codePostal");
		String idStr = request.getParameter("id");
		int id = -1;
		Ville ville = null;
		
		if (idStr != null) {
			id = Integer.parseInt(idStr);
			ville = new Ville(id, nom, codePostal);
		}else {
			ville = new Ville(nom, codePostal);
		}
		
		switch (btn) {
		case "Ajouter":
			try {
				DaoVille.addVille(ville);
			} catch (SQLException e) {
				request.setAttribute("exception", e);
				request.getRequestDispatcher("/erreur").forward(request, response);
			}
			break;
		case "Modifier":
			try {
				DaoVille.modifyVille(ville);
			} catch (SQLException e) {
				request.setAttribute("exception", e);
				request.getRequestDispatcher("/erreur").forward(request, response);
			}
			break;
		case "Supprimer":
			try {
				DaoVille.deleteVille(ville);
			} catch (SQLException e) {
				request.setAttribute("exception", e);
				request.getRequestDispatcher("/erreur").forward(request, response);
			}
			break;
		default:
			break;
		}

		doGet(request, response);
	}

}
