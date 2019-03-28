package servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Participant;
import dao.DaoHelper;
import dao.DaoProfil;


/**
 * Servlet implementation class Login.
 */
@WebServlet( urlPatterns="/login", name="login")
public class Login extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       

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
		
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				
				if (cookie.getName().equals("identifiant")) {
					session.setAttribute("identifiant", cookie.getValue());
					System.out.println(cookie.getValue());
				}
			}
		}
		
		request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
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
		
		HttpSession session = request.getSession(true);
		String identifiant = request.getParameter("identifiant");
		String password = request.getParameter("password");
		if(identifiant == null) identifiant = "";
		if(password == null) password = "";
		
		//Si le cookie n'existe pas on le créé si l'utilisateur à coché la checkbox
		if (request.getParameter("seSouvenirDeMoi") != null)
		{
			setCookie(response, "identifiant", identifiant, 60*60*24*30);
			session.setAttribute("identifiant", identifiant);
		}
		else
		{
			setCookie(response, "identifiant", "", 0);
			session.removeAttribute("identifiant");
		}
		
		
		try {
			if (DaoHelper.verifMdp(DaoHelper.hash(password), identifiant) ) {
				try {
					Participant user = DaoProfil.getParticipant(identifiant);
					session.setAttribute("utilisateur", user);
				} catch (SQLException e) {
					request.setAttribute("exception", e);
					request.getRequestDispatcher("/erreur").forward(request, response);
				}
				session.setAttribute("erreur", false);
				response.sendRedirect("/ENI_Sortie_Com/membre/accueil");
				//request.getRequestDispatcher("/membre/accueil").forward(request, response);
			}else {
				session.setAttribute("erreur", true);
				request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
			}
		} catch (NoSuchAlgorithmException | SQLException e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("/erreur").forward(request, response);
		}
		
		
		
		
	}
	
	/**
	 * Sets the cookie.
	 *
	 * @param response the response
	 * @param nom the nom
	 * @param valeur the valeur
	 * @param maxAge the max age
	 */
	private static void setCookie( HttpServletResponse response, String nom, String valeur, int maxAge )
	{
	    Cookie cookie = new Cookie( nom, valeur );
	    cookie.setMaxAge( maxAge );
	    response.addCookie( cookie );
	}	

}
