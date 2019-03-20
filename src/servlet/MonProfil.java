package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Participant;
import bean.Site;
import dao.DaoAdmin;

/**
 * Servlet implementation class MonProfil
 */
@WebServlet("/membre/monProfil")
public class MonProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Site> sites = null;
		
		try {
			
			sites = DaoAdmin.getSites("");
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		//Mis en attribut des sites afin qu'ils soient affichés  dans la jsp 
		request.setAttribute("sites", sites);		
		request.getRequestDispatcher("/WEB-INF/monProfil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession Session = request.getSession();
		Participant utilisateurModif = new Participant();
		
		String mdp = (request.getParameter("mdp"));
		String cMdp =(request.getParameter("cmdp"));
		
		utilisateurModif.setPseudo(request.getParameter("pseudo"));
		utilisateurModif.setPrenom(request.getParameter("prenom"));
		utilisateurModif.setNom(request.getParameter("nom"));
		utilisateurModif.setTelephone(request.getParameter("telephone"));
		utilisateurModif.setMail(request.getParameter("email"));
		
		
		//site a confirmer. 
		
//		utilisateurModif.setSite(site);(request.getParameter("pseudo"));
		
		
		doGet(request, response);
		
		
	}

}
