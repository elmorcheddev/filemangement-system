package com.files.pfe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.files.pfe.model.Categorie;
 
public interface CategorieRepo extends JpaRepository<Categorie, Long> {
	
	Categorie findByNomCat(String nomCat);
}
