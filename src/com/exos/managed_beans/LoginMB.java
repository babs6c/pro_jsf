package com.exos.managed_beans;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.exos.dao.UtilisateurDao;
import com.exos.entities.Utilisateur;

@ManagedBean
@ViewScoped
public class LoginMB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Utilisateur utilisateur;
   
	// Injection de notre EJB (Session Bean Stateless)
	@EJB
	private UtilisateurDao utilisateurDao;
	
    // Initialisation de l'entité utilisateur
	public LoginMB() {
	
		utilisateur=new Utilisateur();
	}


	// Méthode d'action appelée lors du clic sur le bouton du formulaire
    //de connexion
	public void connecter()
	{
		utilisateurDao.trouver(utilisateur.getEmail(), utilisateur.getPass());
		FacesMessage message = new FacesMessage( "Succès de l'inscription !" );
        FacesContext.getCurrentInstance().addMessage( null, message );
		
	}


	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	
	public List<Utilisateur> getListeUsers() {
		
		return utilisateurDao.lister();
	}
	
}
