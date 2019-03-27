package servlet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Participant;
import bll.ManagementFile;
import dao.DaoHelper;
import dao.DaoProfil;

/**
 * Servlet implementation class MonProfil
 */
@WebServlet(urlPatterns = "/membre/monProfil")
public class MonProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//TODO récupérer l"image dans C:Image  et la mettre dans W>ebcontent/image si elle n'y est pas déjà.
		//Chemin où sont enregistrés les fichiers
		HttpSession session = request.getSession();
		Participant utilisateur = (Participant) session.getAttribute("utilisateur");
		String avatarName = utilisateur.getImage();		

		ManagementFile mf = new ManagementFile();
		
		
		Path pathImage = Paths.get(mf.readProperties("image.stock")+avatarName);		
		Path pathImageDisplay = Paths.get(mf.readProperties("image.display")+avatarName);
	
		if(! Files.exists(pathImageDisplay)) {
			
			ManagementFile.copyFile(pathImage, pathImageDisplay);
		}	
		
		request.getRequestDispatcher("/WEB-INF/monProfil.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		HttpSession session = request.getSession();		
		Participant utilisateurModif = (Participant) session.getAttribute("utilisateur");
		int idUtilisateur = utilisateurModif.getIdParticipant();
		System.out.println(idUtilisateur+" Id utilisateur");
		
		//Mot de passe vérifié 
		String mdpa = null;
		
		try {
			mdpa = DaoHelper.hash(request.getParameter("mdpa"));
		} catch (NoSuchAlgorithmException e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("/erreur").forward(request, response);
		}
		
		boolean verifMdp = false;
		
		try {
			verifMdp = DaoHelper.verifMdp(mdpa, idUtilisateur);
		} catch (SQLException e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("/erreur").forward(request, response);
		}
		
		//nouveau mdp  verifications + hashage
		String nmdp = request.getParameter("nmdp");
		String cmdp = request.getParameter("cmdp");		
		
		try {
			nmdp = nmdp != null && !nmdp.isEmpty() ? DaoHelper.hash(request.getParameter("nmdp")) : "1" ;
		} catch (NoSuchAlgorithmException e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("/erreur").forward(request, response);
		}
		try {
			cmdp = cmdp != null && !cmdp.isEmpty() ? DaoHelper.hash(request.getParameter("cmdp")) : "2" ;
		} catch (NoSuchAlgorithmException e) {
			request.setAttribute("exception", e);
			request.getRequestDispatcher("/erreur").forward(request, response);
		}
		
		// récupération des parametres du formulaire
		String nPseudo = request.getParameter("pseudo");
		String nPrenom = request.getParameter("prenom");
		String nNom = request.getParameter("nom");
		String nTelephone = request.getParameter("telephone") ;
		String nEmail = request.getParameter("email");
		
		//Verification du telephone tailles et chiffres
		Pattern p = Pattern.compile("\\d{8,15}+");
		Matcher m = p.matcher(nTelephone);
		boolean isRegTel = m.matches();
		
		//resultat du requetage
		int resultModif = 0;

		if(verifMdp) {
			boolean bool = true;
			// modification de monProfil	
			if(nPseudo 		!= null && !nPseudo.isEmpty() && nPseudo.length() <31 ) { utilisateurModif.setPseudo(nPseudo); }
			else {bool = false;} ;
			if(bool && nPrenom 		!= null && !nPrenom.isEmpty() && nPrenom.length() <31  ) { utilisateurModif.setPrenom(nPrenom); }
			else {bool = false;} ;
			if(bool && nNom 		!= null && !nNom.isEmpty() 	  && nNom.length() < 31 ) 	{ utilisateurModif.setNom(nNom); }
			else {bool = false;} ;			
			if(bool && nEmail 		!= null && !nEmail.isEmpty()  && nEmail.length() <21 ) 	{ utilisateurModif.setMail(nEmail); }
			else {bool = false;} ;
			if(bool && nTelephone 	!= null && !nTelephone.isEmpty() && isRegTel ) 			{ utilisateurModif.setTelephone(nTelephone); }
			else {bool = false;} ;
			
			if(bool) {
				try {
					resultModif = DaoProfil.modifyParticipant(utilisateurModif);
				} catch (SQLException e) {
					request.setAttribute("exception", e);
					request.getRequestDispatcher("/erreur").forward(request, response);
				}				
				//modification du mdp
				if(nmdp.equals(cmdp) ) {				
					
					try {
						resultModif = DaoProfil.modifyParticipantMDP(nmdp, idUtilisateur);
					} catch (SQLException e) {
						request.setAttribute("exception", e);
						request.getRequestDispatcher("/erreur").forward(request, response);
					}
				}
				session.setAttribute("utilisateur", utilisateurModif);
			}
		}
		
		
		
		//Permet d'indiquer a la jsp si il y a eu des modifications. 		
		if(resultModif > 0)
		{				
			request.setAttribute("modification", resultModif);
		}
		
		doGet(request, response);
		
	}

}
