package servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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
import dao.DaoHelper;
import dao.DaoProfil;

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
		
		request.getRequestDispatcher("/WEB-INF/monProfil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Participant utilisateurModif = (Participant) session.getAttribute("utilisateur");
		Site site = null;
		
		//nouveau mdp 
		String nmdp = request.getParameter("nmdp");
		String cmdp =request.getParameter("cmdp");
		
		//mdp actuel
		String mdpa = request.getParameter("mdpa");
		String test;
		
		try {
			
			byte[] mdpHash = DaoHelper.hash("vincent");
			test = mdpHash.toString();
			
			
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(test);
		// changement des informations utilisateurs.

			utilisateurModif.setPseudo(request.getParameter("pseudo"));
			utilisateurModif.setPrenom(request.getParameter("prenom"));
			utilisateurModif.setNom(request.getParameter("nom"));
			utilisateurModif.setTelephone(request.getParameter("telephone"));
			utilisateurModif.setMail(request.getParameter("email"));

			
			DaoProfil.modifyParticipant(utilisateurModif);			
		
		
		
		//site a confirmer. 
		
//		utilisateurModif.setSite(site);(request.getParameter("pseudo"));
		
		
		doGet(request, response);
		
		
	}

}
