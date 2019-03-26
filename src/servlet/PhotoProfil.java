package servlet;

import java.io.IOException;

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

/**
 * Servlet implementation class PhotoProfil
 */

@MultipartConfig(location="c:/image", fileSizeThreshold = 1024 * 1024,
maxFileSize = 1024 * 1024 * 5, 
maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(name = "uploadAvatar", urlPatterns = "/membre/avatar", 
initParams = @WebInitParam(name = "location", value="c:/image/"))
public class PhotoProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//Récupération de l'utilisateur en  session
		 HttpSession session = request.getSession();
		 Participant utilisateur = (Participant) session.getAttribute("utilisateur");
		 
		 // Recupération du fichoier ainsi que du nom de celui ci
		 Part image = request.getPart("file");
		 String imageName = ManagementFile.uniqueName(ManagementFile.getFileName(image) , utilisateur.getIdParticipant());
			 
		 // Récupération du chemin d'inscription du fichier
		 String path =  getServletConfig().getInitParameter("location"); 
		 
		 //Inscription du fichier dans le dossier correspondant. 
		 ManagementFile.writeFile(image, imageName, path);
		   

	    response.sendRedirect("/ENI_Sortie_Com/membre/monProfil");
	}
}
