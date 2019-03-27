package bll;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import bean.Lieu;
import bean.Participant;
import bean.Site;
import bean.Sortie;
import bean.Ville;
import dao.DaoProfil;
import dao.DaoSortie;
import bean.Sortie.Etats;

public class Mappage {
	
	public static Lieu mappageLieu(ResultSet rs) {
		
		Lieu lieu = null;
		
		try {
			
			Ville ville = mappageVille(rs);

			int id = rs.getInt("no_lieu");
			String nom = rs.getString("nom_lieu");
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
			Site site = mappageSite(rs);
			
			int id = rs.getInt("no_participant");
			String pseudo = rs.getString("pseudo");
			String nom = rs.getString("nom");
			String prenom = rs.getString("prenom");
			String telephone = rs.getString("telephone");
			String mail = rs.getString("mail");
			boolean administrateur = rs.getBoolean("administrateur");
			boolean actif = rs.getBoolean("actif");
			String image = rs.getString("urlAvatar");
			
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
		
		try {
			
			int id = rs.getInt("sites_no_site");
			String nom = rs.getString("sites_nom_site");
			
			Site siteSortie = new Site(id, nom);
			Lieu lieu = mappageLieu(rs);
			
			id = rs.getInt("no_sortie");
			nom = rs.getString("nom_sortie");
			Date dateDebut = rs.getDate("datedebut");
			int duree = rs.getInt("duree");
			Date dateCloture = rs.getDate("datecloture");
			
			int nbInscription = rs.getInt("nbinscriptionsmax");
			String descriptionInfos = rs.getString("descriptioninfos");
			String urlPhoto = rs.getString("urlPhoto");
			
			Participant organisateur = mappageParticipant(rs);
			
			int etats_no_etat = rs.getInt("etats_no_etat") - 1;
			Etats etat = Etats.values()[etats_no_etat];
			
			List<Participant> participants = DaoProfil.getParticipantsBySortie(id);
			
			// le changement c'est mainentatgna
			
			Date now = new Date(new java.util.Date().getTime() );
			Date moisProchain = new Date (dateDebut.getTime() + 31l*24l*60l*60l*1000l );
			Date fini = new Date (dateDebut.getTime() + ((long) duree) *60l*1000l );
			
			if ((etat == Etats.PASSEE || etat == Etats.ACTIVITE_EN_COURS 
					|| etat == Etats.CLOTUREE || etat == Etats.OUVERTE)
					&& now.after(moisProchain )) {
				DaoSortie.setArchive(id);
				nom += " [ARCHIVE]";
				etat = Etats.PASSEE;
			}else {
				if((etat == Etats.ACTIVITE_EN_COURS || etat == Etats.CLOTUREE 
						|| etat == Etats.OUVERTE) && now.after(fini )) {
					DaoSortie.setPassee(id);
					etat = Etats.PASSEE;
				}else {
					if( (etat == Etats.CLOTUREE || etat == Etats.OUVERTE ) && now.after(dateDebut )) {
						DaoSortie.setEnCours(id);
						etat = Etats.ACTIVITE_EN_COURS;
					}else {
						if( etat == Etats.OUVERTE && now.after(dateCloture )) {
							DaoSortie.setCloture(id);
							etat = Etats.CLOTUREE;
						}
					}
				}
			}
			
			sortie = new Sortie(id, nom, dateDebut, duree, dateCloture, nbInscription, 
					descriptionInfos, etat, organisateur, participants, lieu, siteSortie, urlPhoto);
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return sortie;
	}
	
}
