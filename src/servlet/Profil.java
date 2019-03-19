package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Participant;
import bean.Site;

/**
 * Servlet implementation class Profil
 * membre/profil?pseudo=XXXX
 */
@WebServlet("/membre/profil")
public class Profil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//
		String pseudo =  request.getParameter("pseudo");
		//dao pour récupérer le profil
		System.out.println(pseudo);
		Site site = new Site("Le Mans");
		Participant participant = new Participant("vins", "Vincent","DOBREMEL","0606060606","vincent@vincent.com",true,true, site ,"Le Mans"); 
		//mise en attribut  du 	profil. 	
		
		request.setAttribute("membre",participant);
		
		//Envoi vers la JSP
		request.getRequestDispatcher("/WEB-INF/profil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		doGet(request, response);
	}

}
