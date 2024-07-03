package com.files.pfe.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.files.pfe.model.Autorisation;
import com.files.pfe.model.Fichier;
import com.files.pfe.model.FilePermission;
import com.files.pfe.model.Utilisateurs;
import com.files.pfe.repository.AutorisationRepo;
import com.files.pfe.repository.FichierRepo;
import com.files.pfe.repository.UtilisateurRepo;
import com.files.pfe.services.AutorisationService;
@Service
public class AutorisationServiceImpl implements AutorisationService{

	  @Autowired
	  private AutorisationRepo autorisationRepository;
	  @Autowired
	  private UtilisateurRepo utilisateurRepo;
	  @Autowired
	  private FichierRepo fichierRepo;
	  @Override
	  public Autorisation grantAccess(Long fileId, Long userId, FilePermission permission) {
		    Autorisation autorisation = new Autorisation();
		    Fichier fichier = fichierRepo.findById(fileId).get();  
		    Utilisateurs utilisateur = utilisateurRepo.findById(userId).get(); 
		    boolean auto  = autorisationRepository.existsByFichierAndUtilisateursAndPermission(fichier,utilisateur, permission);
		    System.out.println(auto);
		    if (auto == false ) {
		        autorisation.setFichier(fichier);
		        autorisation.setUtilisateurs(utilisateur);
		        autorisation.setPermission(permission);
		        return autorisationRepository.save(autorisation);
		    } else {
 		        return null;
		    }
		}


	  @Override
	  public void revokeAccess(Long fileId, Long userId) {
		  Fichier fichier= fichierRepo.findById(fileId).get();
		    Utilisateurs utilisateurs= utilisateurRepo.findById(userId).get();
	    autorisationRepository.deleteByFichierAndUtilisateurs(fichier, utilisateurs);
	  }

	 

	@Override
	public List<Autorisation> listAutorisations() {
		// TODO Auto-generated method stub
		return autorisationRepository.findAll();
	}


	@Override
	public List<Autorisation> findByUtilisateursAndFichier(Utilisateurs utilisateurs, Fichier fichier){
		// TODO Auto-generated method stub
		return autorisationRepository.findByUtilisateursAndFichier(utilisateurs,fichier);
	}


	@Override
	public Autorisation hasPermission(Long fileId, Long userId, FilePermission permission) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void deleteAutorization(Long id) {
		// TODO Auto-generated method stub
		autorisationRepository.deleteById(id);
	}


	 


	 

	 
	
 }
