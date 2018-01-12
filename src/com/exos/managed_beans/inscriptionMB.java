package com.exos.managed_beans;

import java.util.Date;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.myfaces.custom.fileupload.UploadedFile;
import org.joda.time.DateTime;

import com.exos.dao.UtilisateurDao;
import com.exos.entities.Utilisateur;

@ManagedBean
@ViewScoped
public class inscriptionMB {
	
	private Utilisateur utilisateur;

	// Injection de notre EJB (Session Bean Stateless)
		@EJB
		private UtilisateurDao utilisateurDao;
		
	    // Initialisation de l'entité utilisateur
		public inscriptionMB() {
		
			utilisateur=new Utilisateur();
		}
	
		// Méthode d'action appelée lors du clic sur le bouton du formulaire
	    //de connexion
		public void inscrire()
		{
			utilisateur.setDateInscription(new DateTime());
			utilisateur.setPhoto("");
			try {
				utilisateurDao.add(utilisateur);
				FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Succès de l'inscription !", null );
		        FacesContext.getCurrentInstance().addMessage( null, message );
			}
			catch(Exception e)
			{
				 FacesMessage message = new FacesMessage( FacesMessage.SEVERITY_ERROR, e.getMessage(), null );
				 FacesContext.getCurrentInstance().addMessage( null,  message );
			}
		}


		public Utilisateur getUtilisateur() {
			return utilisateur;
		}

		public void setUtilisateur(Utilisateur utilisateur) {
			this.utilisateur = utilisateur;
		}

}
