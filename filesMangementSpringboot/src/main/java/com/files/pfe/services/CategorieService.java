package com.files.pfe.services;

import java.util.List;

import com.files.pfe.model.Categorie;

public interface CategorieService  {
	List<Categorie> listCategorie();
	
	Categorie addNewCategorie(Categorie categorie);
	
	Categorie findBYiDCategorie(Long id);
	Categorie findByNomCat(String nomCat);

	void deleteByIdCat(Long id);
}
