package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class PhotoProfil
 */
@WebServlet("/membre/avatar")
public class PhotoProfil extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	/**
	 *@MultipartConfig(location="C:/env-dev/projets/javaEE\NI_Sortie_Com/WebContent/image", fileSizeThreshold = 1024 * 1024,
	 *maxFileSize = 1024 * 1024 * 5, 
	 *maxRequestSize = 1024 * 1024 * 5 * 5)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		 System.out.println(isMultipart);
		 Part image = request.getPart("file");
		 
		 System.out.println(image);
		 
//	    for (Part part : request.getParts()) {
//	    	
//	        String filename = getFilename(part);
//	        
//	        if (filename == null) {
//	            // Traiter les champs classiques ici (input type="text|radio|checkbox|etc", select, etc).
//	            String fieldname = part.getName();
//	            String fieldvalue = getValue(part);
//	            // ... (traitement à faire)
//	        } else if (!filename.isEmpty()) {
//	            // Traiter les champs de type fichier (input type="file").
//	            String fieldname = part.getName();
//	            filename = filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
//	            InputStream filecontent = part.getInputStream();
//	            // ... (traitement à faire)
//	        }
//	    }

	    // ...
	    
	    

		   
	    this.getServletContext().getRequestDispatcher( "/WEB-INF/monProfil.jsp" ).forward( request, response );
	}
	
	private static String getFilename(Part part) {
	    for (String cd : part.getHeader("content-disposition").split(";")) {
	        if (cd.trim().startsWith("filename")) {
	            return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}
		
	private static String getValue(Part part) throws IOException {
	    BufferedReader reader = new BufferedReader(new InputStreamReader(part.getInputStream(), "UTF-8"));
	    StringBuilder value = new StringBuilder();
	    char[] buffer = new char[1024];
	    for (int length = 0; (length = reader.read(buffer)) > 0;) {
	        value.append(buffer, 0, length);
	    }
	    return value.toString();
	}
}
