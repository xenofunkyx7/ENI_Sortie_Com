package bll;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

import javax.servlet.http.Part;


// 
/**
 * The Class ManagementFile.
 * Cette classe  sert à oa gestion des fichiers, plus particulierement les images. 
 */
public class ManagementFile {
	
	/** The Constant TAILLE_TAMPON. */
	private static final int TAILLE_TAMPON = 10240; // 10 ko
	
	/**
	 * Gets the file name.
	 * Cette classe return le nom du fichier qu'a rentré l'utilisateur
	 * @param part the part
	 * @return the file name
	 */
	//récupére le nom du fichier.
	public static String getFileName(Part part) {
		
	    for (String cd : part.getHeader("content-disposition").split(";")) {
	        if (cd.trim().startsWith("filename")) {
	             return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");	             
	        }
	    }
	    return null;
	}	
	
	
	/**
	 * Write file.
	 *
	 *Enregistre de maniere definitive le fichier en dehoors de l'application.
	 *
	 * @param part the part Le fichier Fourni
	 * @param name the name Le nom du fichier à enregistrer
	 * @param path the path La localisation où le fichier sera enregistré
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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
		        
	        	if(out != null) {
	        		out.close();
	        	}     
	        
	        	if(in != null) {
	        		in.close();
	        	}
		        			         
		  }
	}
	
	
	
	/**
	 * copyFile.
	 * 
	 * Copie le fichier
	 *
	 * @param source the source La source du fichier à enreghistrer
	 * @param destination the destination. La destination du fichier à enrtegistrer
	 * @return true, if successful
	 * @throws IOException 
	 */
	public static boolean copyFile(Path source, Path destination) throws IOException {
	
			Files.copy(source, destination);
			return true;
	}
	
	/**
	 * Read properties.
	 * Lis les prsopriétés envoyés du fichier config.properties
	 *
	 * @param propertiesName the properties name
	 * @return the string
	 * @throws IOException 
	 */
	//lecture fichier
	public  String readProperties(String propertiesName) throws IOException {
		
		Properties prop = new Properties();
		InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");
		String propriete = null;
		
		
		if(input != null)
		{
			prop.load(input);
			propriete = prop.getProperty(propertiesName);			
		}
		
		return propriete;	
	}
	
	/**
	 * Unique name.
	 *
	 *Modifie le nom du donné en parametre  en y ajoutant un numero.
	 *Et supprime les espaces.
	 *
	 * @param name the name
	 * @param id the id
	 * @return the string
	 */
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
