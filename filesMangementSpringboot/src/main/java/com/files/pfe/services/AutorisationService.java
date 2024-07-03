package com.files.pfe.services;

import java.util.List;

import com.files.pfe.model.Autorisation;
import com.files.pfe.model.Fichier;
import com.files.pfe.model.FilePermission;
import com.files.pfe.model.Utilisateurs;

 
public interface AutorisationService  {
 
	  void revokeAccess(Long fileId, Long userId);
	  List<Autorisation> listAutorisations();
	Autorisation hasPermission(Long fileId, Long userId, FilePermission permission);
 	List<Autorisation> findByUtilisateursAndFichier(Utilisateurs utilisateurs, Fichier fichier);
 	void deleteAutorization(Long id);
	Autorisation grantAccess(Long fileId, Long userId, FilePermission permission);

}
