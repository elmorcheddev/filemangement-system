package com.files.pfe.services.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.files.pfe.model.Roles;
import com.files.pfe.model.Utilisateurs;
import com.files.pfe.repository.RolesRepo;
import com.files.pfe.repository.UtilisateurRepo;
import com.files.pfe.services.UtilisateurService;
import com.files.pfe.validation.UtilisateurValidation;

 @Service
public class UtilisateurServiceImpl implements UtilisateurService {

	@Autowired
	private UtilisateurRepo utilisateurRepo;
	@Autowired
	private RolesRepo rolesRepo;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	 

	@Override
	public List<Utilisateurs> listUtilisateurs() {
		// TODO Auto-generated method stub
		return utilisateurRepo.findAll();
	}

	@Override
	public Utilisateurs addNewUtilisateur(Utilisateurs utilisateurs) {
		List<String> erreur=UtilisateurValidation.VALIDATION(utilisateurs);
		Roles roles=rolesRepo.findByNomRoles("EMPLOYEUR");
		Set<Roles> listRoles= new HashSet<>();
		listRoles.add(roles);
		if(erreur.isEmpty() && utilisateurRepo.findByUsername(utilisateurs.getUsername())== null) {
			utilisateurs.setRoles(listRoles);
	        utilisateurs.setPassword(passwordEncoder.encode(utilisateurs.getPassword()));
	        utilisateurs.setNom(utilisateurs.getNom());
	        utilisateurs.setPrenom(utilisateurs.getPrenom());
	        utilisateurs.setCin(utilisateurs.getCin());
	        utilisateurs.setPhoto(utilisateurs.getPhoto());

	        utilisateurs.setAdresse(utilisateurs.getAdresse());
	        utilisateurs.setUsername(utilisateurs.getUsername());
	 		return utilisateurRepo.save(utilisateurs);

		}else {
			System.out.println(" mohamed is the best ");
			return null;
		}
	}
	@Override
	public Utilisateurs updateUtilisateur(Utilisateurs utilisateurs) {
		List<String> erreur=UtilisateurValidation.VALIDATION(utilisateurs);
		Roles roles=rolesRepo.findByNomRoles("EMPLOYEUR");
		Set<Roles> listRoles= new HashSet<>();
		listRoles.add(roles);
		if(erreur.isEmpty()) {
			utilisateurs.setRoles(listRoles);
	        utilisateurs.setPassword(passwordEncoder.encode(utilisateurs.getPassword()));
	        utilisateurs.setNom(utilisateurs.getNom());
	        utilisateurs.setPrenom(utilisateurs.getPrenom());
	        utilisateurs.setCin(utilisateurs.getCin());
	        utilisateurs.setPhoto(utilisateurs.getPhoto());

	        utilisateurs.setAdresse(utilisateurs.getAdresse());
	        utilisateurs.setUsername(utilisateurs.getUsername());
	 		return utilisateurRepo.save(utilisateurs);

		}else {
			System.out.println(" mohamed is the best ");
			return null;
		}
	}

	@Override
	public Utilisateurs addNewADMIN(Utilisateurs utilisateurs) {
	    List<String> errors = UtilisateurValidation.VALIDATION(utilisateurs); // Validate user data

	    boolean adminbyRolesExists = utilisateurRepo.existsByRolesContains(Collections.singleton(rolesRepo.findByNomRoles("ADMIN")));
	    boolean adminbyUserNameExists = utilisateurRepo.existsByUsername(utilisateurs.getUsername());

	    if (errors.isEmpty() && !adminbyRolesExists && !adminbyUserNameExists) {
	        Roles adminRole = rolesRepo.findByNomRoles("ADMIN");
	        Set<Roles> roles = new HashSet<>();
	        roles.add(adminRole);
	        utilisateurs.setPhoto(utilisateurs.getPhoto());
	        utilisateurs.setPassword(passwordEncoder.encode(utilisateurs.getPassword()));
	        utilisateurs.setRoles(roles);
	        return utilisateurRepo.save(utilisateurs);
	    } else {
	        System.out.println("An admin user already exists, or validation errors occurred.");
	        return null;
	    }
	}

	 
	@Override
	public Utilisateurs findBYiDUtilisateurs(Long id) {
 		return utilisateurRepo.findById(id).get();
	}

	@Override
	public void deleteByIdUtilisateur(Long id) {
		utilisateurRepo.deleteById(id);
	}

	@Override
	public Utilisateurs findByUsername(String username) {
		// TODO Auto-generated method stub
		return  utilisateurRepo.findByUsername(username);
	}

	@Override
	public Utilisateurs activeDesactiveUtilisateur(Long id) {
		Utilisateurs utilisateurs= utilisateurRepo.findById(id).orElse(null);
		if(utilisateurs.isEtat()== true) {
			utilisateurs.setEtat(false);
			return utilisateurRepo.save(utilisateurs);
		}else  {
			utilisateurs.setEtat(true);
			return utilisateurRepo.save(utilisateurs);

		} 
	}

	@Override
	public List<Utilisateurs> findByEtatIsFalse() {
		// TODO Auto-generated method stub
		return utilisateurRepo.findByEtatIsFalse();
	}

	@Override
	public List<Utilisateurs> findByEtatIsTrue() {
		// TODO Auto-generated method stub
		 return utilisateurRepo.findByEtatIsTrue();

	}

}
