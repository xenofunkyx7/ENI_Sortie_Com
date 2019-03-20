package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Lieu;
import bean.Sortie;
import bean.Ville;
import dao.DaoLieu;
import dao.DaoVille;



/**
 * Servlet implementation class Sortie
 */
@WebServlet("/membre/Sortie")
public class CreerSortie extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//validation git

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Ville> villes = null;
		List<Lieu> lieux = null;
		
		
		// Recupération de tout les lieux et Villes
		try {
			villes = DaoVille.getVilles("");
		} catch (SQLException e) {
			//TODO
			e.printStackTrace();
		}
		try {
			lieux = DaoLieu.getLieux("");
		} catch (SQLException e) {
			// TODO 
			e.printStackTrace();
		}
		
		request.setAttribute("villes",villes );
		request.setAttribute("lieux", lieux);
		
		
		request.getRequestDispatcher("/WEB-INF/sortie.jsp").forward(request, response); 
	}
 
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		
		Sortie newSortie = new Sortie();
		
		newSortie.setNom(nom);
		newSortie.setDateHeureDebut(dateHeureDebut);
		newSortie.setDateLimiteInscription(dateLimiteInscription);
		newSortie.setNbInscriptionMax(nbInscriptionMax);
		newSortie.setDuree(duree);
		newSortie.setInfoSortie(infoSortie);
		newSortie.setLieu(lieu);
		//probleme set ville qui est Site
		
		
		
	
		
	}

}
