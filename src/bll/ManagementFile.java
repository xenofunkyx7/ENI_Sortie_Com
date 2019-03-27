package bll;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

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
	//Enregistre  le fichier avec le nom choisi à la localisation indiqué
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
	
	
	public static boolean copyFile(Path source, Path destination) {
		
		try {			
				
			Files.copy(source, destination);
			
		}catch(IOException e) {
			e.printStackTrace(); 
	        return false; 
		}
		
		return true;
		
	}
	//lecture fichier
	public  String readProperties(String propertiesName) {
		
		Properties prop = new Properties();
		InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");
		String propriete = null;
		
		
		if(input != null)
		{
			try {
				
				prop.load(input);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			propriete = prop.getProperty(propertiesName);			
		}
		
		return propriete;
	
		
	}
	
	public static String uniqueName(String name, int id) {
		//Séparation du nom en deux partit séparé par l'exetension
		String definitiveName = null;		
		String extension = name.substring(name.lastIndexOf("."));
		String pureName = name.substring(0, name.lastIndexOf("."));		
		
		System.out.println("dans le uniqueName ");
		definitiveName = pureName +"_"+id + extension;
		definitiveName = definitiveName.replaceAll("\\s", "_");
		
		return definitiveName;	
	}
	
}
