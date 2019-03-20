package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Participant;
import bean.Site;
import dao.DaoProfil;

/**
 * Servlet implementation class Profil
 * membre/profil?pseudo=XXXX
 */
@WebServlet("/membre/profil")
public class Profil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//
		String pseudo =  request.getParameter("pseudo");
		Participant participant = null;
		//dao pour récupérer le profil
		try {
			
			 participant = DaoProfil.getParticipant(pseudo);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		System.out.println(pseudo);
//		Site site = new Site("Le Mans");
//		Participant participant1 = new Participant("vins", "Vincent","DOBREMEL","0606060606","vincent@vincent.com",true,true, site ,"Le Mans"); 
		//mise en attribut  du 	profil. 	
		
		request.setAttribute("membre",participant);
		
		if( participant !=  null) {
			request.getRequestDispatcher("/WEB-INF/profil.jsp").forward(request, response);
		}else {
			//TODO à vérifier. 
			getServletContext().getRequestDispatcher(request.getContextPath()+"/membre/accueil").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		doGet(request, response);
	}

}
