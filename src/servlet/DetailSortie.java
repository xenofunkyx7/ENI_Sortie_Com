package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Sortie;
import dao.DaoSortie;


/**
 * Servlet implementation class DetailSortie.
 */
@WebServlet("/membre/detailSortie")
public class DetailSortie extends HttpServlet {
	
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

		
		Sortie sortie = null;
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		//get sortie by id
		try {
			sortie = DaoSortie.getSortie(id);
		} catch (SQLException e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("/erreur").forward(request, response);
		}
		
		request.setAttribute( "sortie", sortie);		
		request.getRequestDispatcher("/WEB-INF/detailSortie.jsp").forward(request, response);	
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

		doGet(request, response);
	}

}
