package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				System.out.println(cookie.getName().equals("identifiant"));
				System.out.println(cookie.getValue());
				
				if (cookie.getName().equals("identifiant")) {
					request.setAttribute("identifiant", cookie.getValue());
				}
			}
		}
		
		request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String identifiant = request.getParameter("identifiant");
		String password = request.getParameter("password");
		if(identifiant == null) identifiant = "";
		if(password == null) password = "";
		
		//Si le cookie n'existe pas on le cr�� si l'utilisateur � coch� la checkbox
		if (request.getParameter("seSouvenirDeMoi") != null)
		{
			setCookie(response, "identifiant", identifiant, 60*60*24*30);
		}
		else
		{
			setCookie(response, "identifiant", "", 0);
		}
		
		doGet(request, response);
	}
	
	private static void setCookie( HttpServletResponse response, String nom, String valeur, int maxAge )
	{
	    Cookie cookie = new Cookie( nom, valeur );
	    cookie.setMaxAge( maxAge );
	    response.addCookie( cookie );
	}	

}
