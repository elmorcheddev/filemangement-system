package com.files.pfe.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.files.pfe.model.Categorie;
import com.files.pfe.model.Fichier;
 
public interface FichierService  {
	List<Fichier> listFichier();
	ResponseEntity<String> archiverFichier(Long id);
	void deleteByIdUtilisateur(Long id);
	Fichier getFihierById(Long id);
	 List<Fichier> findAllByEtatIsTrueAndCategorie(Categorie categorie);
	    List<Fichier> findAllByEtatIsFalseAndCategorie(Categorie categorie);
    List<Fichier> findAllByEtatIsTrue();
    List<Fichier> findAllByEtatIsFalse();
 	//Fichier uploadFichier(MultipartFile file, Long sousCategorieId, Long userId) throws IOException;
	Fichier uploadFichier(Fichier fichier, Long sousCategorieId, Long userId) throws IOException;
    List<Fichier> findTop10ByOrderByDateUploadedDesc();
    List<Map<String, Object>> getUploadCountByDate();
}
