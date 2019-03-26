package bll;

import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Lieu;
import bean.Participant;
import bean.Site;
import bean.Sortie;
import bean.Ville;

public class Mappage {
	
	public static Lieu mappageLieu(ResultSet rs) {
		
		Lieu lieu = null;
		
		try {
			
			int id = rs.getInt("no_ville");
			String nom = rs.getString("nom_ville");
			String codePostal = rs.getString("code_postal");
			
			Ville ville = new Ville(id, nom, codePostal);

			id = rs.getInt("no_lieu");
			nom = rs.getString("nom_lieu");
			String rue = rs.getString("rue");
			float latitude = rs.getFloat("latitude");
			float longitude = rs.getFloat("longitude");
			
			lieu = new Lieu(id, nom, rue, ville, latitude, longitude);
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return lieu;
	}
	
	public static Participant mappageParticipant(ResultSet rs) {
		
		Participant participant = null;
		
		try {
				
			Site site = new Site(rs.getInt("sites_no_site"), rs.getString("nom_site"));
				
			
			int id = rs.getInt("no_participant");
			String pseudo = rs.getString("pseudo");
			String nom = rs.getString("nom");
			String prenom = rs.getString("prenom");
			String telephone = rs.getString("telephone");
			String mail = rs.getString("mail");
			boolean administrateur = rs.getBoolean("administrateur");
			boolean actif = rs.getBoolean("actif");
			//String image = rs.getString("image");
			String image = "";
			
			participant = new Participant(id, pseudo, nom, prenom, 
							telephone, mail, administrateur, actif, site, image);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return participant;
	}
	
	public static Site mappageSite(ResultSet rs) {
		Site site = null;
		
		try {
			
			int id = rs.getInt("no_site");
			String nom = rs.getString("nom_site");
			
			site = new Site(id, nom);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return site;
	}
	
	public static Ville mappageVille(ResultSet rs) {
		
		Ville ville = null;
		
		try {

			int id = rs.getInt("no_ville");
			String nom = rs.getString("nom_ville");
			String codePostal = rs.getString("code_postal");
			
			ville = new Ville(id, nom, codePostal);
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ville;
	}
	
	public static Sortie mappageSortie(ResultSet rs) {
		
		Sortie sortie = null;
		
		/*try {
			
				
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		
		return sortie;
	}
	
}
