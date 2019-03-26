package BLL;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.Part;


public class ManagementFile {
	
	private static final int TAILLE_TAMPON = 10240; // 10 ko
	//récupére le nom du fichier.
	public static String getFileName(Part part) {
		
	    for (String cd : part.getHeader("content-disposition").split(";")) {
	        if (cd.trim().startsWith("filename")) {
	            return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}	
	
	//Enregistre le fichier a l'emplacement prévu
	//TODO sort un string qui indique la localisation exact du fichier + son nom
	public static void writeFile(Part part, String name, String path) throws IOException {
		
		InputStream  in = null;
		OutputStream  out = null;
		byte[] tampon = new byte[TAILLE_TAMPON];
		int longueur;
		
		  try {			  

			  in = new BufferedInputStream( part.getInputStream(), TAILLE_TAMPON );
			  out = new BufferedOutputStream( new FileOutputStream( new File( path + name ) ),TAILLE_TAMPON );
				  
			  while ( ( longueur = in.read( tampon ) ) > 0 ) {
		            out.write( tampon, 0, longueur );
		      }
		        
		    } finally {
		        try {
		        	if(out != null) {
		        		out.close();
		        	}
		        	
		        } catch ( IOException e ) {
		        	e.getMessage();
		        }
		        try {
		        	if(in != null) {
		        		in.close();
		        	}
		        			        } catch ( IOException e ) {
		        	e.getMessage();
		        }
		    }
	}
	
	//lecture fichier
	public static void readFile(){
		
	}
	
	public static String uniqueName(String name, int id) {
		//Séparation du nom en deux partit séparé par l'exetension
		String definitiveName = null;
		String extension = name.substring(name.lastIndexOf("."));
		String pureName = name.substring(0, name.lastIndexOf("."));		
		
		definitiveName = pureName +"_"+id + extension;
		
		return definitiveName;	
	}
	
}
