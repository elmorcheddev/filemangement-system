package com.files.pfe.services;

import java.util.List;

import com.files.pfe.model.Utilisateurs;

public interface UtilisateurService {

	List<Utilisateurs> listUtilisateurs();
	
	Utilisateurs addNewUtilisateur(Utilisateurs utilisateurs);
	
	Utilisateurs findBYiDUtilisateurs(Long id);
	List<Utilisateurs> findByEtatIsFalse();
	List<Utilisateurs> findByEtatIsTrue();
	void deleteByIdUtilisateur(Long id);
	Utilisateurs findByUsername(String username);

 
	Utilisateurs addNewADMIN(Utilisateurs utilisateurs);
	Utilisateurs activeDesactiveUtilisateur(Long id);
	Utilisateurs updateUtilisateur(Utilisateurs utilisateurs);
}
