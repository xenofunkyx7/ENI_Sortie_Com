package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import bean.Participant;
import bll.ManagementFile;
import dao.DaoProfil;


/**
 * Servlet implementation class PhotoProfil.
 */

@MultipartConfig(location="c:/image", fileSizeThreshold = 1024 * 1024,
maxFileSize = 1024 * 1024 * 5, 
maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(name = "uploadAvatar", urlPatterns = "/membre/avatar", 
initParams = @WebInitParam(name = "location", value="c:/image/"))
public class PhotoProfil extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
       


	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Récupération de l'utilisateur en  session
		HttpSession session = request.getSession();
		Participant utilisateur = (Participant) session.getAttribute("utilisateur");
		int id_participant = utilisateur.getIdParticipant();
		 
		// Recupération du fichoier ainsi que du nom de celui ci
		Part fileImage = request.getPart("file");
		String imageName = ManagementFile.uniqueName(ManagementFile.getFileName(fileImage) , id_participant );
		System.out.println("image name  = "+imageName);	 
		// Récupération du chemin d'inscription du fichier
		String path =  getServletConfig().getInitParameter("location"); 
		System.out.println("le path" +path);
		
		//Inscription du fichier dans le dossier correspondant. 
		ManagementFile.writeFile(fileImage, imageName, path);
		 
		//modification du nom de la photo de profil dans la BDD
		try {
			DaoProfil.modifyAvatar(imageName, id_participant);
		} catch (SQLException e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("/erreur").forward(request, response);
		}
		utilisateur.setImage(imageName);   

	    response.sendRedirect("/ENI_Sortie_Com/membre/monProfil");
	}
}
