package com.files.pfe.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

import com.files.pfe.model.Utilisateurs;

public class UtilisateurValidation {
	
	
	public static List<String> VALIDATION(Utilisateurs utilisateurs){
		
		List<String> erreur= new ArrayList<>();
		if(!StringUtils.hasLength(utilisateurs.getNom())) {
			erreur.add("NOM EST OBLIGATOIRE");
		}
		if(!StringUtils.hasLength(utilisateurs.getPrenom())) {
			erreur.add("Prenom EST OBLIGATOIRE");
		}
		
		return erreur;
		
	}
}
